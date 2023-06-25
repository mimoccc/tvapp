/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import timber.log.Timber

// todo
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun <T> ListWithPreview(
    modifier: Modifier = Modifier,
    items: List<T> = listOf(),
    immersiveListHeight: Dp = 300.dp,
    cardSpacing: Dp = 10.dp,
    cardWidth: Dp = 200.dp,
    cardHeight: Dp = 150.dp
) {
    val itemBackground: (index: Int) -> Any? = { index ->
        (if (items.size > index)
            (items[index] as? ItemWithImage?)?.backgroundImageUrl
        else null) ?: Color.Transparent
    }
    ImmersiveList(
        modifier = modifier
            .height(immersiveListHeight + cardHeight / 2)
            .fillMaxWidth(),
        background = { index, _ ->
            ImageAny(
                modifier = Modifier
                    .height(immersiveListHeight)
                    .fillMaxWidth(),
                src = itemBackground.invoke(index)
            )
        }
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(cardSpacing)) {
            items.forEachIndexed { index, item ->
                val isFocused = remember { mutableStateOf(false) }
                ImageAny(
                    modifier = Modifier
                        .width(cardWidth)
                        .height(cardHeight)
                        .border(5.dp, Color.White.copy(alpha = if (isFocused.value) 1f else 0.3f))
                        .onFocusChanged { focusState ->
                            isFocused.value = focusState.isFocused
                        }
                        .immersiveListItem(index)
                        .clickable {
                            Timber.tag("ImmersiveList: Item $index was clicked")
                        },
                    src = itemBackground.invoke(index)
                )
            }
        }
    }
}