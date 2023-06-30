/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvapp.base.annotations.TvPreview

@OptIn(ExperimentalFoundationApi::class)
@TvPreview
@Composable
fun VerticalTvPager(
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    initialPageOffsetFraction: Float = 0f,
    pageCount: () -> Int = { 0 },
    state: PagerState = rememberPagerState(
        initialPage = initialPage,
        initialPageOffsetFraction = initialPageOffsetFraction,
        pageCount = pageCount
    ),
    beyondBoundsPageCount: Int = 0,
    contentPadding: Dp = 0.dp,
    pageSize: PageSize = PageSize.Fill,
    pageSpacing: Dp = 0.dp,
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    flingBehavior: SnapFlingBehavior = PagerDefaults.flingBehavior(state = state),
    userScrollEnabled: Boolean = false,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
        Orientation.Vertical
    ),
    pageContent: @Composable PagerScope.(page: Int) -> Unit = {},
) = VerticalPager(
    state = state,
    modifier = modifier,
    contentPadding = PaddingValues(contentPadding),
    pageSize = pageSize,
    beyondBoundsPageCount = beyondBoundsPageCount,
    pageSpacing = pageSpacing,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    reverseLayout = reverseLayout,
    key = key,
    pageNestedScrollConnection = pageNestedScrollConnection,
    pageContent = pageContent
)