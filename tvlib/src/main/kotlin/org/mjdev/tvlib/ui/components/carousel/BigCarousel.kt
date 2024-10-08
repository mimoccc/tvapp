/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.swipeGestures
import org.mjdev.tvlib.ui.components.card.FocusHelper

@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    autoScrollDurationMillis: Long = 8000,
    carouselState: CarouselState = remember(items) { CarouselState() },
    onItemSelected: ((movie: Any?, fromUser: Boolean) -> Unit)? = null,
    onItemClicked: ((movie: Any?) -> Unit)? = null,
    onSwipeUp: ((dragAmount: Offset) -> Unit)? = null,
    onSwipeDown: ((dragAmount: Offset) -> Unit)? = null,
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
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .height(height.dp)
            .onFocusChanged { state ->
                cardFocusState.value = state
            }
            .swipeGestures(
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
                onSwipeDown = onSwipeDown,
                onSwipeUp = onSwipeUp
            ),
    ) { indexOfCarouselItem ->
        val selectedItem = items[indexOfCarouselItem]
        onItemSelected?.invoke(selectedItem, false)
        CarouselCard(
            item = selectedItem,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            scale = CardScale.None,
            focusState = cardFocusState,
            onFocus = { item, fromUser ->
                onItemSelected?.invoke(item, fromUser)
            },
            onClick = {
                onItemClicked?.invoke(selectedItem)
            }
        )
    }
}