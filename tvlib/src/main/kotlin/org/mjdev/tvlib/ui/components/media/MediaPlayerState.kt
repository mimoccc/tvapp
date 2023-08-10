/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MediaMetadata.MEDIA_TYPE_ALBUM
import androidx.media3.common.MediaMetadata.MEDIA_TYPE_MOVIE
import androidx.media3.common.MediaMetadata.MEDIA_TYPE_MUSIC
import androidx.media3.common.MediaMetadata.MEDIA_TYPE_MIXED
import org.mjdev.tvlib.helpers.media.MetadataRetriever
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.interfaces.ItemWithDate

@Suppress("unused", "MemberVisibilityCanBePrivate")
class MediaPlayerState(
    val player: IMediaPlayer,
    val items: List<Any?>,
    initItem :Int = 0,
    autoPlay: Boolean = true,
    startSeek: Long = 0,
) {
    val currentItem = mutableIntStateOf(initItem)

    val src = items[initItem]
    val title: String get() = (src as? ItemWithTitle<*>)?.title?.toString() ?: "-"
    val date: String get() = (src as? ItemWithDate)?.date ?: "-"
    val details: String get() = metadataRetriever.getInfo(src)
    val uri: String get() = ((src as? ItemWithUri<*>)?.uri ?: src).toString()

    val mediaType: Int = when (src) {
        is ItemAudio -> MEDIA_TYPE_MUSIC
        is ItemVideo -> MEDIA_TYPE_MOVIE
        is ItemPhoto -> MEDIA_TYPE_ALBUM
        else -> MEDIA_TYPE_MIXED
    }

    val metadataRetriever by lazy {
        MetadataRetriever(player.context)
    }

    val mediaUri: MutableState<Any?> = mutableStateOf(uri)
    val isPlaying: MutableState<Boolean> = mutableStateOf(autoPlay)
    val seek: MutableState<Long> = mutableLongStateOf(startSeek)

    val hasMediaToPlay = mediaUri.value != Uri.EMPTY
    val isAutoPlay = isPlaying.value

    val imageUrl: Any?
        get() {
            val photo = (src as? ItemPhoto)?.uri
            val image = (src as? ItemWithImage<*>)?.image
            val background = (src as? ItemWithBackground<*>)?.background
            return photo ?: image ?: background
        }

    val metaData
        get() = MediaMetadata.Builder()
            .setDisplayTitle(title)
            .setDescription(details)
            .setMediaType(mediaType)
            // todo more info
            .setArtworkUri(imageUrl.toString().parseUri())
            .build()

    val mediaItem
        get() = MediaItem.Builder()
            .setUri(mediaUri.value.toString())
            .setMediaMetadata(metaData)
            .build()

    fun play() {
        isPlaying.value = true
    }

    fun stop() {
        isPlaying.value = false
    }

    fun pause() {
        isPlaying.value = false
    }

    fun resume() {
        isPlaying.value = true
    }

    fun seekTo(ms: Long) {
        seek.value = ms
    }

    fun dispose() {
        player.release()
    }

    companion object {

        @Composable
        fun rememberMediaPlayerState(
            player: IMediaPlayer,
            items: List<Any?>,
            itemToPlay: Int = 0,
            autoPlay: Boolean = true,
            startSeek: Long = 0,
        ) = remember {
            MediaPlayerState(
                player,
                items,
                itemToPlay,
                autoPlay,
                startSeek,
            )
        }

    }

}