/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.BaseInputConnection
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebView
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import org.jsoup.Jsoup
import org.mjdev.tvlib.extensions.GlobalExt.launchInMain
import org.mjdev.tvlib.webscrapper.adblock.IAdBlocker
import org.mjdev.tvlib.webscrapper.adblock.NoAdBlock
import org.mjdev.tvlib.webscrapper.select.Doc
import timber.log.Timber
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

@Suppress("MemberVisibilityCanBePrivate", "unused")
@SuppressLint(
    "SetJavaScriptEnabled", "ViewConstructor", "JavascriptInterface",
    "ClickableViewAccessibility"
)
class CustomWebView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val userAgent: String = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36",
    private val adBlocker: IAdBlocker = NoAdBlock(),
    val onError: CustomWebView.(Throwable) -> Unit = { e -> Timber.e(e) },
    private val onIconReceived: (CustomWebView.(Bitmap?) -> Unit)? = null,
    private val onTitleReceived: (CustomWebView.(String?) -> Unit)? = null,
    private val onProgress: (CustomWebView.(Int) -> Unit)? = null,
    private val onPageLoadStart: (CustomWebView.() -> Unit)? = null,
    private val onPageLoadFinish: (CustomWebView.() -> Unit)? = null,
    private val onUnhandledKey: (CustomWebView.(event: KeyEvent?) -> Boolean)? = null,
    private val maxScriptDelay: Long = 5 * 60 * 1000, // 5 minutes
    private val loadImages: Boolean = true,
    private val loadScripts: Boolean = true,
    private val loadMedia: Boolean = true,
    private val loadCssStyles: Boolean = true,
    private val loadFonts: Boolean = true
) : WebView(context, attrs, defStyleAttr) {
    private val cookieManager: CookieManager = CookieManager.getInstance()
    private val scrapeTasks = mutableListOf<ScrapeTask>()
    private var currentScrapeTask: ScrapeTask? = null
    private val webChromeClient by lazy {
        CustomChromeClient(
            context = context,
            userAgent = userAgent,
            onProgress = { progress -> onProgress?.invoke(this, progress) },
            onError = { error -> onError(this, error) },
            onIconReceived = { bitmap -> onIconReceived?.invoke(this, bitmap) },
            onTitleReceived = { title -> onTitleReceived?.invoke(this, title) },
            popupEnabled = false
        )
    }
    private val webViewClient by lazy {
        CustomWebViewClient(
            adBlocker = adBlocker,
            loadImages = loadImages,
            loadScripts = loadScripts,
            loadMedia = loadMedia,
            loadCssStyles = loadCssStyles,
            loadFonts = loadFonts,
            onError = { error -> onError(this, error) },
            onPageLoadStart = { onPageLoadStart?.invoke(this) },
            onPageLoadFinish = {
                postFinishPageLoading()
                onPageLoadFinish?.invoke(this)
            }
        )
    }

    val isScrapping get() = scrapeTasks.isNotEmpty()
    val links = ArrayDeque<String>()
    val scannedLinks = mutableListOf<String>()

    val scrapeScope
        get() = flow {
            runCatching {
                Doc(getHtmlContent().asDocument(url ?: ""))
            }.onFailure { error ->
                emit(
                    ScrapeScope(
                        url = currentScrapeTask?.url,
                        title = title ?: "",
                        error = error,
                        document = null,
                        videos = emptyList(),
                        anchors = emptyList()
                    )
                )
            }.onSuccess { document ->
                emit(
                    ScrapeScope(
                        url = currentScrapeTask?.url,
                        title = title ?: "",
                        error = null,
                        document = document,
                        videos = document.eachVideo,
                        audios = document.eachAudio,
                        anchors = document.eachAnchor
                    )
                )
            }
        }

    init {
        background = ColorDrawable(0)
        isFocusableInTouchMode = true
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(this, true)
        setWebChromeClient(webChromeClient)
        setWebViewClient(webViewClient)
        settings.cacheMode = LOAD_DEFAULT
        settings.domStorageEnabled = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        settings.useWideViewPort = true
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.allowContentAccess = true
        settings.allowFileAccess = true
        settings.blockNetworkImage = !loadImages
        settings.blockNetworkLoads = false
        settings.builtInZoomControls = false
        settings.cacheMode = LOAD_DEFAULT
        settings.databaseEnabled = true
        settings.displayZoomControls = false
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            settings.isAlgorithmicDarkeningAllowed = true
        }
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        settings.loadWithOverviewMode = true
        settings.userAgentString = userAgent
        settings.setSupportMultipleWindows(true)
        scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
        setOnKeyListener { _, _, event ->
            onUnhandledKey?.invoke(this, event) ?: false
        }
        setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_DOWN -> {
                    if (!v.hasFocus()) {
                        v.requestFocus()
                        true
                    } else false
                }

                else -> false
            }
        }
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection =
        BaseInputConnection(this, false)

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return if (event.action === KeyEvent.ACTION_DOWN) when (event.keyCode) {
            KeyEvent.KEYCODE_SPACE -> {
                true
            }

            KeyEvent.KEYCODE_ENTER -> {
                true
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
                requestFocus(View.FOCUS_UP)
                true
            }

            KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP -> {
                requestFocus(View.FOCUS_UP)
                true
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                requestFocus(View.FOCUS_DOWN)
                true
            }

            KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN -> {
                requestFocus(View.FOCUS_DOWN)
                true
            }

            else -> super.dispatchKeyEvent(event)
        }
        else super.dispatchKeyEvent(event)
    }


    private fun postFinishPageLoading() {
        launchInMain {
            scrapeScope.collectLatest { scope ->
                currentScrapeTask?.response?.invoke(scope)
                currentScrapeTask = null
            }
            startScraping()
        }
    }

    private suspend inline fun <T> suspendCoroutineWithTimeout(
        timeout: Long,
        crossinline block: (Continuation<T>) -> Unit
    ) = withTimeout(timeout) {
        suspendCancellableCoroutine(block = block)
    }

    private suspend inline fun evaluateJavascript(
        query: String,
        timeout: Long = maxScriptDelay
    ): String = suspendCoroutineWithTimeout(timeout) { continuation ->
        evaluateJavascript(query) { result ->
            continuation.resume(result)
        }
    }

    enum class IdentifierType { CLASS, NAME, XPATH }

    suspend fun getHtmlContent(): String = evaluateJavascript(
        "(function(){ return document.getElementsByTagName('html')[0].outerHTML; })();"
    ).cleanHtml()

    // todo improve
