/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.extensions

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import kotlin.math.roundToInt

object ColorExt {

    fun Color.invert(): Color {
        val a :Int = alpha.roundToInt()
        val r :Int = 255 - red.roundToInt()
        val g :Int = 255 - green.roundToInt()
        val b :Int = 255 - blue.roundToInt()
        val color = ((a and 0xFF) shl 24) or
                ((r and 0xFF) shl 16) or
                ((g and 0xFF) shl 8) or
                (b and 0xFF)
        return Color(color)
    }

    fun contrastAndBrightness(
        @FloatRange(from = 0.0, to = 10.0)
        contrast: Float,
        @FloatRange(from = -255.0, to = 255.0)
        brightness: Float,
    ) = ColorFilter.colorMatrix(
        ColorMatrix(
            floatArrayOf(
                contrast, 0f, 0f, 0f, brightness,
                0f, contrast, 0f, 0f, brightness,
                0f, 0f, contrast, 0f, brightness,
                0f, 0f, 0f, 1f, 0f
            )
        )
    )

    fun bw() = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })

    fun colorInvert() = ColorFilter.colorMatrix(
        ColorMatrix(
            floatArrayOf(
                -1f, 0f, 0f, 0f, 255f,
                0f, -1f, 0f, 0f, 255f,
                0f, 0f, -1f, 0f, 255f,
                0f, 0f, 0f, 1f, 0f
            )
        )
    )

}