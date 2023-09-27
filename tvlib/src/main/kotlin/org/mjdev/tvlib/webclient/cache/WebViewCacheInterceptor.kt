package org.mjdev.tvlib.webclient.cache

import android.content.Context
import android.webkit.URLUtil
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.mjdev.tvlib.webclient.adblock.AdBlockInterceptor
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

// todo trust manager
@Suppress("unused")
class WebViewCacheInterceptor(builder: Builder) : WebViewRequestInterceptor {
    override val cachePath: File
    private val dynamicCacheFile: File?
    private val cacheSize: Long
    private val connectTimeout: Long
    private val readTimeout: Long
    private val cacheExtensionConfig: CacheExtensionConfig
    private val context: Context
    private var cacheType: CacheType
    private var assetsDir: String = ""
    private var trustAllHostname = false
    private var sslSocketFactory: SSLSocketFactory? = null
    private var x509TrustManager: X509TrustManager? = null
    private var dns: Dns? = null
    private val resourceInterceptor: ResourceInterceptor?
    private var isSuffixMod = false
    private var httpClient: OkHttpClient? = null
    private var origin: String? = ""
    private var referer: String? = ""
    private var userAgent: String? = ""

    init {
        cacheExtensionConfig = builder.cacheExtensionConfig
        cachePath = builder.cacheFile
        dynamicCacheFile = builder.dynamicCacheFile
        cacheSize = builder.cacheSize
        cacheType = builder.cacheType
        connectTimeout = builder.connectTimeout
        readTimeout = builder.readTimeout
        context = builder.context
        assetsDir = builder.assetsDir
        x509TrustManager = builder.x509TrustManager
        sslSocketFactory = builder.sslSocketFactory
        trustAllHostname = builder.trustAllHostname
        resourceInterceptor = builder.resourceInterceptor
        isSuffixMod = builder.isSuffixMod
        dns = builder.dns
        initHttpClient()
        if (isEnableAssets) initAssetsLoader()
    }

    private val isEnableDynamicCache: Boolean
        get() = dynamicCacheFile != null
    private val isEnableAssets: Boolean
        get() = assetsDir.isNotEmpty()

    private fun initAssetsLoader() {
        AssetsLoader.instance.init(context).setDir(assetsDir).isAssetsSuffixMod(isSuffixMod)
    }

