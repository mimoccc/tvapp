/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.media

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import org.mjdev.tvapp.base.extensions.ComposeExt
import org.mjdev.tvapp.base.extensions.ContextExt.isATv

class ExoPlayerImpl(
    private val exoPlayer: ExoPlayer
) : IMediaPlayer {

    @UnstableApi
    @Composable
    override fun PlayerView() {

        val isEdit = ComposeExt.isEditMode()
        val context = LocalContext.current
        val isAtv = context.isATv

        if (isEdit) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black, RectangleShape)
            )

        } else {

            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent, RectangleShape),
                factory = {
                    androidx.media3.ui.PlayerView(context).apply {
                        player = exoPlayer
                        controllerAutoShow = !isAtv
                        controllerHideOnTouch = !isAtv
                    }
                }
            )

        }

    }

    override fun setMediaUri(uri: Uri) {
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
    }

    override fun prepare() {
        exoPlayer.prepare()
    }

    override fun play() {
        exoPlayer.playWhenReady = true
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
    }

    override fun resume() {
        exoPlayer.playWhenReady = true
    }

    override fun stop() {
        exoPlayer.playWhenReady = false
    }

    override fun dispose() {
    }

    override fun seekTo(value: Long) {
        exoPlayer.seekTo(value)
    }

}