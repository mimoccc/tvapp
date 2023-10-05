/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithMessage<T> {
    val message: T?

    companion object {
        val <T> ItemWithMessage<T>.hasMessage get() = message?.toString()?.isNotEmpty() == true
    }
}