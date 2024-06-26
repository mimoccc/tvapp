/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemWithDate {

    var date: String?

    companion object {

        val ItemWithDate.hasDate get() = date?.isNotEmpty() == true

    }

}
