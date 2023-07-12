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
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import org.mjdev.tvapp.base.extensions.CursorExt.asMap
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import timber.log.Timber

@Suppress( "unused")
class PhotoItem(
    private val contentResolver: ContentResolver,
    c: Cursor
) : HashMap<String, Any?>(), ItemWithTitle<String>, ItemWithImage<Any> {

    init {
        putAll(c.asMap(MEDIA_PROJECTION))
    }

    override val title: String?
        get() = this[MediaStore.Images.Media.TITLE]?.toString()

    val uri: Uri
        get() = Uri.withAppendedPath(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            this[MediaStore.Images.Media._ID].toString()
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
            Uri.parse(this[MediaStore.Images.Media.DATA].toString())
        }

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