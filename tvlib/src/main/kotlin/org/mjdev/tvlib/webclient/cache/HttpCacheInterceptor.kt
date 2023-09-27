package org.mjdev.tvlib.webclient.cache

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HttpCacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val cache = request.header(WebViewCacheInterceptor.KEY_CACHE)
        val originResponse: Response = chain.proceed(request)
        return if (!TextUtils.isEmpty(cache) && cache == CacheType.NORMAL.ordinal.toString() + "")
            originResponse
        else originResponse.newBuilder()
            .removeHeader("pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", "max-age=3153600000")
            .build()
    }

}