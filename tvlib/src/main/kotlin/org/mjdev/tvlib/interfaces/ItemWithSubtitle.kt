/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithSubtitle<T> {
    val subtitle: T?

    companion object {
        val <T> ItemWithSubtitle<T>.hasSubtitle get() = subtitle?.toString()?.isNotEmpty() == true
    }
}