    // todo use application client
    private fun initHttpClient() {
        val cache = Cache(cachePath, cacheSize)
        val builder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpCacheInterceptor())
            .addNetworkInterceptor(AdBlockInterceptor())
//            .addNetworkInterceptor(LogInterceptor())
        if (trustAllHostname) {
            builder.hostnameVerifier { _: String?, _: SSLSession? -> true }
        }
        if (sslSocketFactory != null && x509TrustManager != null) {
            builder.sslSocketFactory(sslSocketFactory!!, x509TrustManager!!)
        }
        if (dns != null) {
            builder.dns(dns!!)
        }
        httpClient = builder.build()
    }

    override fun interceptRequest(request: WebResourceRequest?): WebResourceResponse? {
        val headers = mutableMapOf<String, String?>().apply {
            request?.requestHeaders?.also { putAll(it) }
        }
        return interceptRequest(request?.url?.toString(), headers)
    }

    private fun buildHeaders(): MutableMap<String, String?> {
        val headers: MutableMap<String, String?> = HashMap()
        if (!origin.isNullOrEmpty()) {
            headers["Origin"] = origin
        }
        if (!referer.isNullOrEmpty()) {
            headers["Referer"] = referer
        }
        if (!userAgent.isNullOrEmpty()) {
            headers["User-Agent"] = userAgent
        }
        return headers
    }

    override fun interceptRequest(url: String?): WebResourceResponse? {
        return interceptRequest(url, buildHeaders())
    }

    private fun checkUrl(url: String?): Boolean {
        if (url.isNullOrEmpty()) return false
        if (!url.startsWith("http")) return false
        if (resourceInterceptor != null && !resourceInterceptor.interceptor(url)) return false
        val extension: String = MimeTypeMapUtils.getFileExtensionFromUrl(url)
        if (extension.isEmpty()) return false
        if (cacheExtensionConfig.isMedia(extension)) return false
        return cacheExtensionConfig.canCache(extension)
    }

    override fun loadUrl(webView: WebView?, url: String?) {
        if (!isValidUrl(url)) return
        webView?.loadUrl(url ?: "")
        referer = webView?.url
        origin = NetUtils.getOriginUrl(referer)
        userAgent = webView?.settings?.userAgentString
    }

    override fun loadUrl(url: String?, userAgent: String?) {
        if (!isValidUrl(url)) return
        referer = url
        origin = NetUtils.getOriginUrl(referer)
        this.userAgent = userAgent
    }

    override fun loadUrl(
        url: String?,
        additionalHttpHeaders: Map<String?, String?>?,
        userAgent: String?
    ) {
        if (!isValidUrl(url)) return
        referer = url
        origin = NetUtils.getOriginUrl(referer)
        this.userAgent = userAgent
    }

    override fun loadUrl(
        webView: WebView?,
        url: String?,
        additionalHttpHeaders: Map<String?, String?>?
    ) {
        if (!isValidUrl(url)) return
        val headers = mutableMapOf<String?, String?>().apply {
            additionalHttpHeaders?.also { putAll(it) }
        }
        webView?.loadUrl(url ?: "", headers)
        referer = webView?.url
        origin = NetUtils.getOriginUrl(referer)
        userAgent = webView?.settings?.userAgentString
    }

    override fun clearCache() {
        FileUtil.deleteDirs(cachePath.absolutePath, false)
        AssetsLoader.instance.clear()
    }

    override fun enableForce(force: Boolean) {
        cacheType = if (force) CacheType.FORCE else CacheType.NORMAL
    }

    override fun getCacheFile(url: String?): InputStream? {
        return OKHttpFile.getCacheFile(cachePath, url)
    }

    override fun initAssetsData() {
        AssetsLoader.instance.initData()
    }

    private fun addHeader(reqBuilder: Request.Builder, headers: Map<String, String?>?) {
        if (headers == null) return
        for ((key, value) in headers) {
            reqBuilder.addHeader(key, value ?: "")
        }
    }

    private fun interceptRequest(
        url: String?,
        headers: MutableMap<String, String?>
    ): WebResourceResponse? {
        if (cacheType === CacheType.NORMAL) return null
        if (!checkUrl(url)) return null
        if (isEnableDynamicCache) {
            val file: File? = DynamicCacheLoader.instance.getResByUrl(dynamicCacheFile, url)
            if (file != null) {
                Timber.d(String.format("from dynamic file: %s", url))
                val mimeType: String? = MimeTypeMapUtils.getMimeTypeFromUrl(url)
                var inputStream: InputStream? = null
                try {
                    inputStream = FileInputStream(file)
                } catch (e: FileNotFoundException) {
                    Timber.e(e)
                }
                return WebResourceResponse(mimeType, "", inputStream)
            }
        }
        if (isEnableAssets) {
            val inputStream = AssetsLoader.instance.getResByUrl(url!!)
            if (inputStream != null) {
                Timber.d("from assets: %s", url)
                val mimeType: String? = MimeTypeMapUtils.getMimeTypeFromUrl(url)
                return WebResourceResponse(mimeType, "", inputStream)
            }
        }
        try {
            val reqBuilder: Request.Builder = Request.Builder().url(url ?: "")
            val extension: String = MimeTypeMapUtils.getFileExtensionFromUrl(url)
            if (cacheExtensionConfig.isHtml(extension)) headers[KEY_CACHE] =
                cacheType.ordinal.toString() + ""
            addHeader(reqBuilder, headers)
            if (!NetUtils.isConnected(context)) reqBuilder.cacheControl(CacheControl.FORCE_CACHE)
            val request: Request = reqBuilder.build()
            val response: Response? = httpClient?.newCall(request)?.execute()
            val cacheRes = response?.cacheResponse
            if (cacheRes != null) Timber.d(
                String.format(
                    "from cache: %s",
                    url
                )
            ) else Timber.d(String.format("from server: %s", url))
            val mimeType: String? = MimeTypeMapUtils.getMimeTypeFromUrl(url)
            val webResourceResponse = WebResourceResponse(mimeType, "", response?.body?.byteStream())
            if (response?.code == 504 && !NetUtils.isConnected(context)) return null
            var message = response?.message
            if (message.isNullOrEmpty()) message = "OK"
            try {
                webResourceResponse.setStatusCodeAndReasonPhrase(
                    response?.code ?: -1,
                    message
                )
            } catch (e: Exception) {
                return null
            }
            webResourceResponse.responseHeaders =
                NetUtils.multimapToSingle(response?.headers?.toMultimap() ?: emptyMap())
            return webResourceResponse
        } catch (e: IOException) {
            Timber.e(e)
        }
        return null
    }

    class Builder(val context: Context) {
        var cacheFile: File
        var dynamicCacheFile: File? = null
        var cacheSize = (100 * 1024 * 1024).toLong()
        var connectTimeout: Long = 20
        var readTimeout: Long = 20
        var cacheExtensionConfig: CacheExtensionConfig
        var debug = true
        var cacheType = CacheType.FORCE
        var trustAllHostname = false
        var sslSocketFactory: SSLSocketFactory? = null
        var x509TrustManager: X509TrustManager? = null
        var resourceInterceptor: ResourceInterceptor? = null
        private set
        var assetsDir: String = ""
        var isSuffixMod = false
        var dns: Dns? = null
        private set

        init {
            cacheFile = File(context.cacheDir.toString(), "CacheWebViewCache")
            cacheExtensionConfig = CacheExtensionConfig()
        }

        fun setResourceInterceptor(resourceInterceptor: ResourceInterceptor?) {
            this.resourceInterceptor = resourceInterceptor
        }

        fun setTrustAllHostname(): Builder {
            trustAllHostname = true
            return this
        }

        fun setSSLSocketFactory(
            sslSocketFactory: SSLSocketFactory?,
            trustManager: X509TrustManager?
        ): Builder {
            if (sslSocketFactory != null && trustManager != null) {
                this.sslSocketFactory = sslSocketFactory
                x509TrustManager = trustManager
            }
            return this
        }

        fun setCachePath(file: File?): Builder {
            if (file != null) cacheFile = file
            return this
        }

        fun setDynamicCachePath(file: File?): Builder {
            if (file != null) dynamicCacheFile = file
            return this
        }

        fun setCacheSize(cacheSize: Long): Builder {
            if (cacheSize > 1024) this.cacheSize = cacheSize
            return this
        }

        fun setReadTimeoutSecond(time: Long): Builder {
            if (time >= 0) readTimeout = time
            return this
        }

        fun setConnectTimeoutSecond(time: Long): Builder {
            if (time >= 0) connectTimeout = time
            return this
        }

        fun setCacheExtensionConfig(config: CacheExtensionConfig?): Builder {
            if (config != null) cacheExtensionConfig = config
            return this
        }

        fun setDebug(debug: Boolean): Builder {
            this.debug = debug
            return this
        }

        fun setCacheType(cacheType: CacheType): Builder {
            this.cacheType = cacheType
            return this
        }

        fun isAssetsSuffixMod(suffixMod: Boolean): Builder {
            isSuffixMod = suffixMod
            return this
        }

        fun setAssetsDir(dir: String?): Builder {
            if (dir != null) {
                assetsDir = dir
            }
            return this
        }

        fun setDns(dns: Dns?) {
            this.dns = dns
        }

        fun build(): WebViewRequestInterceptor {
            return WebViewCacheInterceptor(this)
        }
    }

    private fun isValidUrl(url: String?): Boolean {
        return URLUtil.isValidUrl(url)
    }

    companion object {
        const val KEY_CACHE = "WebResourceInterceptor-Key-Cache"
    }

}