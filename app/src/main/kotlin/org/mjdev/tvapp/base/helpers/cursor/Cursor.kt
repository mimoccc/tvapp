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
import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import org.mjdev.tvapp.base.extensions.CursorExt.toList
import timber.log.Timber

@Composable
fun rememberMediaCursor(
    uri: Uri?,
    projection: Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
): Flow<List<CursorItem>> {
    val context: Context = LocalContext.current
    return remember {
        callbackFlow {
            val resolver = context.contentResolver
            val observer = object : ContentObserver(null) {
                @SuppressLint("Recycle")
                override fun onChange(selfChange: Boolean) {
                    if (uri != null) {
                        try {
                            resolver.query(
                                uri, projection, selection, selectionArgs, sortOrder
                            )?.toList(projection)?.let { data ->
                                trySend(data)
                            }
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    } else {
                        trySend(emptyList())
                    }
                }
            }
            if (uri != null) {
                resolver.registerContentObserver(uri, true, observer)
                observer.onChange(true)
                awaitClose {
                    resolver.unregisterContentObserver(observer)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}