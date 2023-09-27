package org.mjdev.tvlib.webclient.cache

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import java.io.File
import java.io.InputStream

@Suppress("unused")
class WebViewCacheInterceptorInst : WebViewRequestInterceptor {

    private var interceptor: WebViewRequestInterceptor? = null

    fun init(builder: WebViewCacheInterceptor.Builder?) {
        if (builder != null) interceptor = builder.build()
    }

    override fun interceptRequest(request: WebResourceRequest?): WebResourceResponse? {
        return interceptor?.interceptRequest(request)
    }

    override fun interceptRequest(url: String?): WebResourceResponse? {
        return interceptor?.interceptRequest(url)
    }

    override fun loadUrl(webView: WebView?, url: String?) {
        interceptor?.loadUrl(webView, url)
    }

    override fun loadUrl(url: String?, userAgent: String?) {
        interceptor?.loadUrl(url, userAgent)
    }

    override fun loadUrl(
        url: String?,
        additionalHttpHeaders: Map<String?, String?>?,
        userAgent: String?
    ) {
        interceptor?.loadUrl(url, additionalHttpHeaders, userAgent)
    }

    override fun loadUrl(
        webView: WebView?,
        url: String?,
        additionalHttpHeaders: Map<String?, String?>?
    ) {
        interceptor?.loadUrl(webView, url, additionalHttpHeaders)
    }

    override fun clearCache() {
        interceptor?.clearCache()
    }

    override fun enableForce(force: Boolean) {
        interceptor?.enableForce(force)
    }

    override fun getCacheFile(url: String?): InputStream? {
        return interceptor?.getCacheFile(url)
    }

    override fun initAssetsData() {
        AssetsLoader.instance.initData()
    }

    override val cachePath: File?
        get() = interceptor?.cachePath

    companion object {

        private var webViewCacheInterceptorInst: WebViewCacheInterceptorInst? = null

        val instance: WebViewCacheInterceptorInst?
            get() {
                if (webViewCacheInterceptorInst == null) {
                    synchronized(WebViewCacheInterceptorInst::class.java) {
                        if (webViewCacheInterceptorInst == null) {
                            webViewCacheInterceptorInst = WebViewCacheInterceptorInst()
                        }
                    }
                }
                return webViewCacheInterceptorInst
            }
    }

}