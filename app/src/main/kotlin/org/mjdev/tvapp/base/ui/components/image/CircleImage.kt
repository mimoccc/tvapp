/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.image

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvapp.R

@SuppressLint("ModifierParameter")
@Preview
@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    backGroundColor: Color = Color.Black,
    borderColor: Color = Color.Transparent,
    borderSize: Dp = 0.dp,
    contentDescription: String = "",
    contentPadding: Dp = 2.dp,
    src: Any? = R.drawable.milanj
) {

    Box(
        modifier = modifier.padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {

        ImageAny(
            modifier = modifier
                .clip(CircleShape)
                .background(backGroundColor, CircleShape)
                .border(borderSize, borderColor, CircleShape),
            src = src,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )

    }

}