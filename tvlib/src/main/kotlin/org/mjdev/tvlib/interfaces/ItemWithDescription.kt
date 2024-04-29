/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithDescription<T> {

    val description: T?

    companion object {

        val <T> ItemWithDescription<T>.hasDescription
            get() = description?.toString()?.isNotEmpty() == true

    }

}
