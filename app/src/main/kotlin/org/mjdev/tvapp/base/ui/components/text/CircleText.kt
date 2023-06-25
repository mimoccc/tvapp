/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.text

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.base.ui.components.image.ImageAny

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun CircleText(
    modifier: Modifier = Modifier.defaultMinSize(40.dp),
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    text: String = "0",
    textColor: Color = Color.White,
    textSize: TextUnit = 40.sp
) {

    Box(
        modifier = modifier
            .defaultMinSize(40.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {

        ImageAny(
            modifier = modifier
                .clip(CircleShape),
            src = ColorDrawable(backGroundColor.toArgb()),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        ImageAny(
            modifier = modifier
                .clip(CircleShape).apply {
                    if (borderSize > 0.dp) {
                        border(borderSize, borderColor, CircleShape)
                    }
                },
            src = ColorDrawable(0),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        TextAny(
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = textColor,
            text = text
        )

    }
}