/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.badge

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.text.CircleText

@Preview
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    count: Int = 0,
    backGroundColor: Color = Color.DarkGray,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    textColor: Color = Color.White,
    textSize: TextUnit = 20.sp,
    contentPadding: Dp = 2.dp,
    onClick: () -> Unit = {}
) {
    FocusableBox(
        modifier = modifier.clip(CircleShape),
        shape = shape,
        onClick = onClick
    ) {
        CircleText(
            modifier = modifier.clip(CircleShape),
            contentPadding = contentPadding + borderSize,
            backGroundColor = backGroundColor,
            text = count.toString(),
            textSize = textSize,
            textColor = textColor,
            borderColor = borderColor,
            borderSize = borderSize
        )
    }
}