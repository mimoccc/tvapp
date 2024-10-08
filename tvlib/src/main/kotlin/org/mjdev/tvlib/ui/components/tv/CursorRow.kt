/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.CursorExt.getData
import org.mjdev.tvlib.extensions.CursorExt.isNotEmpty
import org.mjdev.tvlib.helpers.cursor.CachingCursor.Companion.rememberCursor
import org.mjdev.tvlib.ui.components.card.PhotoCard
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("DEPRECATION")
@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun CursorRow(
    title: Any? = null,
    rowState: LazyListState = rememberLazyListState(),
    padding: Dp = 16.dp,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundCornerSize: Dp = 8.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    cardWidth: Dp = computeCardWidth(),
    contentScale: ContentScale = ContentScale.Crop,
    uri: Uri? = null,
    projection: Array<String> = emptyArray(),
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    transform: (Cursor) -> Any? = { it },
    cursor: Cursor? = rememberCursor(
        uri = uri,
        projection = projection,
        selection = selection,
        selectionArgs = selectionArgs,
        sortOrder = sortOrder,
        transform = transform
    ),
    onItemFocus: ((item: Any?, fromUser:Boolean) -> Unit)? = null,
    openItem: (Context.(item: Any?) -> Unit)? = null,
) {
    val context: Context = LocalContext.current
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    if (cursor?.isNotEmpty == true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor, backgroundShape)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            scrollDelta.value = dragAmount
                        }
                    },
                state = rowState,
                contentPadding = (padding.value * 1.3).dp, // todo due border & glow
            ) {
                items(
                    count = cursor.count,
                    key = { item -> item.hashCode() }
                ) { idx ->
                    PhotoCard(
                        item = cursor.getData(idx),
                        contentScale = contentScale,
                        cardWidth = cardWidth,
                        onFocus = onItemFocus,
                        onClick = { item ->
                            openItem?.invoke(context, item)
                        }
                    )
                }
            }
            LaunchedEffect(scrollDelta.value) {
                rowState.animateScrollBy(-(scrollDelta.value * 3f))
            }
        }
    }
}