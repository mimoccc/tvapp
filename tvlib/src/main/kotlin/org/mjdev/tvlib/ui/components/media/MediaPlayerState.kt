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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Suppress("unused", "MemberVisibilityCanBePrivate")
class MediaPlayerState(
    uri: Uri = Uri.EMPTY,
    autoPlay: Boolean = true,
    startSeek: Long = 0,
) {

    constructor(
        uri: String? = null,
        autoPlay: Boolean = true,
        startSeek: Long = 0
    ) : this(
        if (uri === null) Uri.EMPTY else Uri.parse(uri),
        autoPlay,
        startSeek
    )

    val mediaUri: MutableState<Uri> = mutableStateOf(uri)
    val isPlaying: MutableState<Boolean> = mutableStateOf(autoPlay)
    val seek: MutableState<Long> = mutableLongStateOf(startSeek)

    val hasMediaToPlay = mediaUri.value != Uri.EMPTY
    val isAutoPlay = isPlaying.value

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

    companion object {

        @Composable
        fun rememberMediaPlayerState(
            uri: Uri = Uri.EMPTY,
            autoPlay: Boolean = true,
            startSeek: Long = 0
        ) = remember {
            MediaPlayerState(
                uri,
                autoPlay,
                startSeek
            )
        }

    }

}