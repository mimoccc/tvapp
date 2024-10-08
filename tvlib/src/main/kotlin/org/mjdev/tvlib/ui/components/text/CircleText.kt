/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.text

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ModifierExt.tvAspectRatio

@SuppressLint("ModifierParameter")
@Previews
@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    text: String = "0",
    textColor: Color = Color.White,
    textSize: TextUnit = 20.sp,
    contentPadding: Dp = 0.dp,
) {
    val isPortrait = isPortraitMode()
    Box(
        modifier = modifier
            .size(textSize.value.dp * 2)
            .padding(contentPadding)
            .tvAspectRatio(1f, !isPortrait)
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