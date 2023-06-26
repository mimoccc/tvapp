/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.card.CarouselCard
import org.mjdev.tvapp.data.Movie

// todo swipe left and swipe right
@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Movie> = emptyList(),
    height: Dp = 260.dp,
    onItemSelected: (Movie?) -> Unit = {},
    onItemClicked: (Movie?) -> Unit = {},
) {

    Carousel(
        itemCount = items.size,
        modifier = modifier
            .fillMaxWidth()
//            .pointerInput(Unit) {
//                detectHorizontalDragGestures { change, dragAmount ->
                    // todo
//                }
//            }
            .height(height),
    ) { indexOfCarouselItem ->
        val movie = items[indexOfCarouselItem]

        onItemSelected(movie)

        CarouselCard(
            modifier = modifier.fillMaxWidth().height(height),
            contentScale = ContentScale.Crop,
            scale = CardScale.None,
            movie = movie,
            onClick = { clickedItem ->
                onItemClicked(clickedItem)
            }
        )

    }

}