//    suspend fun getAllLinks(): String = evaluateJavascript(
//        "(function(){ return document.getElementsByTagName('A'); })();"
//    )

    fun scrape(
        url: ScrapeLink,
        response: suspend ScrapeScope.() -> Unit
    ): Job = launchInMain {
        scrapeTasks.add(ScrapeTask(url, response))
        startScraping()
    }

    suspend fun scrape(scrapeTask: ScrapeTask) {
        if (currentScrapeTask != null) {
            scrapeTasks.add(scrapeTask)
        } else {
            currentScrapeTask = scrapeTask
            try {
                currentScrapeTask?.url?.let { url ->
                    load(url.url)
                }
            } catch (e: Exception) {
                onError(e)
                currentScrapeTask?.let { task ->
                    scrapeTasks.add(task)
                }
                currentScrapeTask = null
                startScraping()
            }
        }
    }

    suspend fun load(
        url: String,
        additionalHttpHeaders: Map<String, String>? = null
    ) {
        if (additionalHttpHeaders != null) {
            loadUrl(url, additionalHttpHeaders)
        } else {
            loadUrl(url)
        }
        evaluateJavascript("(function(){while (document.readyState !== 'complete') {} })();")
    }

    private suspend fun startScraping() {
        if (scrapeTasks.isNotEmpty()) {
            webViewClient.resources.clear()
            webViewClient.responses.clear()
            scrape(scrapeTasks.removeAt(0))
        }
    }

    class ScrapeTask(
        val url: ScrapeLink,
        val response: suspend ScrapeScope.() -> Unit
    ) {
        override fun toString(): String = url.toString()
    }


    companion object {

//        fun String.ensureSecureValueString(): String = this
//            .replace("""[\t\r\n]""".toRegex(), "")
//            .replace("\"", "&quot;")
//            .replace("&", "&amp;")
//            .trim()

//        fun String.ensureSecureAttributeString(): String = this
//            .replace("""[^a-zA-Z]""".toRegex(), "")

//        fun constructJavascriptIdentifier(
//            identifierType: IdentifierType,
//            identifier: String
//        ): String = when (identifierType) {
//            IdentifierType.CLASS -> "document.getElementsByClassName(\"${
//                identifier.ensureSecureValueString()
//            }\")"
//
//            IdentifierType.NAME -> "document.getElementsByName(\"${
//                identifier.ensureSecureValueString()
//            }\")"
//
//            IdentifierType.XPATH -> "document.evaluate(\"${
//                identifier.ensureSecureValueString()
//            }\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)"
//        }

        private fun String.cleanHtml(): String { // json decode
            val stripedHtml = if (this.startsWith('"') && this.endsWith('"')) {
                this.substring(1, this.length - 1).trim()
            } else {
                this
            }
            return stripedHtml
                .replace("\\t", "\t")
                .replace("\\r", "\r")
                .replace("\\n", "\n")
                .replace("\\u003C", "<")
                .replace("\\\"", "\"")
                .replace("^\"|\"$", "")
        }

        private fun String.asDocument(url: String) = Jsoup.parse(this, url)

    }

}