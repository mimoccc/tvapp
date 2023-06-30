/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.ui.components.card.CarouselCard
import org.mjdev.tvapp.data.Movie

// todo swipe left and swipe right
@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Movie> = emptyList(),
    height: Dp = 260.dp,
    onItemSelected: (movie: Movie?) -> Unit = {},
    onItemClicked: (movie: Movie?) -> Unit = {},
) {

    val itemIndex = remember { mutableIntStateOf(0) }
    val selectedMovie: () -> Movie? = { items[itemIndex.value] }

    Carousel(
        itemCount = items.size,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) { indexOfCarouselItem ->

        itemIndex.value = indexOfCarouselItem

        CarouselCard(
            modifier = modifier
                .fillMaxWidth()
                .height(height),
            contentScale = ContentScale.Crop,
            scale = CardScale.None,
            item = items[indexOfCarouselItem],
            onClick = {
                onItemClicked(selectedMovie())
            }
        )

    }

}