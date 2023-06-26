/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.ui.components.FocusableCard

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
@SuppressLint("ModifierParameter")
fun MovieCard(
    movie: Movie? = null,
    modifier: Modifier = Modifier,
    onFocus: (movie: Movie?) -> Unit = {},
    onClick: (movie: Movie?) -> Unit = {},
) {

    FocusableCard(
        modifier = modifier
            .widthIn(max = 320.dp)
            .aspectRatio(16f / 9f),
        movie = movie,
        onFocus = {
            onFocus(movie)
        },
        onClick = {
            onClick(movie)
        }
    )

}