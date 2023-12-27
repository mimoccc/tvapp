/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.fetcher

import android.annotation.SuppressLint
import android.content.Context
import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.Cookie
import com.gargoylesoftware.htmlunit.util.NameValuePair
import org.mjdev.tvlib.webscrapper.base.toCookie
import org.mjdev.tvlib.webscrapper.base.urlOrigin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.helpers.http.NetworkConnectionInterceptor
import org.mjdev.tvlib.webscrapper.adblock.IAdBlocker
import org.mjdev.tvlib.webscrapper.adblock.NoAdBlock
import org.mjdev.tvlib.helpers.http.UserAgentInterceptor
import org.mjdev.tvlib.webscrapper.adblock.AdBlockInterceptor
import org.mjdev.tvlib.webscrapper.base.Method
import org.mjdev.tvlib.webscrapper.base.Request
import org.mjdev.tvlib.webscrapper.base.Result
import java.net.Proxy
import java.net.URL

@Suppress("unused")
class BrowserFetcher(
    private val context: Context,
    private val adBlock: IAdBlocker = NoAdBlock()
) : BlockingFetcher<Request> {
    //    private val httpCache by lazy {
//        Cache(
//            directory = File(
//                context.cacheDir,
//                "http_cache"
//            ),
//            maxSize = 1024L * 1024L * 1024L
//        )
//    }
    private val userAgentInterceptor by lazy { UserAgentInterceptor() }
    private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor(context) }

    //    private val cacheInterceptor by lazy { CacheInterceptor() }
    private val adBlockInterceptor by lazy { AdBlockInterceptor(context, adBlock) }
    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    private val httpClient by lazy {
        OkHttpClient.Builder().apply {
            addNetworkInterceptor(networkConnectionInterceptor)
            addNetworkInterceptor(userAgentInterceptor)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(httpLoggingInterceptor)
            }
            addInterceptor(adBlockInterceptor)
//            addNetworkInterceptor(cacheInterceptor)
//            cache(httpCache)
        }.build()
    }

    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result {
        val client = WebClient(BrowserVersion.BEST_SUPPORTED)
            .withOptions(request)
        val webRequest = WebRequest(
            URL(request.url),
            request.method.toHttpMethod()
        ).apply {
            request.body?.run {
                requestBody = this
            }
        }
        val page: Page = client.getPage(client.currentWindow.topWindow, webRequest)
        val httpResponse = page.webResponse
        val document = when {
            page.isHtmlPage -> (page as HtmlPage).asXml()
            else -> httpResponse.contentAsString
        }
        val headers = httpResponse.responseHeaders.toMap()
        val result = Result(
            responseBody = document,
            responseStatus = httpResponse.toStatus(),
            contentType = httpResponse.contentType,
            headers = headers,
            baseUri = request.url,
            cookies = httpResponse.responseHeaders
                .filter { it.name == "Set-Cookie" }
                .map { it.value.toCookie(request.url.urlOrigin) }
        )
        client.javaScriptEngine.shutdown()
        client.close()
//        client.cache.clear()
        return result
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebClient.withOptions(request: Request) = apply {
        cssErrorHandler = SilentCssErrorHandler()
        ajaxController = NicelyResynchronizingAjaxController()
        createCookies(request)
        addRequestHeader("User-Agent", request.userAgent)
        if (request.authentication != null) {
            addRequestHeader("Authorization", request.authentication?.toHeaderValue())
        }
        request.headers.forEach {
            addRequestHeader(it.key, it.value)
        }
        @Suppress("MagicNumber") waitForBackgroundJavaScript(10_000)
        webConnection = OkHttpWebConnection()
        options.apply {
            timeout = request.timeout
            isRedirectEnabled = request.followRedirects
            maxInMemory = 0
            isUseInsecureSSL = request.sslRelaxed
            isCssEnabled = false
            isPopupBlockerEnabled = true
            isDownloadImages = false
            isThrowExceptionOnScriptError = false
            isThrowExceptionOnFailingStatusCode = false
            isPrintContentOnFailingStatusCode = false
            historySizeLimit = 0
            historyPageCacheLimit = 0
            isJavaScriptEnabled = true
            withProxySettings(request)
            waitForBackgroundJavaScript(1000)
        }
    }

    private fun WebClientOptions.withProxySettings(request: Request): WebClientOptions {
        if (request.proxy != null) {
            proxyConfig = ProxyConfig(
                request.proxy?.host,
                request.proxy?.port ?: 80,
                "http",
                request.proxy?.type == Proxy.Type.SOCKS
            )
        }
        return this
    }

    private fun WebClient.createCookies(
        request: Request
    ) {
        request.cookies.forEach {
            cookieManager.addCookie(createCookie(request.url, it.key, it.value))
        }
    }

    private fun createCookie(
        url: String,
        name: String,
        value: String
    ): Cookie {
        return Cookie(URL(url).host, name, value)
    }

    private fun WebResponse.toStatus() =
        Result.Status(statusCode, statusMessage)

    private inner class OkHttpWebConnection : WebConnection {
        override fun close() {
        }

        override fun getResponse(wr: WebRequest?): WebResponse {
            return httpClient.newCall(
                okhttp3.Request.Builder().url(wr?.url?.toString() ?: "").build()
            ).execute().let { r ->
                WebResponseData(
                    r.body.bytes(),
                    r.code,
                    r.message,
                    r.headers.map { h ->
                        NameValuePair(h.first, h.second)
                    }
                ).let { data ->
                    WebResponse(data, wr, 0L)
                }
            }
        }
    }

    companion object {
        fun render(html: String): String =
            WebClient(BrowserVersion.BEST_SUPPORTED).loadHtmlCodeIntoCurrentWindow(html).asXml()
    }

}

@Suppress("unused")
fun Map<String, String>.asRawCookieSyntax(): String =
    entries.joinToString(";", postfix = ";") { "${it.key}=${it.value}" }

fun List<NameValuePair>.toMap(): Map<String, String> =
    associateBy({ it.name }, { it.value })

fun Method.toHttpMethod(): HttpMethod = when (this) {
    Method.GET -> HttpMethod.GET
    Method.POST -> HttpMethod.POST
    Method.HEAD -> HttpMethod.HEAD
    Method.DELETE -> HttpMethod.DELETE
    Method.PATCH -> HttpMethod.PATCH
    Method.PUT -> HttpMethod.PUT
}
