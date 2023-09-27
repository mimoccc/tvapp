package org.mjdev.tvlib.webclient

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.*
import org.mjdev.tvlib.webclient.html.HtmlPage
import org.mjdev.tvlib.webclient.javascript.JSI

@Suppress("unused")
class WebClient constructor(
    context: Context,
) : WebView(context) {

    val js: JSI get() = webViewClient.jsi

    val webViewClient: CustomWebViewClient by lazy {
        object : CustomWebViewClient(this) {
            override fun onGotHtml(htmlPage: HtmlPage, loadedResources: List<String>) {
                this@WebClient.htmlPage = htmlPage
                if (htmlPage.url != "about:blank") {
                    listener?.invoke(
                        this@WebClient,
                        htmlPage,
                        loadedResources
                    )
                }
            }
        }
    }

    private val webChromeClient: CustomWebChromeClient by lazy {
        CustomWebChromeClient(this)
    }

    val pageIcon: Bitmap? get() = webChromeClient.icon

    val pageTitle: String? get() = webChromeClient.title

    var htmlPage: HtmlPage? = null
        internal set

    var listener: (WebClient.(page: HtmlPage?, resources: List<String>) -> Unit)? = null

    init {
        setWebChromeClient(webChromeClient)
        setWebViewClient(webViewClient)
    }

    fun getPage(url: String, block: WebClient.(page: HtmlPage?, resources: List<String>) -> Unit) {
        this.listener = block
        loadUrl(url)
    }

    fun close() {
        loadUrl("about:blank")
    }

    fun attachTo(viewGroup: ViewGroup?, layoutParams: ViewGroup.LayoutParams): WebClient {
        (parent as ViewGroup?)?.removeView(this)
        viewGroup?.addView(this, layoutParams)
        return this
    }

    companion object {
        const val USER_AGENT =
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:89.0) Gecko/20100101 Firefox/89.0"
    }

}