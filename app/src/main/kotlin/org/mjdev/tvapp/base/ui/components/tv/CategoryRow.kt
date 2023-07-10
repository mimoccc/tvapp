/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.ui.components.card.PhotoCard
import org.mjdev.tvapp.base.ui.components.text.TextAny

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun CategoryRow(
    title: Any? = "category 1",
    items: List<Any?> = listOf(Unit, Unit, Unit),
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 8.dp,
    cardWidth: Dp = computeCardWidth(),
    contentScale: ContentScale = ContentScale.Crop,
    backgroundColor: Color = Color.DarkGray,
    onItemClick: (item: Any?) -> Unit = {},
    contentOfItem: @Composable (item: Any?) -> Unit = { item ->
        PhotoCard(
            item = item,
            contentScale = contentScale,
            cardWidth = cardWidth,
            onClick = onItemClick,
        )
    },
) {
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier.recomposeHighlighter()
            .fillMaxWidth()
            .background(backgroundColor, RectangleShape)
    ) {
        TextAny(
            modifier = Modifier.recomposeHighlighter()
                .fillMaxWidth()
                .padding(padding / 2, padding / 2),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        TVRow(
            horizontalArrangement = Arrangement.spacedBy(padding),
            modifier = Modifier.fillMaxWidth().pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    scrollDelta.value = dragAmount.x
                }
            }.recomposeHighlighter(),
            state = rowState,
            contentPadding = (padding.value * 1.3).dp, // todo due border & glow
        ) {
            items(items) { item ->
                contentOfItem(item)
            }
        }
        LaunchedEffect(scrollDelta.value) {
            rowState.dispatchRawDelta(-scrollDelta.value)
        }
    }
}