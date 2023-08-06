/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import org.mjdev.tvlib.extensions.CursorExt.asMap
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import timber.log.Timber
import java.io.Serializable

@Suppress("unused")
class VideoItem() :
    Serializable,
    ItemVideo,
    ItemWithTitle<String>,
    ItemWithImage<Any>,
    ItemWithUri<String>,
    ItemWithDescription<String?>,
    ItemWithDate {

    override var title: String? = null
    override var uri: String? = null
    override var image: Any? = null
    override var date: String? = null
    override var description: String? = null

    constructor(c: Cursor) : this() {
        c.asMap(MEDIA_PROJECTION).forEach { vp ->
            when (vp.key) {
                MediaStore.Video.Media.TITLE -> title = vp.value.toString()
                MediaStore.Video.Media.DATA -> {
                    uri = vp.value.toString()
                    image = vp.value.toString()
                }
            }
        }
        try {
            MediaMetadataRetriever().apply {
                setDataSource(uri)
            }.also { mmr ->
                date = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VideoItem) return false
        return uri == other.uri
    }

    override fun hashCode(): Int {
        return uri?.hashCode() ?: 0
    }

    companion object {

        const val SORT_ORDER_DATE_DESC: String = MediaStore.Video.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Video.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DATA,
        )

    }

}