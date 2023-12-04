/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.card.PhotoCard
import org.mjdev.tvlib.ui.components.text.TextAny

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun CategoryRow(
    title: Any? = "category",
    items: List<Any?> = listOf(Unit, Unit, Unit),
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 8.dp,
    cardWidth: Dp = computeCardWidth(),
    contentScale: ContentScale = ContentScale.Crop,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundCornerSize: Dp = 8.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    onItemFocus: (item: Any?) -> Unit = {},
    onItemClick: (item: Any?) -> Unit = {},
    contentOfItem: @Composable (item: Any?) -> Unit = { item ->
        PhotoCard(
            item = item,
            contentScale = contentScale,
            cardWidth = cardWidth,
            onClick = onItemClick,
            onFocus = onItemFocus
        )
    },
) {
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier
            .recomposeHighlighter()
            .fillMaxWidth()
            .background(backgroundColor, backgroundShape)
    ) {
        TextAny(
            modifier = Modifier
                .recomposeHighlighter()
                .fillMaxWidth()
                .padding(padding, padding / 2),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        TVRow(
            horizontalArrangement = Arrangement.spacedBy(padding),
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        scrollDelta.value = dragAmount.x
                    }
                }
                .recomposeHighlighter(),
            state = rowState,
            contentPadding = padding.value.dp,
        ) {
            items(
                items = items,
                key = { item -> item.hashCode() }
            ) { item ->
                contentOfItem(item)
            }
        }
        LaunchedEffect(scrollDelta.value) {
            rowState.dispatchRawDelta(-scrollDelta.value)
        }
    }
}