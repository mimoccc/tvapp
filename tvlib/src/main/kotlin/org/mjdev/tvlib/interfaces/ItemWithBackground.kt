/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithBackground<T> {

    val background: T?

    companion object {

        val <T> ItemWithBackground<T>.hasBackground
            get() = background?.toString()?.isNotEmpty() == true

    }

}
