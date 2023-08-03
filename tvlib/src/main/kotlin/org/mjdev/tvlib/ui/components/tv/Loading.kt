/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun Loading(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    shape: Shape = RectangleShape,
    text: Any? = R.string.msg_loading,
    textColor: Color = Color.White,
    textSize: TextUnit = 20.sp,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor, shape),
        contentAlignment = contentAlignment
    ) {
        TextAny(
            text = text,
            color = textColor,
            fontSize = textSize,
            style = textStyle
        )
    }
}