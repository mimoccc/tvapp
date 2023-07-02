/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.carousel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ComposeExt
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.ui.components.card.CarouselCard

// todo swipe left and swipe right
@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun BigCarousel(
    modifier: Modifier = Modifier,
    items: List<Any?> = emptyList(),
    onItemSelected: (movie: Any?) -> Unit = {},
    onItemClicked: (movie: Any?) -> Unit = {},
) {
    val isEdit = isEditMode()
    val height = if (isEdit) 128.dp else (LocalConfiguration.current.screenHeightDp / 2).dp
    val focusState: MutableState<FocusState?> = ComposeExt.rememberFocusState()
    val itemIndex = remember { mutableIntStateOf(0) }
    val selectedItem: () -> Any? = { items[itemIndex.value] }
    BoxWithConstraints {
        Carousel(
            itemCount = items.size,
            modifier = modifier
                .fillMaxWidth()
                .height(height),
        ) { indexOfCarouselItem ->
            itemIndex.value = indexOfCarouselItem
            CarouselCard(
                item = items[indexOfCarouselItem],
                modifier = modifier
                    .fillMaxWidth()
                    .height(height)
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
}