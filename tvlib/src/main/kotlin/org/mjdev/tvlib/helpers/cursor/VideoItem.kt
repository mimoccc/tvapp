/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import org.mjdev.tvlib.extensions.CursorExt.asMap
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import timber.log.Timber

@Suppress("unused")
class VideoItem(
    private val contentResolver: ContentResolver,
    c: Cursor
) : HashMap<String, Any?>(), ItemVideo, ItemWithTitle<String>, ItemWithImage<Any>, ItemWithUri<Uri> {

    override val title: String?
        get() = this[MediaStore.Video.Media.TITLE]?.toString()

    override val uri: Uri
        get() = Uri.withAppendedPath(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            this[MediaStore.Video.Media._ID].toString()
        )

    override val image: Any?
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                uri.let { thumbUri ->
                    contentResolver.loadThumbnail(
                        thumbUri,
                        Size(320, 240),
                        null
                    )
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        } else {
            Uri.parse(this[MediaStore.Video.Media.DATA].toString())
        }

    init {
        putAll(c.asMap(MEDIA_PROJECTION))
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