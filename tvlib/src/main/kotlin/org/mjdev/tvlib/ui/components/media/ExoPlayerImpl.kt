/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ContextExt.isATv
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

class ExoPlayerImpl(
    private val exoPlayer: ExoPlayer
) : IMediaPlayer {

    @UnstableApi
    @Composable
    override fun GetPlayerView() {
        val width = LocalConfiguration.current.screenWidthDp
        val height = LocalConfiguration.current.screenHeightDp
        val isEdit = isEditMode()
        Box(
            modifier = Modifier
                .recomposeHighlighter()
                .size(width.dp, height.dp)
                .background(Color.Black, RectangleShape)
        ) {
            if (!isEdit) {
                AndroidView(
                    modifier = Modifier
                        .size(width.dp, height.dp)
                        .recomposeHighlighter(),
                    factory = { context ->
                        val isAtv = context.isATv
                        PlayerView(context).apply {
                            controllerAutoShow = !isAtv
                            controllerHideOnTouch = !isAtv
                            player = exoPlayer
                        }
                    }
                )
            }
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
        exoPlayer.release()
    }

    override fun seekTo(value: Long) {
        exoPlayer.seekTo(value)
    }

}