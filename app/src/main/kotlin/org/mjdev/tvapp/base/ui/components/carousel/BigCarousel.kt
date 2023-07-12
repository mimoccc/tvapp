/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.carousel

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
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
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import kotlin.math.abs

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    autoScrollDurationMillis: Long = 500,
    focusState: MutableState<FocusState?> = rememberFocusState(items),
    carouselState: MutableState<CarouselState> = remember { mutableStateOf(CarouselState()) },
    onItemSelected: (movie: Any?) -> Unit = {},
    onItemClicked: (movie: Any?) -> Unit = {},
) {
    val height = LocalConfiguration.current.let { config ->
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
            config.screenWidthDp * 0.4f
        else
            config.screenHeightDp * 0.4f
    }
    val selectedItem: () -> Any? = { items[carouselState.value.activeItemIndex] }
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    BoxWithConstraints {
        Carousel(
            autoScrollDurationMillis = autoScrollDurationMillis,
            carouselState = carouselState.value,
            itemCount = items.size,
            modifier = modifier
                .recomposeHighlighter()
                .fillMaxWidth()
                .height(height.dp),
        ) { indexOfCarouselItem ->
            CarouselCard(
                item = items[indexOfCarouselItem],
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            scrollDelta.value = dragAmount
                        }
                    }
                    .onFocusChanged { state ->
                        if (state.isFocused || state.hasFocus) {
                            onItemSelected(selectedItem())
                        }
                    },
                contentScale = ContentScale.Crop,
                scale = CardScale.None,
                focusState = focusState,
                onClick = {
                    onItemClicked(selectedItem())
                }
            )
        }
    }
    // todo improve
    LaunchedEffect(scrollDelta.value) {
        val activeItemIndex = carouselState.value.activeItemIndex
        carouselState.value.pauseAutoScroll(activeItemIndex)
        if(abs(scrollDelta.value) > 250) {
            if (scrollDelta.value < 0) {
                val nextItem = carouselState.value.activeItemIndex + 1
                if (nextItem <= items.size) {
                    carouselState.value = CarouselState(nextItem).apply {
                        pauseAutoScroll(nextItem)
                    }
                }
            }
            if (scrollDelta.value > 0) {
                val prevItem = carouselState.value.activeItemIndex - 1
                if (prevItem >= 0) {
                    carouselState.value = CarouselState(prevItem).apply {
                        pauseAutoScroll(prevItem)
                    }
                }
            }
        }
    }
}