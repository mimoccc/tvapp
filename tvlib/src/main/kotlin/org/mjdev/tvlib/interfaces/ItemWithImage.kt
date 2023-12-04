/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithImage<T> {
    val image: T?

    companion object {
        val <T> ItemWithImage<T>.hasImage get() = !(image?.toString()).isNullOrEmpty()
    }
}