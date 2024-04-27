/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("ObjectPropertyName")

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

@Suppress("UnusedReceiverParameter")
val Icons.Filled.Menu: ImageVector
    get() {
        if (_menu != null) {
            return _menu!!
        }
        _menu = Builder(name = "Menu", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(120.0f, 720.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(720.0f)
                verticalLineToRelative(80.0f)
                lineTo(120.0f, 720.0f)
                close()
                moveTo(120.0f, 520.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(720.0f)
                verticalLineToRelative(80.0f)
                lineTo(120.0f, 520.0f)
                close()
                moveTo(120.0f, 320.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(720.0f)
                verticalLineToRelative(80.0f)
                lineTo(120.0f, 320.0f)
                close()
            }
        }
        .build()
        return _menu!!
    }

private var _menu: ImageVector? = null
