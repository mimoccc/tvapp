/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.content.Context
import android.database.Cursor
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.helpers.cursor.CachingCursor.Companion.rememberCursor
import org.mjdev.tvlib.data.local.PhotoItem

@Suppress("DEPRECATION")
@Previews
@Composable
fun LocalPhotosRow(
    title: Any? = R.string.title_photo_local,
    rowState: LazyListState = rememberLazyListState(),
    padding: Dp = 8.dp,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundCornerSize: Dp = 8.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = PhotoItem.SORT_ORDER_DATE_DESC,
    cardWidth: Dp = computeCardWidth(),
    contentScale: ContentScale = ContentScale.Crop,
    transform: (Cursor) -> Any? = { c -> PhotoItem(c) },
    cursor: Cursor? = rememberCursor(
        uri = PhotoItem.URI,
        projection = PhotoItem.MEDIA_PROJECTION,
        selection = selection,
        selectionArgs = selectionArgs,
        sortOrder = sortOrder,
        transform = transform
    ),
    onItemFocus: ((item: Any?, fromUser:Boolean) -> Unit)? = null,
    openItem: (Context.(item: Any?) -> Unit)? = null,
) = CursorRow(
    title = title,
    rowState = rowState,
    padding = padding,
    backgroundColor = backgroundColor,
    backgroundShape = backgroundShape,
    roundCornerSize = roundCornerSize,
    cardWidth = cardWidth,
    contentScale = contentScale,
    uri = PhotoItem.URI,
    projection = PhotoItem.MEDIA_PROJECTION,
    selection = selection,
    selectionArgs = selectionArgs,
    sortOrder = sortOrder,
    transform = transform,
    openItem = openItem,
    onItemFocus = onItemFocus,
    cursor = cursor
)