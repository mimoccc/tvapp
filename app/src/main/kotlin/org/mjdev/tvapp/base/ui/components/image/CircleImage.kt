/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.image

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.R

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.Black,
    borderSize: Dp = 2.dp,
    contentDescription: String = "",
    src: Any? = R.drawable.milanj
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        ImageAny(
            modifier = modifier
                .padding(borderSize)
                .clip(CircleShape),
            src = ColorDrawable(backGroundColor.toArgb()),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )

        ImageAny(
            modifier = modifier
                .clip(CircleShape)
                .apply {
                    if (borderSize > 0.dp) {
                        border(borderSize, borderColor, CircleShape)
                    }
                },
            src = src,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )

    }

}