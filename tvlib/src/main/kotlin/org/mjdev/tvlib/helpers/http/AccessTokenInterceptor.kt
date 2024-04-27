/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.http

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.mjdev.tvlib.exception.AuthenticationError
import org.mjdev.tvlib.exception.ServerException
import java.io.IOException
import java.net.HttpURLConnection

@Suppress("unused")
class AccessTokenInterceptor internal constructor(
    private val sessionPreferenceManager: SessionPreferenceManager,
    private val sessionRepository: ISessionRepository
) : Interceptor {

    private val token: String
        get() = sessionPreferenceManager.token

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        val token = sessionPreferenceManager.token
        addAuthHeader(builder)
        request = builder.build()
        val response = chain.proceed(request)
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            synchronized(this) {
                val newToken = sessionPreferenceManager.token
                return if (token != newToken) {
                    chain.proceed(newRequestWithAccessToken(request, newToken))
                } else try {
                    refreshToken()
                    addAuthHeader(builder)
                    request = builder.build()
                    return chain.proceed(request)
                } catch (error: AuthenticationError) {
                    logout()
                    response
                } catch (error: ServerException) {
                    response
                }
            }
        }
        return response
    }

    private fun newRequestWithAccessToken(
        request: Request, accessToken: String
    ): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

    private fun addAuthHeader(builder: Request.Builder) {
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("Authorization", "Bearer $token")
        }
    }

    @Throws(AuthenticationError::class, ServerException::class)
    private fun refreshToken() {
        return sessionRepository.refreshToken()
    }

    private fun logout() {
        sessionRepository.logout()
    }

}
