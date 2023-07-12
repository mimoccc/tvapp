/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.database.Cursor
import androidx.core.database.getBlobOrNull
import androidx.core.database.getFloatOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import org.mjdev.tvapp.base.helpers.cursor.CachingCursor
import timber.log.Timber

object CursorExt {

    val Cursor.isNotEmpty get() = count > 0

    fun Cursor.asMap(
        projection: Array<String> = columnNames
    ): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
        projection.forEach { cname ->
            val idx = getColumnIndex(cname)
            val value = try {
                getColumn(idx)
            } catch (e: Exception) {
                Timber.e(e)
                moveToFirst()
                try {
                    getColumn(idx)
                } catch (e: Exception) {
                    Timber.e(e)
                    null
                }
            }
            this[cname] = value
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun Cursor.getColumn(idx: Int): Any? = when (getType(idx)) {
        Cursor.FIELD_TYPE_FLOAT -> getFloatOrNull(idx)
        Cursor.FIELD_TYPE_BLOB -> getBlobOrNull(idx)
        Cursor.FIELD_TYPE_INTEGER -> getIntOrNull(idx)
        Cursor.FIELD_TYPE_STRING -> getStringOrNull(idx)
        else -> null
    }

    fun Cursor.getData(idx: Int): Any? {
        return if (this is CachingCursor)
            getData(idx)
        else {
            try {
                moveToPosition(idx)
                asMap()
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }
    }

}