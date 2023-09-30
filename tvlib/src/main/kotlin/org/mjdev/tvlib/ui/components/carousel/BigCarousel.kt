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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil.ImageLoader
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageLoader
import org.mjdev.tvlib.extensions.ModifierExt.detectSwipe
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.card.FocusHelper

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    autoScrollDurationMillis: Long = 8000,
    imageLoader: ImageLoader = rememberImageLoader(),
    carouselState: CarouselState = remember(items) { CarouselState() },
    onItemSelected: ((movie: Any?) -> Unit)? = null,
    onItemClicked: ((movie: Any?) -> Unit)? = null,
) {
    val height = LocalConfiguration.current.let { config ->
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) config.screenWidthDp * 0.4f
        else config.screenHeightDp * 0.4f
    }
    val focusRequester = rememberFocusRequester(items)
    val cardFocusState = rememberFocusState(items)
    Carousel(
        autoScrollDurationMillis = autoScrollDurationMillis,
        carouselState = carouselState,
        itemCount = items.size,
        modifier = modifier
            .recomposeHighlighter()
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .height(height.dp)
            .onFocusChanged { state ->
                cardFocusState.value = state
            }
            .pointerInput(Unit) {
                detectSwipe(
                    onSwipeLeft = {
                        carouselState.moveToNextItem(items.size)
                        cardFocusState.value = FocusHelper(true)
                        focusRequester.requestFocus()
                    },
                    onSwipeRight = {
                        carouselState.moveToPreviousItem(items.size)
                        cardFocusState.value = FocusHelper(true)
                        focusRequester.requestFocus()
                    },
                )
            },
    ) { indexOfCarouselItem ->
        val selectedItem = items[indexOfCarouselItem]
        onItemSelected?.invoke(selectedItem)
        CarouselCard(
            item = selectedItem,
            modifier = modifier
                .fillMaxWidth()
                .height(height.dp),
            contentScale = ContentScale.Crop,
            scale = CardScale.None,
            focusState = cardFocusState,
            imageLoader = imageLoader,
            onFocus = {
                onItemSelected?.invoke(it)
            },
            onClick = {
                onItemClicked?.invoke(selectedItem)
            }
        )
    }
}