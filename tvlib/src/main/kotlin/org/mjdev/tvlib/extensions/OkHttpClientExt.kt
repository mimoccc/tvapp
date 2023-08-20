/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.extensions

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object OkHttpClientExt {

    fun OkHttpClient.download(
        url: String,
        onResult: (Result<ByteArray>) -> Unit
    ) {
        Request.Builder().url(url).build().also { request ->
            newCall(request).enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: okio.IOException) {
                        onResult.invoke(Result.failure(e))
                    }

                    override fun onResponse(call: Call, response: Response) {
                        onResult.invoke(Result.success(response.body.bytes()))
                    }
                }
            )
        }
    }

//    fun OkHttpClient.download(
//        url: String,
//        onResult: (Result<String>) -> Unit
//    ) {
//        Request.Builder().url(url).build().also { request ->
//            newCall(request).enqueue(
//                object : Callback {
//                    override fun onFailure(call: Call, e: okio.IOException) {
//                        onResult.invoke(Result.failure(e))
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        onResult.invoke(Result.success(response.body.string()))
//                    }
//                }
//            )
//        }
//    }

}