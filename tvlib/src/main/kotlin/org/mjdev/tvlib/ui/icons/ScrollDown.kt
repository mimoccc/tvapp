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

val Icons.Filled.ScrollDown: ImageVector
    get() {
        if (_scroll_down != null) {
            return _scroll_down!!
        }
        _scroll_down = Builder(
            name = "ScrollDown",
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
                moveTo(10.6529f, 17.9989f)
                curveTo(11.3788f, 18.7853f, 12.6212f, 18.7853f, 13.3471f, 17.9989f)
                lineTo(20.1598f, 10.6185f)
                curveTo(21.2439f, 9.4441f, 20.4109f, 7.5417f, 18.8127f, 7.5417f)
                lineTo(5.1873f, 7.5417f)
                curveTo(3.5891f, 7.5417f, 2.7561f, 9.4441f, 3.8402f, 10.6185f)
                lineTo(10.6529f, 17.9989f)
                close()
            }
        }.build()
        return _scroll_down!!
    }

private var _scroll_down: ImageVector? = null
