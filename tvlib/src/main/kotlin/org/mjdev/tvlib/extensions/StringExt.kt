/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.net.Uri

@Suppress("unused")
object StringExt {

    fun String.asException() = Exception(this)

    fun String.parseUri(): Uri = Uri.parse(this)

    fun List<String>.removeEmptyLines(): List<String> =
        filter { line -> line.isNotEmpty() }

    fun List<String>.removeComments(
        vararg startTagChars: Char
    ): List<String> =
        filter { line -> (!startTagChars.contains(line[0])) }

    fun List<String>.trimLines() = map { line -> line.trim() }

}