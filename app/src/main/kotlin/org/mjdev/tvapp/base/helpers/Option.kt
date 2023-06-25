/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers

sealed class Option<out T> {

    object None : Option<Nothing>()

    data class Some<T>(val value: T) : Option<T>()

    companion object {

        fun <T> Option<T>.unwrapOr(defaultValue: T): T {
            return (this as? Some)?.value ?: defaultValue
        }

    }

}