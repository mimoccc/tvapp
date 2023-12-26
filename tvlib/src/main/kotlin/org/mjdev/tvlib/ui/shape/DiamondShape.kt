/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

@Suppress("unused")
class DiamondShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawDiamondPath(size)
        )
    }

    private fun drawDiamondPath(size: Size): Path {
        return Path().apply {
            val centerX = size.width / 2f
            val diamondCurve = 60f
            val width = size.width
            val height = size.height

            moveTo(x = 0f + diamondCurve, y = 0f)
            lineTo(x = width - diamondCurve, y = 0f)
            lineTo(x = width, y = diamondCurve)
            lineTo(x = centerX, y = height)
            // ...
        }
    }

}