/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers

sealed class Result<out T> {

    object Empty : Result<Nothing>()

    data class Success<T>(val value: T) : Result<T>()

    companion object {

        fun <T> Result<T>.unwrapOr(defaultValue: T): T {
            return (this as? Success)?.value ?: defaultValue
        }

    }

}