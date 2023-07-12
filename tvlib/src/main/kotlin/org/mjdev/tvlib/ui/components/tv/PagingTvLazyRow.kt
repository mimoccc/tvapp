/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.paging.ListPagingSource
import org.mjdev.tvlib.paging.SOURCE
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("ModifierParameter")
@TvPreview
@Composable
fun <T : Any> PagingTvLazyRow(
    modifier: Modifier = Modifier.fillMaxSize(),
    state: TvLazyListState = rememberTvLazyListState(),
    contentPadding: Dp = 0.dp,
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal = if (!reverseLayout) Arrangement.Start
    else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    userScrollEnabled: Boolean = true,
    pivotOffsets: PivotOffsets = PivotOffsets(),
    perPage: Int = 8,
    source: SOURCE<T> = { _, _ -> emptyList() },
    loadStateHandler: (state: LoadState) -> Unit = {},
    onItemClick: (data: T) -> Unit = { _ -> },
    emptyContent: @Composable () -> Unit = { TextAny(text = R.string.no_items) },
    itemBlock: @Composable (
        idx: Int, item: T, onItemClick: (data: T) -> Unit
    ) -> Unit = { _, _, _ -> }
) {

    val listData = remember {
        Pager(config = PagingConfig(
            pageSize = perPage, prefetchDistance = perPage, initialLoadSize = perPage
        ), pagingSourceFactory = {
            ListPagingSource(source)
        }).flow
    }.collectAsLazyPagingItems().apply {
        loadStateHandler.invoke(loadState.refresh)
    }

    TvLazyRow(
        modifier = modifier.recomposeHighlighter(),
        state = state,
        contentPadding = PaddingValues(contentPadding),
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        userScrollEnabled = userScrollEnabled,
        pivotOffsets = pivotOffsets,
    ) {
        if (listData.itemCount == 0) {
            item {
                emptyContent()
            }
        } else {
            items(
                count = listData.itemCount,
                key = listData.itemKey(),
                contentType = listData.itemContentType()
            ) { index ->
                val item = listData[index]
                if (item != null) {
                    itemBlock(index, item, onItemClick)
                }
            }
        }
    }

}