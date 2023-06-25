/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.data.Movie

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
@SuppressLint("ModifierParameter")
fun MovieCard(
    movie: Movie? = null,
    modifier: Modifier = Modifier,
    onFocus: () -> Unit = {},
    onClick: (Movie?) -> Unit = {},
) {

    val focusState = remember { mutableStateOf<FocusState?>(null) }
    val isFocused: () -> Boolean = {
        ((focusState.value?.isFocused == true) || (focusState.value?.hasFocus == true))
    }

    CompactCard(
        modifier = modifier
            .widthIn(max = 320.dp)
            .aspectRatio(16f / 9f)
            .onFocusChanged { state ->
                focusState.value = state
            }
            .touchable {
                if (isFocused()) onClick(movie) else onFocus()
            },
        image = {
            ImageAny(
                src = movie?.cardImageUrl,
                contentDescription = movie?.description,
                contentScale = ContentScale.Crop,
                placeholder = R.drawable.placeholder
            )
        },
        title = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = movie?.title
            )
        },
        subtitle = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = movie?.studio
            )
        },
        description = {
        },
        onClick = {
            onClick(movie)
        },
        glow = CardDefaults.glow(
            glow = Glow.None,
            focusedGlow = Glow(
                elevationColor = Color.Green,
                elevation = 10.dp
            ),
            pressedGlow = Glow.None
        )
    )

}