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
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerControlView
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@SuppressLint("UnsafeOptInUsageError")
@TvPreview
@Composable
fun MediaPlayerContainer(
    modifier: Modifier = Modifier,
    autoPlay: Boolean = true,
    startSeek: Long = 0L,
    context: Context = LocalContext.current,
    isEdit: Boolean = isEditMode(),
    items: List<Any?> = emptyList(),
    itemToPlay: Int = 0
) {
    val mediaPlayer: IMediaPlayer = remember {
        if (isEdit) {
            IMediaPlayer.EMPTY
        } else {
            MediaPlayerContainerDefaults.exoPlayer(context)
        }
    }
    val state = rememberMediaPlayerState(
        player = mediaPlayer,
        items = items,
        itemToPlay = itemToPlay,
        autoPlay = autoPlay,
        startSeek = startSeek,
    )
    Box(
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxSize()
    ) {
        mediaPlayer.GetPlayerView()
        if (isEdit) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    PlayerControlView(context)
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