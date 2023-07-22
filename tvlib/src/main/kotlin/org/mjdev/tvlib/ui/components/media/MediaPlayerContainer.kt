/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.ui.components.media

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@TvPreview
@Composable
fun MediaPlayerContainer(
    modifier: Modifier = Modifier,
    uri: Uri? = Uri.EMPTY,
    autoPlay: Boolean = true,
    startSeek: Long = 0L,
    mediaPlayer: IMediaPlayer = MediaPlayerContainerDefaults.exoPlayer,
) {
    val state = rememberMediaPlayerState(
        mediaPlayer,
        uri ?: Uri.EMPTY,
        autoPlay,
        startSeek
    )
    Box(
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxSize()
    ) {
        mediaPlayer.GetPlayerView()
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
            state.stop()
            state.dispose()
        }
    }
}