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

class AudioItem(
    private val contentResolver: ContentResolver,
    ci: CursorItem
) : CursorItem(), ItemWithTitle<String>, ItemWithImage<Any>, ItemWithUri<Uri> {

    init {
        putAll(ci)
    }

    override val title: String?
        get() = this[MediaStore.Audio.Media.TITLE]?.toString()

    override val uri: Uri
        get() = Uri.withAppendedPath(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            this[MediaStore.Audio.Media._ID].toString()
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

    companion object {
        val URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val MEDIA_PROJECTION = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.BITRATE,
            MediaStore.Audio.Media.AUTHOR,
            MediaStore.Audio.Media.ALBUM_ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Audio.Media.BUCKET_ID,
            MediaStore.Audio.Media.CAPTURE_FRAMERATE,
            MediaStore.Audio.Media.CD_TRACK_NUMBER,
            MediaStore.Audio.Media.COMPILATION,
            MediaStore.Audio.Media.WRITER,
            // todo
        )
    }

}