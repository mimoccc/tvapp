/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ButtonGlow
import androidx.tv.material3.ButtonScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable

@Preview
@Suppress("UNUSED_PARAMETER")
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Button(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    containerColor: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    shape: Shape = RectangleShape,
    glow: ButtonGlow = ButtonDefaults.glow(),
    scale: ButtonScale = ButtonDefaults.scale(),
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {}
) {

    Button(
        modifier = modifier.touchable(),
        glow = glow,
        scale = scale,
        onClick = onClick,
        colors = ButtonDefaults.colors(containerColor = containerColor),
        shape = ButtonDefaults.shape(shape),
        contentPadding = contentPadding,
        content = content
    )

}