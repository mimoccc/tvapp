/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.os.Handler
import timber.log.Timber

@Suppress("unused")
object GlobalExt {

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