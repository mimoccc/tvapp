/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme

@Preview
@Composable
fun TextWithShadow(
    modifier: Modifier = Modifier,
    shadowSize: Dp = 8.dp,
    shadowColor: Color = Color.Green,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.White,
    text: Any? = "test"
) {
    TextAny(
        modifier = modifier.padding(
            shadowSize,
            shadowSize,
            shadowSize,
            shadowSize
        ),
        style = MaterialTheme.typography.bodyMedium.copy(
            shadow = Shadow(
                color = shadowColor,
                offset = Offset(offsetX.value, offsetY.value),
                blurRadius = shadowSize.value
            )
        ),
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color
    )
}