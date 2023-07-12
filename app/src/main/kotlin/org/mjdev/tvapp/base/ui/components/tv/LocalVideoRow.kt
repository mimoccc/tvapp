/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberContentResolver
import org.mjdev.tvapp.base.helpers.cursor.CachingCursor.Companion.rememberCursor
import org.mjdev.tvapp.base.helpers.cursor.VideoItem

@Suppress("DEPRECATION")
@TvPreview
@Composable
fun LocalVideoRow(
    title: Any? = R.string.title_video_local,
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 8.dp,
    backgroundColor: Color = Color.DarkGray,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = VideoItem.SORT_ORDER_DATE_DESC,
    cardWidth: Dp = computeCardWidth(),
    contentScale: ContentScale = ContentScale.Crop,
    contentResolver: ContentResolver = rememberContentResolver(),
    transform: (Cursor) -> Any? = { c -> VideoItem(contentResolver, c) },
    cursor: Cursor? = rememberCursor(
        uri = VideoItem.URI,
        projection = VideoItem.MEDIA_PROJECTION,
        selection = selection,
        selectionArgs = selectionArgs,
        sortOrder = sortOrder,
        transform = transform
    ),
    openItem: Context.(item: Any?) -> Unit = {},
) {
    CursorRow(
        title = title,
        rowState = rowState,
        padding = padding,
        backgroundColor = backgroundColor,
        cardWidth = cardWidth,
        contentScale = contentScale,
        uri = VideoItem.URI,
        projection = VideoItem.MEDIA_PROJECTION,
        selection = selection,
        selectionArgs = selectionArgs,
        sortOrder = sortOrder,
        transform = { c -> VideoItem(contentResolver, c) },
        openItem = openItem,
    )
}