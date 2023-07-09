/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers.cursor

import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.interfaces.ItemWithUri
import timber.log.Timber

class VideoItem(
    private val contentResolver: ContentResolver,
    ci: CursorItem
) : CursorItem(), ItemWithTitle<String>, ItemWithImage<Any>, ItemWithUri<Uri> {

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
        putAll(ci)
    }

    companion object {
        val URI: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.ARTIST,
            MediaStore.Video.Media.ALBUM,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.COMPOSER,
            MediaStore.Video.Media.YEAR,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_MODIFIED,
            // todo
        )
    }

}