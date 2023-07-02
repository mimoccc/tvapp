/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.ui.components.card.PhotoCard
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Suppress("UNUSED_PARAMETER")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun CategoryRow(
    title: Any? = "category 1",
    items: List<Any?> = listOf(Unit, Unit, Unit),
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 16.dp,
    backgroundColor: Color = Color.DarkGray,
    onItemClick: (item: Any?) -> Unit = {},
) {
// todo better design & scroll horizontally
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RectangleShape)
    ) {
        TextAny(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding / 2, padding / 2),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        TVRow(
            horizontalArrangement = Arrangement.spacedBy(padding),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = (padding.value * 1.3).dp, // todo due border & glow
        ) {
            items(items) { item ->
                PhotoCard(
                    item = item,
                    onClick = onItemClick,
                )
            }
        }
    }
}