/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.RenderProcessGoneDetail
import android.webkit.SafeBrowsingResponse
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import okhttp3.Response
import org.mjdev.tvlib.helpers.http.MimeTypeMapUtils
import org.mjdev.tvlib.webscrapper.adblock.IAdBlocker
import org.mjdev.tvlib.webscrapper.adblock.NoAdBlock
import timber.log.Timber
import java.io.ByteArrayInputStream

class CustomWebViewClient(
    private val loadImages: Boolean = true,
    private val loadScripts: Boolean = true,
    private val loadMedia: Boolean = true,
    private val loadCssStyles: Boolean = true,
    private val loadFonts:Boolean = true,
    private val adBlocker: IAdBlocker = NoAdBlock(),
    private val onError: (Exception) -> Unit = { e ->
        Timber.e(e)
    },
    private val onPageLoadStart: (() -> Unit)? = null,
    private val onPageLoadFinish: (() -> Unit)? = null,
) : WebViewClient() {
    val responses = mutableListOf<Response>()
    val resources = mutableMapOf<String, String>()

    @Deprecated("Deprecated in Java")
    override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
        val mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(url) ?: ""
        return if (adBlocker.isBlocked(url)) EmptyWebResource()
        else when {
            (mimeType.contains("video/") && (!loadMedia)) -> EmptyWebResource()
            (mimeType.contains("audio/") && (!loadMedia)) -> EmptyWebResource()
            (mimeType.contains("image/") && (!loadImages)) -> EmptyWebResource()
            (mimeType.contains("text/javascript") && (!loadScripts)) -> EmptyWebResource()
            (mimeType.contains("application/javascript") && (!loadScripts)) -> EmptyWebResource()
            (mimeType.contains("text/css") && (!loadCssStyles)) -> EmptyWebResource()
            (mimeType.contains("font/") && (!loadFonts)) -> EmptyWebResource()
            else -> null
        }
    }

    class EmptyWebResource : WebResourceResponse(
        "text/plain",
        "utf-8",
        ByteArrayInputStream(ByteArray(0))
    )

    override fun onLoadResource(view: WebView?, url: String?) {
        val mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(url) ?: ""
        url?.let { resource ->
            if (!adBlocker.isBlocked(url)) {
                when {
                    (mimeType.contains("video/") && (!loadMedia)) -> {
                        Timber.e("Mime type blocked: $resource")
                    }

                    (mimeType.contains("audio/") && (!loadMedia)) -> {
                        Timber.e("Mime type blocked: $resource")
                    }

                    (mimeType.contains("image/") && (!loadImages)) -> {
                        Timber.e("Mime type blocked: $resource")
                    }

                    (mimeType.contains("text/javascript") && (!loadScripts)) -> {
                        Timber.e("Mime type blocked: $resource")
                    }

                    (mimeType.contains("text/css") && (!loadCssStyles)) -> {
                        Timber.e("Mime type blocked: $resource")
                    }

                    (mimeType.contains("font/") && (!loadFonts)) ->  {
                        Timber.e("Mime type blocked: $resource")
                    }

                    else -> {
                        resources[resource] = mimeType
                        Timber.e("Loading: $resource")
                    }
                }
            } else {
                Timber.e("Blocked by ad blocker: $resource")
            }
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        onPageLoadStart?.invoke()
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        onError(ReceivedError(error.toString()))
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        onError(ReceivedError(errorResponse?.reasonPhrase))
    }

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        return super.onRenderProcessGone(view, detail)
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        super.onFormResubmission(view, dontResend, resend)
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        super.onReceivedClientCertRequest(view, request)
    }

    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
    }

    override fun onReceivedLoginRequest(
        view: WebView?,
        realm: String?,
        account: String?,
        args: String?
    ) {
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        onError(SSLError(error.toString()))
    }

    override fun onSafeBrowsingHit(
        view: WebView?,
        request: WebResourceRequest?,
        threatType: Int,
        callback: SafeBrowsingResponse?
    ) {
        super.onSafeBrowsingHit(view, request, threatType, callback)
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        super.onUnhandledKeyEvent(view, event)
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "onError(ReceivedError(description))",
            "org.mjdev.tvlib.webscraper.base.ReceivedError"
        )
    )
    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        onError(ReceivedError(description))
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onTooManyRedirects(view, cancelMsg, continueMsg)",
            "android.webkit.WebViewClient"
        )
    )
    @Suppress("DEPRECATION")
    override fun onTooManyRedirects(view: WebView?, cancelMsg: Message?, continueMsg: Message?) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg)
    }

    override fun onPageFinished(view: WebView, url: String) {
        onPageLoadFinish?.invoke()
    }
}
