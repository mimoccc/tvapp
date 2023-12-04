/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.MalformedURLException
import java.net.URL
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
object GlobalExt {

    val UI = Dispatchers.Main
    val IO = Dispatchers.IO

    fun launch(
        context: CoroutineContext = IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = CoroutineScope(context).launch(
        context = context,
        start = start,
        block = block
    )

    fun <T> T.postDelayed(
        delay: Long,
        block: T.() -> Unit
    ) = Handler(Looper.myLooper() ?: Looper.getMainLooper()).postDelayed({
        block.invoke(this)
    }, delay)

    class CodeException(
        message: String = "Unhandled Exception.",
        code: Int = -1,
    ) : Exception(String.format("$code : $message."))

    fun MutableState<Boolean>.toggle() {
        value = !value
    }

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

    @Suppress("SENSELESS_COMPARISON")
    val String.isUrl: Boolean
        get() = try {
            URL(this) != null
        } catch (e: MalformedURLException) {
            false
        }
}