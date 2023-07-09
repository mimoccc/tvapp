/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

object GlobalExt {

    fun <E> runSafe(what: () -> E): Result<E> = try {
        what.invoke().let { result ->
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}