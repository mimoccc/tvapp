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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ModifierExt.detectSwipe
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    autoScrollDurationMillis: Long = 1500,
    carouselState: MutableState<CarouselState> = remember { mutableStateOf(CarouselState()) },
    onItemSelected: (movie: Any?) -> Unit = {},
    onItemClicked: (movie: Any?) -> Unit = {},
) {
    val height = LocalConfiguration.current.let { config ->
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) config.screenWidthDp * 0.4f
        else config.screenHeightDp * 0.4f
    }
    val isFocused = remember { mutableStateOf(false) }
    Box {
        Carousel(
            autoScrollDurationMillis = autoScrollDurationMillis,
            carouselState = carouselState.value,
            itemCount = items.size,
            modifier = modifier
                .recomposeHighlighter()
                .fillMaxWidth()
                .height(height.dp)
                .pointerInput(Unit) {
                    detectSwipe(
                        onSwipeLeft = {
                            val nextItem = carouselState.value.activeItemIndex + 1
                            if (nextItem < items.size) {
                                carouselState.value = CarouselState(nextItem).apply {
                                    pauseAutoScroll(nextItem).apply {
                                        if (isFocused.value) {
                                            resumeAutoScroll()
                                        }
                                    }
                                }
                            }
                        },
                        onSwipeRight = {
                            val prevItem = carouselState.value.activeItemIndex - 1
                            if (prevItem >= 0) {
                                carouselState.value = CarouselState(prevItem).apply {
                                    pauseAutoScroll(prevItem).apply {
                                        if (isFocused.value) {
                                            resumeAutoScroll()
                                        }
                                    }
                                }
                            }
                        },
                    )
                },
        ) { indexOfCarouselItem ->
            CarouselCard(
                item = items[indexOfCarouselItem],
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .onFocusChanged { state ->
                        val selectedIdx = carouselState.value.activeItemIndex
                        val selectedItem = items[selectedIdx]
                        if (state.isFocused || state.hasFocus) {
                            onItemSelected(selectedItem)
                        }
                        isFocused.value = state.isFocused || state.hasFocus
                    },
                contentScale = ContentScale.Crop,
                scale = CardScale.None,
                focused = isFocused.value && (carouselState.value.activeItemIndex == indexOfCarouselItem),
                onFocus = onItemSelected,
                onClick = {
                    val selectedIdx = carouselState.value.activeItemIndex
                    val selectedItem = items[selectedIdx]
                    onItemClicked(selectedItem)
                }
            )
        }
    }
}