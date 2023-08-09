/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.os.Handler
import androidx.compose.runtime.MutableState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@Suppress("unused")
object GlobalExt {

    fun MutableState<Boolean>.toggle() {
        value = !value
    }

    fun <T> ApiResponse<T>.safeFlow(
        error: (code: Int, message: String?) -> Unit = { code, message ->
            Timber.e("Response error : ($code, ${message})")
        }
    ): Flow<T> = onError {
        error(statusCode.code, message())
    }.toFlow()

    inline fun <reified T> ApiResponse<List<T>>.safeGet(
        crossinline error: (code: Int, message: String?) -> Unit = { code, message ->
            Timber.e("Response error : ($code, ${message})")
        },
    ): List<T> = onError {
        error(statusCode.code, message())
    }.getOrNull() ?: emptyList()

    suspend fun <E> runSafe(block: suspend () -> E): Result<E> = try {
        block.invoke().let { result ->
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    @Suppress("DEPRECATION")
    @Throws(Exception::class)
    fun retry(
        retryCnt: Int = 8,
        retryDelay: Long = 500L,
        onError: (error: Throwable) -> Unit = { e -> throw (e) },
        block: () -> Unit
    ) {
        var retryStep = retryCnt
        try {
            block.invoke()
        } catch (e: Throwable) {
            Timber.e(e)
            retryStep -= 1
            if (retryStep > 0) {
                Timber.e("Retrying run again.")
                Handler().postDelayed({
                    retry(retryStep, retryDelay, onError, block)
                }, retryDelay)
            } else {
                onError(e)
            }
        }
    }

}