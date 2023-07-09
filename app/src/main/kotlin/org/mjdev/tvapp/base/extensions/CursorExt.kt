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
import org.mjdev.tvapp.base.helpers.cursor.CursorItem
import timber.log.Timber

object CursorExt {

    fun Cursor.toList(
        projection: Array<String>
    ): List<CursorItem> {
        val c: Cursor = this
        return ArrayList<CursorItem>().also { list ->
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    list.add(CursorItem().also { ci ->
                        projection.forEach { cname ->
                            val idx = c.getColumnIndex(cname)
                            val value = try {
                                c.get(idx)
                            } catch (e: Exception) {
                                Timber.e(e)
                                null
                            }
                            ci[cname] = value
                        }
                    })
                } while (moveToNext())
            }
        }
    }

    fun Cursor.get(idx: Int): Any? = when (getType(idx)) {
        Cursor.FIELD_TYPE_FLOAT -> getFloatOrNull(idx)
        Cursor.FIELD_TYPE_BLOB -> getBlobOrNull(idx)
        Cursor.FIELD_TYPE_INTEGER -> getIntOrNull(idx)
        Cursor.FIELD_TYPE_STRING -> getStringOrNull(idx)
        else -> null
    }

}