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
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvapp.base.extensions.CursorExt.isNotEmpty
import timber.log.Timber

@Suppress("FunctionName")
open class CachingCursor(
    context: Context,
    uri: Uri?,
    private val projection: Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    private val prefetchItems: Int = 8,
    private val cacheItems: Int = 256,
    private val transform: (Cursor) -> Any? = { it },
) : Cursor {

    private val resolver: ContentResolver by lazy {
        context.contentResolver
    }
    private val observer = object : ContentObserver(null) {
        @Suppress("DEPRECATION")
        override fun onChange(selfChange: Boolean) {
            cursor?.requery()
        }
    }
    private val cache: MutableList<CachedCursorItem> = mutableListOf()
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

    val isNotEmpty: Boolean get() = count > 0

    init {
        (0..prefetchItems).forEach { idx -> getData(idx) }
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

    fun getData(idx: Int): Any? {
        (idx - prefetchItems..idx + prefetchItems).forEach { i -> _get(i) }
        return _get(idx)
    }

    private fun insertToCache(idx: Int, value: Any) {
        if (cache.size > cacheItems) {
            cache.minByOrNull { item ->
                item.cached
            }?.let { item ->
                cache.remove(item)
            }
        }
        cache.add(CachedCursorItem(idx, System.currentTimeMillis(), value))
    }

    private fun getFromCache(idx: Int): Any? {
        return cache.firstOrNull { item ->
            item.idx == idx
        }?.data
    }

    private fun isCached(idx: Int): Boolean {
        return cache.count { item ->
            item.idx == idx
        } > 0
    }

    override fun close() {
        cache.clear()
        cursor?.unregisterContentObserver(observer)
        cursor?.close()
    }

    override fun getCount(): Int =
        cursor?.count ?: 0

    override fun getPosition(): Int =
        cursor?.position ?: -1

    override fun move(idx: Int): Boolean =
        cursor?.move(idx) ?: false

    override fun moveToPosition(idx: Int): Boolean =
        cursor?.moveToPosition(idx) ?: false

    override fun moveToFirst(): Boolean =
        cursor?.moveToFirst() ?: false

    override fun moveToLast(): Boolean =
        cursor?.moveToLast() ?: false

    override fun moveToNext(): Boolean =
        cursor?.moveToNext() ?: false

    override fun moveToPrevious(): Boolean =
        cursor?.moveToPrevious() ?: false

    override fun isFirst(): Boolean =
        cursor?.isFirst ?: false

    override fun isLast(): Boolean =
        cursor?.isLast ?: true

    override fun isBeforeFirst(): Boolean =
        cursor?.isBeforeFirst ?: false

    override fun isAfterLast(): Boolean =
        cursor?.isAfterLast ?: true

    override fun getColumnIndex(name: String?): Int =
        cursor?.getColumnIndex(name) ?: -1

    override fun getColumnIndexOrThrow(name: String?): Int =
        cursor?.getColumnIndexOrThrow(name) ?: -1

    override fun getColumnName(idx: Int): String? =
        cursor?.getColumnName(idx)

    override fun getColumnNames(): Array<String>? =
        cursor?.columnNames

    override fun getColumnCount(): Int =
        cursor?.columnCount ?: 0

    override fun getBlob(idx: Int): ByteArray =
        getData(idx) as ByteArray

    override fun getString(idx: Int): String =
        getData(idx).toString()

    override fun copyStringToBuffer(idx: Int, text: CharArrayBuffer?) {
        cursor?.copyStringToBuffer(idx, text)
    }

    override fun getShort(idx: Int): Short =
        getData(idx) as Short

    override fun getInt(idx: Int): Int =
        getData(idx) as Int

    override fun getLong(idx: Int): Long =
        getData(idx) as Long

    override fun getFloat(idx: Int): Float =
        getData(idx) as Float

    override fun getDouble(idx: Int): Double =
        getData(idx) as Double

    override fun getType(idx: Int): Int =
        try {
            cursor?.getType(idx)
        } catch (e: Exception) {
            Timber.e(e)
            moveToFirst()
            try {
                cursor?.getType(idx)
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        } ?: -1

    override fun isNull(idx: Int): Boolean =
        cursor?.isNull(idx) ?: true

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun deactivate() {
        cursor?.deactivate()
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun requery(): Boolean {
        cache.clear()
        val result = cursor?.requery() ?: false
        if (result) {
            (0..prefetchItems).forEach { idx -> getData(idx) }
        }
        return result
    }

    override fun isClosed(): Boolean =
        cursor?.isClosed ?: true

    override fun registerContentObserver(observer: ContentObserver?) {
        cursor?.registerContentObserver(observer)
    }

    override fun unregisterContentObserver(observer: ContentObserver?) {
        cursor?.unregisterContentObserver(observer)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        cursor?.registerDataSetObserver(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        cursor?.unregisterDataSetObserver(observer)
    }

    override fun setNotificationUri(cr: ContentResolver?, uri: Uri?) {
        cursor?.setNotificationUri(cr, uri)
    }

    override fun getNotificationUri(): Uri? =
        cursor?.notificationUri

    override fun getWantsAllOnMoveCalls(): Boolean =
        cursor?.wantsAllOnMoveCalls ?: false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setExtras(extras: Bundle?) {
        cursor?.extras = extras
    }

    override fun getExtras(): Bundle? =
        cursor?.extras

    override fun respond(b: Bundle?): Bundle? =
        cursor?.respond(b)

    data class CachedCursorItem(
        val idx: Int,
        val cached: Long,
        val data: Any?
    )

    companion object {

        @Deprecated("Please for better performance use viewmodel with cursors.")
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
        ): CachingCursor? {
            if (uri == null) return null
            else {
                val context = LocalContext.current
                var cursor by remember {
                    mutableStateOf<CachingCursor?>(null)
                }
                DisposableEffect(uri) {
                    cursor = CachingCursor(
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

    }

}