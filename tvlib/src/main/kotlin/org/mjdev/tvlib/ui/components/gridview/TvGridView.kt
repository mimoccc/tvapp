/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gridview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.ui.components.card.PhotoCard
import androidx.compose.foundation.lazy.grid.items

@Previews
@Composable
fun TvGridView(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit
    ),
    state: LazyGridState = rememberLazyGridState(),
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    contentScale: ContentScale = ContentScale.Crop,
    onItemFocus: ((item: Any?, fromUser:Boolean) -> Unit)? = null,
    onItemClick: ((item: Any?) -> Unit)? = null,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    contentOfItem: @Composable (item: Any?) -> Unit = { item ->
        PhotoCard(
            item = item,
            aspectRatio = aspectRatio,
            contentScale = contentScale,
            cardWidth = cardWidth,
            onClick = onItemClick,
            onFocus = onItemFocus
        )
    },
) {
    val isPortrait = isPortraitMode()
    if (isPortrait) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Adaptive(cardWidth),
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
            contentPadding = PaddingValues(16.dp),
            state = state,
        ) {
            items(
                items = items,
                key = { item -> item.hashCode() }
            ) { item ->
                contentOfItem(item)
            }
        }
    } else {
        val cardHeight = (cardWidth / aspectRatio)
        LazyHorizontalGrid(
            modifier = modifier.fillMaxSize(),
            rows = GridCells.Adaptive(cardHeight),
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
            contentPadding = PaddingValues(16.dp),
            state = state,
        ) {
            items(
                items = items,
                key = { item -> item.hashCode() }
            ) { item ->
                contentOfItem(item)
            }
        }
    }
}