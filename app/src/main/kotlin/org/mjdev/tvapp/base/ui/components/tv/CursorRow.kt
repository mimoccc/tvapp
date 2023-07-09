/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.computeCardHeight
import org.mjdev.tvapp.base.helpers.cursor.CursorItem
import org.mjdev.tvapp.base.helpers.cursor.rememberMediaCursor

@TvPreview
@Composable
fun CursorRow(
    title: Any? = null,
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 16.dp,
    backgroundColor: Color = Color.DarkGray,
    uri: Uri? = null,
    projection: Array<String> = emptyArray(),
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    transform: (CursorItem) -> Any? = { it },
    cardHeight: Dp = computeCardHeight(),
    contentScale: ContentScale = ContentScale.Crop,
    openItem: Context.(item: CursorItem?) -> Unit = {},
) {
    val context: Context = LocalContext.current
    val items = rememberMediaCursor(
        uri,
        projection,
        selection,
        selectionArgs,
        sortOrder
    ).collectAsState(emptyList())
    if (items.value.isNotEmpty()) {
        CategoryRow(
            title = title,
            items = items.value.map { item -> transform(item) },
            rowState = rowState,
            padding = padding,
            cardHeight = cardHeight,
            contentScale = contentScale,
            backgroundColor = backgroundColor,
            onItemClick = { item ->
                openItem(context, item as? CursorItem)
            },
        )
    }
}