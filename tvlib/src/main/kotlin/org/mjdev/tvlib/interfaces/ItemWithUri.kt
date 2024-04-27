/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

interface ItemWithUri<T> {

    val uri: T?

    companion object {

        val <T> ItemWithUri<T>.hasUri get() = uri?.toString()?.isNotEmpty() == true

    }

}
