/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.compose.runtime.MutableState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@Suppress("unused")
object GlobalExt {

    @Suppress("MemberVisibilityCanBePrivate")
    class CodeException(
        val code:Int = -1,
        message:String = String.format("$code : Unhandled Exception.")
    ) : Exception(message)

    fun MutableState<Boolean>.toggle() {
        value = !value
    }

    fun <T> ApiResponse<T>.safeFlow(
        error: (exception: Exception) -> Unit = { exception ->
            Timber.e(exception)
        }
    ): Flow<T> = onException {
        error(exception)
    }.onError {
        error(CodeException(statusCode.code, message()))
    }.toFlow()

    inline fun <reified T> ApiResponse<List<T>>.safeGet(
        crossinline error: (exception: Exception) -> Unit = { exception ->
            Timber.e(exception)
        }
    ): List<T> = onException {
        error(exception)
    }.onError {
        error(CodeException(statusCode.code, message()))
    }.getOrNull() ?: emptyList()

    suspend fun <E> runSafe(block: suspend () -> E): Result<E> = try {
        block.invoke().let { result ->
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}