package org.mjdev.tvlib.webclient

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.webkit.*
import org.mjdev.tvlib.webclient.html.HtmlPage
import org.mjdev.tvlib.webclient.javascript.JSI

@SuppressLint("ViewConstructor")
@Suppress("unused", "MemberVisibilityCanBePrivate")
class WebClient constructor(
    context: Context,
    val block: (page: HtmlPage?, resources: List<String>) -> Unit
) : WebView(context) {
    val webChromeClient: CustomWebChromeClient by lazy {
        CustomWebChromeClient(this)
    }
    val webViewClient: CustomWebViewClient by lazy {
        CustomWebViewClient(this) { page, res ->
            onGotHtml(page, res)
        }
    }
    val js: JSI get() = webViewClient.jsi

    init {
        setWebChromeClient(webChromeClient)
        setWebViewClient(webViewClient)
    }

    fun onGotHtml(htmlPage: HtmlPage, loadedResources: List<String>) {
        if (htmlPage.url != "about:blank") {
            block(htmlPage, loadedResources)
        }
    }

    fun getPage(url: String) {
        loadUrl(url)
    }

    fun close() {
        loadUrl("about:blank")
    }

    fun attachViewTo(
        viewGroup: ViewGroup?,
        layoutParams: ViewGroup.LayoutParams
    ): WebClient {
        detachView()
        viewGroup?.addView(this, layoutParams)
        return this
    }

    fun detachView() {
        (parent as ViewGroup?)?.removeView(this)
    }

    companion object {
        const val USER_AGENT =
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:89.0) Gecko/20100101 Firefox/89.0"
    }

}