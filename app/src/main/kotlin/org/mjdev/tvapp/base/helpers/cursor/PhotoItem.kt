/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers.cursor

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import org.mjdev.tvapp.base.extensions.CursorExt.asMap
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle

@Suppress("UNUSED_PARAMETER", "unused")
class PhotoItem(
    contentResolver: ContentResolver,
    c: Cursor
) : HashMap<String, Any?>(), ItemWithTitle<String>, ItemWithImage<Any> {

    init {
        putAll(c.asMap(MEDIA_PROJECTION))
    }

    override val title: String?
        get() = this[MediaStore.Images.Media.TITLE]?.toString()

    override val image: Any?
        get() = this[MediaStore.Images.Media.DATA]

    companion object {
        const val SORT_ORDER_DATE_DESC: String = MediaStore.Images.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Images.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_ADDED,
            // todo more columns
        )
    }

}