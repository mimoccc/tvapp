/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.adblock

import android.content.Context
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.mjdev.tvlib.helpers.http.CacheType
import java.io.IOException

@Suppress("unused")
class AdBlockInterceptor(
    private val context: Context,
    private val adBlock: IAdBlocker = NoAdBlock()
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val reqUrl = request.url
        val originResponse: Response = chain.proceed(request)
        return if (adBlock.isBlocked(reqUrl.toUrl()))
            emptyResponse(request)
        else {
            val cache = request.header(CacheType.KEY_CACHE)
            if (!TextUtils.isEmpty(cache) && (cache == CacheType.NORMAL.ordinal.toString() + ""))
                originResponse
            else originResponse.newBuilder()
                .removeHeader("pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "max-age=3153600000")
                .build()
        }
    }

    private fun emptyResponse(request: Request) = Response.Builder()
        .request(request)
        .code(200)
        .message("OK")
        .protocol(Protocol.HTTP_1_1)
        .body("".toResponseBody())
        .build()
}