package org.mjdev.tvlib.webclient.cache

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import java.io.File
import java.io.InputStream

interface WebViewRequestInterceptor {

    val cachePath: File?

    fun interceptRequest(request: WebResourceRequest?): WebResourceResponse?

    fun interceptRequest(url: String?): WebResourceResponse?

    fun clearCache()

    fun enableForce(force: Boolean)

    fun getCacheFile(url: String?): InputStream?

    fun initAssetsData()

    fun loadUrl(webView: WebView?, url: String?)

    fun loadUrl(url: String?, userAgent: String?)

    fun loadUrl(url: String?, additionalHttpHeaders: Map<String?, String?>?, userAgent: String?)

    fun loadUrl(webView: WebView?, url: String?, additionalHttpHeaders: Map<String?, String?>?)

}