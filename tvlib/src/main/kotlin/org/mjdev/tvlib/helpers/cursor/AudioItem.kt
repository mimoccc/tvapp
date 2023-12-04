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
import org.mjdev.tvlib.helpers.apps.App
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithDescription
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
    ItemWithUri<String>,
    ItemWithDescription<String?>,
    ItemWithDate {

    override var title: String? = null
    override var uri: String? = null
    override var image: Any? = null
    override var date: String? = null
    override var description: String? = null

    constructor(c: Cursor) : this() {
        c.asMap(VideoItem.MEDIA_PROJECTION).forEach { vp ->
            when (vp.key) {
                MediaStore.Audio.Media.TITLE -> title = vp.value.toString()
                MediaStore.Audio.Media.DATA -> {
                    uri = vp.value.toString()
                    image = vp.value.toString()
                }
            }
        }
// todo move to another thread
//        try {
//            MediaMetadataRetriever().apply {
//                setDataSource(uri)
//            }.also { mmr ->
//                date = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)
//            }
//        } catch (e: Exception) {
//            Timber.e(e)
//        }
    }

    override fun equals(other: Any?): Boolean =
        if (other !is AudioItem) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (uri?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

    companion object {
        const val SORT_ORDER_DATE_DESC: String = MediaStore.Audio.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Audio.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
        )
    }
}