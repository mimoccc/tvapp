/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.flow.Flow
import org.mjdev.tvlib.exception.CodeException
import timber.log.Timber

@Suppress("unused")
object ApiResponseExt {

    fun <T> ApiResponse<T>.safeFlow(
        error: (exception: Exception) -> Unit = { exception ->
            Timber.e(exception)
        },
        onError: (error: ApiResponse.Failure.Error) -> Unit = { e ->
            error(CodeException(e.message()))
        }
    ): Flow<T> = onException {
        error(this)
    }.onError {
        onError(this)
    }.toFlow()

    inline fun <reified T> ApiResponse<List<T>>.safeGet(
        crossinline error: (exception: Exception) -> Unit = { exception ->
            Timber.e(exception)
        }
    ): List<T> = onException {
        error(this)
    }.onError {
        error(CodeException(message()))
    }.getOrNull() ?: emptyList()

    suspend fun <E> runSafe(block: suspend () -> E): Result<E> = try {
        block.invoke().let { result ->
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}