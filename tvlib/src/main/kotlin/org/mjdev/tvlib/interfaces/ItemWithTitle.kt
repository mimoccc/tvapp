/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithTitle<T> {

    val title: T?

    companion object {

        val <T> ItemWithTitle<T>.hasTitle get() = title?.toString()?.isNotEmpty() == true

    }

}
