/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.helpers.cursor

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvapp.base.extensions.CursorExt.isNotEmpty
import timber.log.Timber

@SuppressLint("Recycle")
@Composable
fun rememberCursor(
    uri: Uri?,
    projection: Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    prefetchItems: Int = 8,
    transform: (Cursor) -> Any?,
): PrefetchCursor? {
    if (uri == null) return null
    else {
        val context = LocalContext.current
        var cursor by remember {
            mutableStateOf<PrefetchCursor?>(null)
        }
        DisposableEffect(uri) {
            cursor = PrefetchCursor(
                context,
                uri,
                projection,
                selection,
                selectionArgs,
                sortOrder,
                prefetchItems,
                transform,
            )
            onDispose {
                cursor?.close()
            }
        }
        return cursor
    }
}

// todo prefetching & cache
@Suppress("UNUSED_PARAMETER")
class PrefetchCursor(
    context: Context,
    uri: Uri?,
    private val projection: Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    prefetchItems: Int = 8,
    private val transform: (Cursor) -> Any? = { it },
) {

    private val resolver: ContentResolver by lazy {
        context.contentResolver
    }
    private val cursor: Cursor? by lazy {
        if (uri != null) {
            try {
                resolver.query(
                    uri, projection, selection, selectionArgs, sortOrder
                ).apply {
                    if (this?.isNotEmpty == true) moveToFirst()
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        } else null
    }

    val count: Int get() = cursor?.count ?: 0
    val isNotEmpty: Boolean get() = count > 0

    fun get(idx: Int): Any? {
        var result: Any? = null
        try {
            if (count >= idx) {
                cursor?.moveToPosition(idx)
                result = cursor?.let { c -> transform(c) }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return result
    }

    fun close() {
        cursor?.close()
    }

}