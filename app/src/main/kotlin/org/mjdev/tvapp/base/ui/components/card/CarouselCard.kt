/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardScale
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.data.Movie

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
@SuppressLint("ModifierParameter")
fun CarouselCard(
    movie: Movie? = null,
    placeHolder: Int = R.drawable.placeholder,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardScale.None,
    onClick: () -> Unit = {}
) {

    CompactCard(
        modifier = modifier,
        scale = scale,
        image = {
            ImageAny(
                modifier = modifier,
                src = movie?.cardImageUrl,
                contentDescription = movie?.description,
                contentScale = contentScale,
                placeholder = placeHolder,
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
        glow = CardDefaults.glow(
            glow = Glow.None,
            focusedGlow = Glow(
                elevationColor = Color.Green,
                elevation = 10.dp
            ),
            pressedGlow = Glow.None
        ),
        onClick = onClick
    )

}