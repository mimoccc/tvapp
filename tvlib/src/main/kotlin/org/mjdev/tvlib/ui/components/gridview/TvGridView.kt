/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gridview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyGridState
import androidx.tv.foundation.lazy.grid.TvLazyHorizontalGrid
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.ui.components.card.PhotoCard

@OptIn(ExperimentalTvMaterial3Api::class)
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
    state: TvLazyGridState = rememberTvLazyGridState(),
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    contentScale: ContentScale = ContentScale.Crop,
    onItemFocus: (item: Any?) -> Unit = {},
    onItemClick: (item: Any?) -> Unit = {},
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
        TvLazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = TvGridCells.Adaptive(cardWidth),
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
            contentPadding = PaddingValues(16.dp),
            state = state,
        ) {
            items(items) { item ->
                contentOfItem(item)
            }
        }
    } else {
        val cardHeight = (cardWidth / aspectRatio)
        TvLazyHorizontalGrid(
            modifier = modifier.fillMaxSize(),
            rows = TvGridCells.Adaptive(cardHeight),
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
            contentPadding = PaddingValues(16.dp),
            state = state,
        ) {
            items(items) { item ->
                contentOfItem(item)
            }
        }
    }
}