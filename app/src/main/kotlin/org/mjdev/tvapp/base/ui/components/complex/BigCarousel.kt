/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListScope
import androidx.tv.material3.Card
import androidx.tv.material3.CardScale
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun <T> BigCarousel(
    modifier: Modifier = Modifier,
    items: List<T> = emptyList(),
    height: Dp = 376.dp,
    onItemSelected: (T?) -> Unit = {}
) = FocusableBox(
    modifier = modifier
        .fillMaxWidth()
        .height(height),
    focusedColor = Color.Transparent
) {

    Carousel(
        itemCount = items.size,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) { indexOfCarouselItem ->
        val item = items[indexOfCarouselItem]

        Card(
            scale = CardScale.None,
            modifier = modifier,
            onClick = {
                onItemSelected(item)
            }
        ) {

            ImageAny(
                src = if (item is ItemWithImage) item.backgroundImageUrl else null,
                contentDescription = null,
                placeholder = R.drawable.placeholder,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

        }

        TextAny(
            text = if (item is ItemWithTitle) item.title else "",
            style =
            MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(36.dp)
                .background(MaterialTheme.colorScheme.background)
        )

    }

}

@Suppress("unused")
fun <T> TvLazyListScope.bigCarousel(
    items: List<T?> = emptyList(),
    height: Dp = 280.dp,
    onItemSelected: (T?) -> Unit = {}
) = item {
    BigCarousel(
        items = items,
        height = height,
        onItemSelected = onItemSelected
    )
}