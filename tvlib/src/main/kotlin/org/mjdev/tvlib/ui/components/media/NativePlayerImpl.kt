/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.media.MediaPlayer
import android.net.Uri
import android.view.SurfaceView
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
import org.mjdev.tvlib.extensions.ComposeExt
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

// todo
@Suppress("unused")
class NativePlayerImpl(
    private val mediaPlayer: MediaPlayer
) : IMediaPlayer {

    @Composable
    override fun GetPlayerView() {
        val width = LocalConfiguration.current.screenWidthDp
        val height = LocalConfiguration.current.screenHeightDp
        val isEdit = ComposeExt.isEditMode()
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
                        SurfaceView(context).apply {
                            mediaPlayer.setDisplay(holder)
                        }
                    }
                )
            }
        }
    }

    override fun setMediaUri(uri: Uri) {
        mediaPlayer.setDataSource(uri.toString())
    }

    override fun prepare() {
        mediaPlayer.prepareAsync()
    }

    override fun play() {
        mediaPlayer.start()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun resume() {
        mediaPlayer.start()
    }

    override fun stop() {
        mediaPlayer.stop()
    }

    override fun dispose() {
        mediaPlayer.release()
    }

    override fun seekTo(value: Long) {
        mediaPlayer.seekTo(value.toInt())
    }

}