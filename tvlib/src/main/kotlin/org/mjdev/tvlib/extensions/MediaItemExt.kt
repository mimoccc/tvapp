/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package org.mjdev.tvlib.extensions

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.helpers.media.MetadataRetriever
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri

@Suppress("unused")
object MediaItemExt {

    val Context.metadataRetriever
        get() = MetadataRetriever(this)

    val Any?.title: String
        get() = (this as? ItemWithTitle<*>)?.title?.toString() ?: "-"

    val Any?.date: String
        get() = (this as? ItemWithDate)?.date ?: ""

    fun Any?.details(context: Context): String =
        context.metadataRetriever.getInfo(this)

    val Any?.uri: String
        get() = when (this) {
            is MediaItem -> this.localConfiguration?.uri.toString()
            is ItemWithUri<*> -> (this as? ItemWithUri<*>)?.uri.toString()
            is ItemAudio -> this.uri.toString()
            is ItemVideo -> this.uri.toString()
            is ItemPhoto -> this.uri.toString()
            else -> this.toString()
        }

    val Any?.mediaType: Int
        get() = when (this) {
            is ItemAudio -> MediaMetadata.MEDIA_TYPE_MUSIC
            is ItemVideo -> MediaMetadata.MEDIA_TYPE_MOVIE
            is ItemPhoto -> MediaMetadata.MEDIA_TYPE_ALBUM
            else -> MediaMetadata.MEDIA_TYPE_MIXED
        }

    // todo : MediaItem
    val Any?.imageUrl: Any?
        get() {
            val photo = (this as? ItemPhoto)?.uri
            val image = (this as? ItemWithImage<*>)?.image
            val background = (this as? ItemWithBackground<*>)?.background
            return photo ?: image ?: background
        }

    // todo more info
    val Any?.metaData: MediaMetadata
        get() = MediaMetadata.Builder()
            .setDisplayTitle(title)
            .setMediaType(mediaType)
            .setArtworkUri(imageUrl.toString().parseUri())
            .build()

    val Any?.mediaItem: MediaItem
        get() = if (this is MediaItem) this else {
            val url = uri
            val webUrl = (this as? org.mjdev.tvlib.data.local.IWebUrlContainer)?.webUrl
            MediaItem.Builder()
                .setUri(url)
//                .setMediaId(url)
//                .setMimeType(url.mimeType)
                .setMediaMetadata(metaData)
                .setTag(webUrl)
                .build()
        }

}
