/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem

@Suppress("unused", "MemberVisibilityCanBePrivate")
class MediaPlayerState(
    val player: IMediaPlayer,
    initItems: List<MediaItem>,
    initItemIndex: Int = 0,
    autoPlay: Boolean = true,
    startSeek: Long = 0,
) : Player.Listener {

    val mediaItems: MutableState<List<MediaItem>> = mutableStateOf(
        initItems
    )

    val currentItemIndex = mutableIntStateOf(initItemIndex)

    val currentItem = derivedStateOf {
        mediaItems.value[currentItemIndex.intValue].mediaItem
    }

    val currentPosition = mutableLongStateOf(startSeek)

    val isPlaying: MutableState<Boolean> = mutableStateOf(autoPlay)

    val error: MutableState<Exception?> = mutableStateOf(null)

    val hasMediaToPlay = mediaItems.value.isNotEmpty()

    val isAutoPlay = isPlaying.value

    init {
        player.addListener(this)
    }

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
        currentPosition.longValue = ms
    }

    fun dispose() {
        player.release()
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        this.isPlaying.value = isPlaying
    }

    override fun onPlayerError(error: PlaybackException) {
        this.error.value = error
    }

    override fun onPlayerErrorChanged(error: PlaybackException?) {
        this.error.value = error
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        currentPosition.longValue = player.currentPosition
    }

    companion object {

        @Composable
        fun rememberMediaPlayerState(
            player: IMediaPlayer,
            items: List<MediaItem>,
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