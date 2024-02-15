/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.http

import okhttp3.Interceptor
import okhttp3.Response
import org.mjdev.tvlib.exception.AuthenticationError
import org.mjdev.tvlib.exception.GenericMessageException
import org.mjdev.tvlib.exception.ServerException
import timber.log.Timber

@Suppress("unused")
class ErrorInterceptor(
    val fromJson: (body: String, cls: Class<BaseResponse>) -> BaseResponse?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            when (response.code) {
                in 401 until 403 -> throw AuthenticationError()
                in 500 until 599 -> throw ServerException()
                else -> {
                }
            }
        } else {
            val genericResponse: BaseResponse = parseError(response) ?: return response
            if (!genericResponse.status) {
                Timber.e("Generic Message thrown with message: ${genericResponse.message}")
                throw GenericMessageException(genericResponse.message)
            }
        }
        return response
    }

    private fun parseError(response: Response): BaseResponse? {
        return try {
            val responseBodyCopy = response.peekBody(Long.MAX_VALUE)
            val bodyString = responseBodyCopy.string()
            fromJson(bodyString, BaseResponse::class.java)
        } catch (e: Exception) {
            return null
        }
    }

    interface BaseResponse {
        val status: Boolean
        val message: String
    }

}
