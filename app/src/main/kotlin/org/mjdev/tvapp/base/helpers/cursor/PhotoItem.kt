/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers.cursor

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import org.mjdev.tvapp.base.extensions.CursorExt.asMap
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle

class PhotoItem(
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
        val URI: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.ARTIST,
            MediaStore.Images.Media.ALBUM,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.COMPOSER,
            MediaStore.Images.Media.YEAR,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DURATION,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.ALBUM_ARTIST,
            MediaStore.Images.Media.AUTHOR,
            MediaStore.Images.Media.BITRATE,
            // todo
        )
    }

}