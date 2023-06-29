/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.media

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.runtime.Composable

// todo
@Suppress("unused")
class NativePlayerImpl(
    val mediaPlayer: MediaPlayer
) : IMediaPlayer {

    @Composable
    override fun PlayerView() {
    }

    override fun setMediaUri(uri: Uri) {
    }

    override fun prepare() {
    }

    override fun play() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun stop() {
    }

    override fun dispose() {
    }

    override fun seekTo(value: Long) {
    }

}