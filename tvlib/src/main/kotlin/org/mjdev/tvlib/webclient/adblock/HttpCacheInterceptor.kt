/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webclient.adblock

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.mjdev.tvlib.webclient.cache.CacheType
import org.mjdev.tvlib.webclient.cache.WebViewCacheInterceptor
import java.io.IOException

class AdBlockInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val reqUrl = request.url
        val adBlock = AdBlock.instance
        return if (adBlock.isBlocked(reqUrl.toUrl())) {
            Response.Builder().code(404).build()
        } else {
            val cache = request.header(WebViewCacheInterceptor.KEY_CACHE)
            val originResponse: Response = chain.proceed(request)
            if (!TextUtils.isEmpty(cache) && cache == CacheType.NORMAL.ordinal.toString() + "")
                originResponse
            else originResponse.newBuilder()
                .removeHeader("pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "max-age=3153600000")
                .build()
        }
    }

}