/*
* Copyright (c) Milan Jurkulák 2023.
*  Contact:
*  e: mimoccc@gmail.com
*  e: mj@mjdev.org
*  w: https://mjdev.org
*/

@file:Suppress("unused")

package org.mjdev.tvlib.ui.components.media

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerControlView
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.ui.components.audiopreview.AudioPreview.Companion.AudioPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@SuppressLint("UnsafeOptInUsageError")
@TvPreview
@Composable
fun MediaPlayerContainer(
    modifier: Modifier = Modifier,
    uri: Uri = Uri.EMPTY,
    isAudio: Boolean = false,
    title: String? = null,
    subtitle: String? = null,
    autoPlay: Boolean = true,
    startSeek: Long = 0L,
    mediaPlayer: IMediaPlayer = MediaPlayerContainerDefaults.exoPlayer,
) {
    val isEdit = isEditMode()
    val state = rememberMediaPlayerState(
        player = mediaPlayer,
        uri = uri,
        autoPlay = autoPlay,
        startSeek = startSeek,
        title = title,
        subtitle = subtitle
    )
    Box(
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxSize()
    ) {
        if (isAudio) {
            AudioPreview(
                modifier = Modifier.fillMaxSize(),
                state = state,
                uri = uri
            )
        }
        mediaPlayer.GetPlayerView()
        if (isEdit) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    PlayerControlView(context).apply {
                        player = mediaPlayer
                    }
                }
            )
        }
    }
    DisposableEffect(state) {
        if (state.hasMediaToPlay) {
            mediaPlayer.setMediaItem(state.mediaItem)
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