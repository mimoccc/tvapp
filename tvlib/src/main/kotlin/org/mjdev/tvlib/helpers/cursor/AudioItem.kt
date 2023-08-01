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
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import java.io.Serializable

@Suppress("unused")
class AudioItem() :
    Serializable,
    ItemAudio,
    ItemWithTitle<String>,
    ItemWithImage<Any>,
    ItemWithUri<String> {

    override var title: String? = null

    override var uri: String? = null

    override var image: Any? = null

    constructor(c: Cursor) : this() {
        c.asMap(VideoItem.MEDIA_PROJECTION).forEach { vp ->
            when (vp.key) {
                MediaStore.Audio.Media.TITLE -> title = vp.value.toString()
                MediaStore.Audio.Media._ID -> uri = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    vp.value.toString()
                ).toString()

                MediaStore.Video.Media.DATA -> image = vp.value.toString()
            }
        }
    }

    companion object {

        const val SORT_ORDER_DATE_DESC: String = MediaStore.Audio.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Audio.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DATE_ADDED,
            // todo more columns
        )

    }

}