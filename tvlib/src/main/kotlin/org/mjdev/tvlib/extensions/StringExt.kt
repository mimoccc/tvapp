/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.net.Uri

object StringExt {

    fun String.asException() = Exception(this)

    fun String.parseUri(): Uri = Uri.parse(this)

}