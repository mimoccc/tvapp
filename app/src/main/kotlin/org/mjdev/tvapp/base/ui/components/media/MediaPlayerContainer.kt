/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.ui.components.media

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@TvPreview
@Composable
fun MediaPlayerContainer(
    modifier: Modifier = Modifier,
    uri: Uri? = Uri.EMPTY,
    autoPlay: Boolean = true,
    startSeek: Long = 0L,
    mediaPlayer: IMediaPlayer = MediaPlayerContainerDefaults.exoPlayer,
    background: @Composable (state: MediaPlayerState) -> Unit = {},
    mediaPlayerOverlay: @Composable (state: MediaPlayerState) -> Unit = {},
    mediaPlayerControls: @Composable (state: MediaPlayerState) -> Unit = {}
) {
    val state = rememberMediaPlayerState(uri ?: Uri.EMPTY, autoPlay, startSeek)
    Box(
        modifier = modifier.recomposeHighlighter()
    ) {
        background(state)
        mediaPlayer.GetPlayerView()
        mediaPlayerOverlay(state)
        mediaPlayerControls(state)
    }
    DisposableEffect(state) {
        if (state.hasMediaToPlay) {
            mediaPlayer.setMediaUri(state.mediaUri.value)
            mediaPlayer.prepare()
            if (state.seek.value > 0L) {
                mediaPlayer.seekTo(state.seek.value)
            }
            if (state.isAutoPlay) {
                mediaPlayer.play()
            }
        }
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.dispose()
        }
    }
}