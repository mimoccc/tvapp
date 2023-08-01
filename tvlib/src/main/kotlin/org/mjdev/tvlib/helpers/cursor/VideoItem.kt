/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import org.mjdev.tvlib.extensions.CursorExt.asMap
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import java.io.Serializable

@Suppress("unused")
class VideoItem() :
    Serializable,
    ItemVideo,
    ItemWithTitle<String>,
    ItemWithImage<Any>,
    ItemWithUri<String> {

    override var title: String? = null

    override var uri: String? = null

    override var image: Any? = null

    constructor(c: Cursor) : this() {
        c.asMap(MEDIA_PROJECTION).forEach { vp ->
            when (vp.key) {
                MediaStore.Video.Media.TITLE -> title = vp.value.toString()
                MediaStore.Video.Media._ID -> uri = Uri.withAppendedPath(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    vp.value.toString()
                ).toString()

                MediaStore.Video.Media.DATA -> image = vp.value.toString()
            }
        }
    }

    companion object {

        const val SORT_ORDER_DATE_DESC: String = MediaStore.Video.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Video.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DATE_ADDED
            // todo more columns
        )

    }

}