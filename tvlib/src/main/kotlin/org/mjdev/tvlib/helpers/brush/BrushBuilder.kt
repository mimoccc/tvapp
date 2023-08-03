/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.helpers.brush

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class BrushBuilder {

    private var type: Int = 1
    private var colorStops: Array<out Pair<Float, Color>> = emptyArray()
    private var start: Float = 0f
    private var end: Float = Float.POSITIVE_INFINITY
    private var tileMode: TileMode = TileMode.Clamp

    fun build(): Brush? = when (type) {
        Vertical -> {
            Brush.verticalGradient(
                colorStops = colorStops,
                startY = start,
                endY = end,
                tileMode = tileMode
            )
        }

        Horizontal -> {
            Brush.horizontalGradient(
                colorStops = colorStops,
                startX = start,
                endX = end,
                tileMode = tileMode
            )
        }

        else -> null
    }

    fun type(type: Int): BrushBuilder = apply {
        this.type = type
    }

    fun colors(vararg colors: Pair<Float, Color>) = apply {
        this.colorStops = colors
    }

    fun start(start: Float): BrushBuilder = apply {
        this.start = start
    }

    fun end(end: Float): BrushBuilder = apply {
        this.end = end
    }

    companion object {

        const val Vertical = 1
        const val Horizontal = 2

    }

}

data class GradientOffset(val start: Offset, val end: Offset)

enum class GradientAngle {
    CW0, CW45, CW90, CW135, CW180, CW225, CW270, CW315
}

fun GradientOffset(angle: GradientAngle = GradientAngle.CW0): GradientOffset {
    return when (angle) {
        GradientAngle.CW45 -> GradientOffset(
            start = Offset.Zero,
            end = Offset.Infinite
        )

        GradientAngle.CW90 -> GradientOffset(
            start = Offset.Zero,
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        GradientAngle.CW135 -> GradientOffset(
            start = Offset(Float.POSITIVE_INFINITY, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        GradientAngle.CW180 -> GradientOffset(
            start = Offset(Float.POSITIVE_INFINITY, 0f),
            end = Offset.Zero,
        )

        GradientAngle.CW225 -> GradientOffset(
            start = Offset.Infinite,
            end = Offset.Zero
        )

        GradientAngle.CW270 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset.Zero
        )

        GradientAngle.CW315 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )

        else -> GradientOffset(
            start = Offset.Zero,
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
}

fun gradientBrushLinear(
    vararg colors: Int
) = Brush.linearGradient(
    colors = colors.map { c -> Color(c) }.toList(),
    start = Offset(Float.POSITIVE_INFINITY, 0f),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)

fun Modifier.angledGradientBackground(
    colorStops: Array<Pair<Float, Color>>,
    degrees: Float
) = this.then(
    drawBehind {
        val (x, y) = size
        val degreesNormalised = (degrees % 360).let { if (it < 0) it + 360 else it }
        val angleN = 90 - (degreesNormalised % 90)
        val angleNRad = Math.toRadians(angleN.toDouble())
        val hypot1 = abs((y * cos(angleNRad)))
        val x1 = (abs((hypot1 * sin(angleNRad)))).toFloat()
        val y1 = (abs((hypot1 * cos(angleNRad)))).toFloat()
        val hypot2 = abs((x * cos(angleNRad)))
        val x2 = (abs((hypot2 * cos(angleNRad)))).toFloat()
        val y2 = (abs((hypot2 * sin(angleNRad)))).toFloat()
        val offset = when {
            degreesNormalised > 0f && degreesNormalised < 90f -> arrayOf(
                0f - x1, y - y1,
                x - x2, y + y2
            )

            degreesNormalised == 90f -> arrayOf(0f, 0f, 0f, y)
            degreesNormalised > 90f && degreesNormalised < 180f -> arrayOf(
                0f + x2, 0f - y2,
                0f - x1, y - y1
            )

            degreesNormalised == 180f -> arrayOf(x, 0f, 0f, 0f)
            degreesNormalised > 180f && degreesNormalised < 270f -> arrayOf(
                x + x1, 0f + y1,
                0f + x2, 0f - y2
            )

            degreesNormalised == 270f -> arrayOf(x, y, x, 0f)
            degreesNormalised > 270f && degreesNormalised < 360f -> arrayOf(
                x - x2, y + y2,
                x + x1, 0f + y1
            )

            else -> arrayOf(0f, y, x, y)
        }
        drawRect(
            brush = Brush.linearGradient(
                colorStops = colorStops,
                start = Offset(offset[0], offset[1]),
                end = Offset(offset[2], offset[3])
            ),
            size = size
        )
    }
)