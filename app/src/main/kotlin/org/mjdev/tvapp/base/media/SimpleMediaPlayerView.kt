/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.media

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import org.mjdev.tvapp.base.extensions.ComposeExt

@Composable
@Deprecated("Deprecated, use MediaPlayerContainer instead.")
fun SimpleMediaPlayerView(
    modifier: Modifier = Modifier,
    uri: Uri?,
    autoPlay: Boolean = true,
    background: @Composable (player: ExoPlayer) -> Unit = {},
    mediaPlayerOverlay: @Composable (player: ExoPlayer) -> Unit = {},
    mediaPlayerControls: @Composable (player: ExoPlayer) -> Unit = {}
) {

    val isEdit = ComposeExt.isEditMode()
    val context = LocalContext.current

    val player = remember { ExoPlayer.Builder(context).build() }

    Box(modifier = modifier) {

        background(player)

        if (!isEdit) {
            AndroidView(
                modifier = Modifier
                    .background(Color.Transparent, RectangleShape)
                    .fillMaxSize(),
                factory = {
                    PlayerView(context).apply {
                        this.player = player
                    }
                }
            )
        }

        mediaPlayerOverlay(player)

        mediaPlayerControls(player)

    }

    if (!isEdit) {

        DisposableEffect(player) {

            if (uri != null) {
                player.playWhenReady = autoPlay
                player.setMediaItem(MediaItem.fromUri(uri))
                player.prepare()
            }

            onDispose {
                player.playWhenReady = false
            }

        }

    }

}