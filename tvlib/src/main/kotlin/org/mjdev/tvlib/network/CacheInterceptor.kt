/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Cache interceptor.
 * Made to cache result from internet.
 * Basically for an one hour, to avoid multiple calls.
 *
 * @constructor Create [CacheInterceptor]
 * @property maxAge
 * @property timeUnit
 */
class CacheInterceptor(
    private val maxAge: Int = 1,
    private val timeUnit: TimeUnit = TimeUnit.HOURS
) : Interceptor {

    /**
     * Intercept network calls.
     *
     * @param chain Chain
     * @return [Response]
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(maxAge, timeUnit)
            .build()
        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

}