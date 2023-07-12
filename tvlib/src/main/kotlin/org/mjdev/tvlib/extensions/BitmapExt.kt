/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette

@Suppress("unused")
object BitmapExt {

    fun Bitmap.bimapCopy(): Bitmap = copy(Bitmap.Config.ARGB_8888, false)

    fun Bitmap?.majorColor(
        defaultColor: Color
    ): Color = this?.let { bmp ->
        Palette.from(bmp)
            .generate()
            .let { pal ->
                Color(pal.getDominantColor(defaultColor.toArgb()))
            }
    } ?: defaultColor

}