package org.mjdev.tvlib.webclient

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import org.mjdev.tvlib.webclient.html.HtmlPage
import org.mjdev.tvlib.webclient.javascript.JSI
import org.mjdev.tvlib.webclient.cache.WebViewCacheInterceptorInst
import timber.log.Timber

open class CustomWebViewClient(
    private val webView: WebClient,
    private val onGotHtml: (htmlPage: HtmlPage, loadedResources: List<String>) -> Unit,
) : WebViewClient() {
    val loadedResources = mutableListOf<String>()
    val jsi: JSI by lazy { JSI(webView, onGotHtml) }

    @Synchronized
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val uri = request?.url?.toString()
        uri?.let {
            if (!loadedResources.contains(it)) {
                loadedResources.add(it)
            }
        }
        Timber.d("Loading url: $uri")
        return WebViewCacheInterceptorInst.instance?.interceptRequest(request)
    }

    @Synchronized
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        url?.let {
            if (!loadedResources.contains(it)) {
                loadedResources.add(it)
            }
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
    }

    @SuppressLint("NewApi")
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        Timber.e("Error received: (${error?.errorCode}) ${error?.description}")
    }

    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceResponse?
    ) {
        Timber.e("Error received: (${error?.statusCode}) ${error?.reasonPhrase}")
    }

    override fun onReceivedLoginRequest(
        view: WebView?,
        realm: String?,
        account: String?,
        args: String?
    ) {
        // todo login screen
        Timber.e("Login request. Unsupported yet.")
    }

    override fun onReceivedSslError(
        view: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        super.onReceivedSslError(view, handler, error)
        Timber.e("SSL error: %s", error.toString())
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        super.onUnhandledKeyEvent(view, event)
    }

    @Deprecated("Deprecated in Java")
    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        Timber.e("Error at $failingUrl:")
        Timber.e("$errorCode : $description")
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("Timber.e(\"Too many redirects.\")", "timber.log.Timber")
    )
    override fun onTooManyRedirects(
        view: WebView?,
        cancelMsg: Message?,
        continueMsg: Message?
    ) {
        Timber.e("Too many redirects.")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (!url.contentEquals("about:blank")) {
            jsi.getHtml()
        }
    }

}