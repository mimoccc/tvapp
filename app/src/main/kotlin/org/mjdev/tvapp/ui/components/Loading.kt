/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import org.mjdev.tvapp.base.annotations.TvPreview

@TvPreview
@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(
            Color.DarkGray,
            RectangleShape
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            color = Color.White,
            fontSize=20.sp,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}