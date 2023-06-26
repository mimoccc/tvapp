/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.ui.components.FocusableCard

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
@SuppressLint("ModifierParameter")
fun CarouselCard(
    movie: Movie? = null,
    modifier: Modifier = Modifier,
    onFocus: (movie: Movie?) -> Unit = {},
    onClick: (movie: Movie?) -> Unit = {},
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardScale.None,
) {

    FocusableCard(
        movie = movie,
        scale = CardScale.None,
        modifier = modifier,
        onFocus = {
            onFocus(movie)
        },
        onClick = {
            onClick(movie)
        },
        shape = CardDefaults.shape(),
        colors = CardDefaults.colors(),
        border = CardDefaults.border(),
        glow = CardDefaults.glow(),
        interactionSource = remember { MutableInteractionSource() },
    )

}