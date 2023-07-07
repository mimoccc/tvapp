/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.base.ui.theme.LocalThemeProvider
import org.mjdev.tvapp.data.Movie

@SuppressLint("ModifierParameter")
@TvPreview
@Composable
fun Details(
    movie: Movie? = null,
    modifier: Modifier = Modifier,
) {
    val themeProvider = LocalThemeProvider.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        ImageAny(
            src = movie?.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(themeProvider.detailTextBackgroundColor)
                .padding(
                    vertical = themeProvider.detailTextPaddingVertical,
                    horizontal = themeProvider.detailTextPaddingHorizontal
                )
        ) {
            TextAny(
                text = movie?.title ?: "-",
                style = themeProvider.detailsTitleTextStyle,
                color = themeProvider.detailsTextColor
            )
            TextAny(
                text = movie?.studio ?: "-",
                style = themeProvider.detailsTextStyle,
                color = themeProvider.detailsTextColor
            )
            TextAny(
                text = movie?.title ?: "-",
                style = themeProvider.detailsTextStyle,
                color = themeProvider.detailsTextColor
            )
        }
    }
}