/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.data.local

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import org.mjdev.tvlib.extensions.CursorExt.asMap
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
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
// todo move to another thread
//        try {
//            date = ExifInterface(image.toString()).getAttribute(ExifInterface.TAG_DATETIME)
//        } catch (e: Exception) {
//            Timber.e(e)
//        }
    }

    override fun equals(other: Any?): Boolean =
        if (other !is PhotoItem) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (uri?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (background?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
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
