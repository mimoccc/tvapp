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
import androidx.exifinterface.media.ExifInterface
import org.mjdev.tvlib.extensions.CursorExt.asMap
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import timber.log.Timber
import java.io.Serializable

@Suppress("unused")
class PhotoItem() :
    Serializable,
    ItemPhoto,
    ItemWithTitle<String>,
    ItemWithImage<Any>,
    ItemWithBackground<Any?>,
    ItemWithDescription<String?>,
    ItemWithDate {

    override var title: String? = null
    override var uri: String? = null
    override var image: Any? = null
    override var background: Any? = null
    override var date: String? = null
    override var description: String? = null

    constructor(c: Cursor) : this() {
        c.asMap(VideoItem.MEDIA_PROJECTION).forEach { vp ->
            when (vp.key) {
                MediaStore.Images.Media.TITLE -> title = vp.value.toString()
                MediaStore.Images.Media._ID -> uri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    vp.value.toString()
                ).toString()

                MediaStore.Images.Media.DATA -> {
                    uri = vp.value.toString()
                    image = vp.value.toString()
                    background = vp.value.toString()
                }
            }
        }
        try {
            date = ExifInterface(image.toString()).getAttribute(ExifInterface.TAG_DATETIME)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is PhotoItem) return false
        return uri == other.uri
    }

    override fun hashCode(): Int {
        return uri?.hashCode() ?: 0
    }

    companion object {

        const val SORT_ORDER_DATE_DESC: String = MediaStore.Images.Media.DATE_ADDED + " DESC"
        const val SORT_ORDER_DATE_ASC: String = MediaStore.Images.Media.DATE_ADDED + " ASC"

        val URI: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DATA,
        )

    }

}