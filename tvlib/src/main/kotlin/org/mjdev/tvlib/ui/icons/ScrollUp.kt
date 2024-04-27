/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("UnusedReceiverParameter", "ObjectPropertyName")

package org.mjdev.tvlib.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filled.ScrollUp: ImageVector
    get() {
        if (_scroll_up != null) {
            return _scroll_up!!
        }
        _scroll_up = Builder(
            name = "ScrollUp",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.3471f, 6.0011f)
                curveTo(12.6212f, 5.2146f, 11.3788f, 5.2146f, 10.6529f, 6.0011f)
                lineTo(3.8402f, 13.3815f)
                curveTo(2.7561f, 14.5559f, 3.5891f, 16.4583f, 5.1873f, 16.4583f)
                lineTo(18.8127f, 16.4583f)
                curveTo(20.4109f, 16.4583f, 21.2439f, 14.5559f, 20.1598f, 13.3815f)
                lineTo(13.3471f, 6.0011f)
                close()
            }
        }.build()
        return _scroll_up!!
    }

private var _scroll_up: ImageVector? = null
