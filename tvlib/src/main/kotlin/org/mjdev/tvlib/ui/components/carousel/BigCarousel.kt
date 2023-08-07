/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.detectSwipe
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("AutoboxingStateValueProperty")
@Preview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    autoScrollDurationMillis: Long = 8000,
    carouselState: CarouselState = remember { CarouselState() },
    onItemSelected: (movie: Any?) -> Unit = {},
    onItemClicked: (movie: Any?) -> Unit = {},
) {
    val height = LocalConfiguration.current.let { config ->
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) config.screenWidthDp * 0.4f
        else config.screenHeightDp * 0.4f
    }
    val focusState = rememberFocusState(items)
    Carousel(
        autoScrollDurationMillis = autoScrollDurationMillis,
        carouselState = carouselState,
        itemCount = items.size,
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxWidth()
            .height(height.dp)
            .pointerInput(Unit) {
                detectSwipe(
                    onSwipeLeft = {
                        carouselState.moveToNextItem(items.size)
                    },
                    onSwipeRight = {
                        carouselState.moveToPreviousItem(items.size)
                    },
                )
            },
    ) {indexOfCarouselItem ->
        val selectedItem = items[indexOfCarouselItem]
        onItemSelected(selectedItem)
        CarouselCard(
            item = selectedItem,
            modifier = modifier
                .fillMaxWidth()
                .height(height.dp),
            contentScale = ContentScale.Crop,
            scale = CardScale.None,
            focusState = focusState,
            onFocus = onItemSelected,
            onClick = {
                onItemClicked(selectedItem)
            }
        )
    }
}