/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.text

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.base.extensions.ModifierExt.tvAspectRatio

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    text: String = "0",
    textColor: Color = Color.White,
    textSize: TextUnit = 20.sp,
    contentPadding: Dp = 2.dp,
) {

    Box(
        modifier = modifier
            .size(textSize.value.dp * 2)
            .padding(contentPadding)
            .tvAspectRatio(1f)
            .clip(CircleShape)
            .background(backGroundColor, CircleShape)
            .border(
                BorderStroke(
                    borderSize,
                    borderColor
                ),
                CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {

        TextAny(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = textColor,
            text = text,
            fontSize = textSize
        )

    }

}