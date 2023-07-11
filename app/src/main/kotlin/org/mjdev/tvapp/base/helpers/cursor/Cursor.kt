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
import android.database.ContentObserver
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
    cacheItems: Int = 256,
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
                cacheItems,
                transform,
            )
            onDispose {
                cursor?.close()
            }
        }
        return cursor
    }
}

@Suppress("FunctionName")
class PrefetchCursor(
    context: Context,
    uri: Uri?,
    private val projection: Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    private val prefetchItems: Int = 8,
    private val cacheItems: Int = 256,
    private val transform: (Cursor) -> Any? = { it },
) {

    private val resolver: ContentResolver by lazy {
        context.contentResolver
    }
    private val observer = object : ContentObserver(null) {
        @Suppress("DEPRECATION")
        override fun onChange(selfChange: Boolean) {
            cursor?.requery()
        }
    }
    private val cache: MutableList<Pair<Index, Any?>> = mutableListOf()
    private val cursor: Cursor? by lazy {
        if (uri != null) {
            try {
                resolver.query(
                    uri, projection, selection, selectionArgs, sortOrder
                )?.apply {
                    registerContentObserver(observer)
                    if (this.isNotEmpty) {
                        moveToFirst()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        } else null
    }

    val count: Int get() = cursor?.count ?: 0
    val isNotEmpty: Boolean get() = count > 0

    init {
        (0..prefetchItems).forEach { idx -> get(idx) }
    }

    private fun _get(idx: Int): Any? {
        var result: Any? = null
        if (isCached(idx)) {
            result = getFromCache(idx)
        } else {
            try {
                if (count >= idx) {
                    cursor?.moveToPosition(idx)
                    result = cursor?.let { c -> transform(c) }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
            if (result != null) {
                insertToCache(idx, result)
            }
        }
        return result
    }

    fun get(idx: Int): Any? {
        (idx - prefetchItems..idx + prefetchItems).forEach { i -> _get(i) }
        return _get(idx)
    }

    private fun insertToCache(idx: Int, value: Any) {
        if (cache.size > cacheItems) {
            cache.minByOrNull { item ->
                item.first.cached
            }?.let { item ->
                cache.remove(item)
            }
        }
        cache.add(Pair(Index(idx, System.currentTimeMillis()), value))
    }

    private fun getFromCache(idx: Int): Any? {
        return cache.firstOrNull { item ->
            item.first.idx == idx
        }?.second
    }

    private fun isCached(idx: Int): Boolean {
        return cache.count { item ->
            item.first.idx == idx
        } > 0
    }

    fun close() {
        cursor?.unregisterContentObserver(observer)
        cursor?.close()
    }

    data class Index(val idx: Int, val cached: Long)

}