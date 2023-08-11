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
import androidx.media3.common.MediaItem
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
    items: List<MediaItem> = emptyList(),
    itemToPlay: Int = 0,
    mediaPlayer: IMediaPlayer = remember {
        if (isEdit) {
            IMediaPlayer.EMPTY
        } else {
            MediaPlayerContainerDefaults.exoPlayer(context)
        }
    },
    state: MediaPlayerState = rememberMediaPlayerState(
        player = mediaPlayer,
        items = items,
        itemToPlay = itemToPlay,
        autoPlay = autoPlay,
        startSeek = startSeek,
    )
) {
    Box(
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxSize()
    ) {
        mediaPlayer.GetPlayerView(
            modifier = modifier
                .recomposeHighlighter()
                .fillMaxSize()
        )
        if (isEdit) {
            PlayerControlView(
                modifier = modifier
                    .recomposeHighlighter()
                    .fillMaxSize()
            )
        }
    }
    DisposableEffect(state) {
        if (state.hasMediaToPlay) {
            mediaPlayer.setMediaItems(
                state.mediaItems.value,
                state.currentItemIndex.intValue,
                state.currentPosition.longValue
            )
            mediaPlayer.prepare()
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