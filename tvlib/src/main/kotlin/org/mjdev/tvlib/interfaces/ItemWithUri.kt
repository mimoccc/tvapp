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

    val hasUri
        get() = (uri != null) && (uri.toString().isNotEmpty())

}