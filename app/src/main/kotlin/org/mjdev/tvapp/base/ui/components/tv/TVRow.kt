/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyListItemScope
import androidx.tv.foundation.lazy.list.TvLazyListScope
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter

// todo : scroll horizontally by touch
@TvPreview
@Composable
fun TVRow(
    modifier: Modifier = Modifier,
    state: TvLazyListState = rememberTvLazyListState(),
    contentPadding: Dp = 0.dp,
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal = if (!reverseLayout) Arrangement.Start
    else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    userScrollEnabled: Boolean = true,
    pivotOffsets: PivotOffsets = PivotOffsets(),
    content: TvLazyListScope.() -> Unit = {}
) {
    TvLazyRow(
        modifier = modifier.recomposeHighlighter(),
        state = state,
        contentPadding = PaddingValues(contentPadding * 2, contentPadding),
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        userScrollEnabled = userScrollEnabled,
        pivotOffsets = pivotOffsets,
        content = content
    )
}

inline fun <T> TvLazyListScope.items(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable TvLazyListItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}