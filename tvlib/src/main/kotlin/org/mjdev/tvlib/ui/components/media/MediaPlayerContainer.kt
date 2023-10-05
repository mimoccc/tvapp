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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.ModifierExt.swipeGestures
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@SuppressLint("UnsafeOptInUsageError")
@Previews
@Composable
fun MediaPlayerContainer(
    modifier: Modifier = Modifier,
    state: MediaPlayerState = rememberMediaPlayerState(),
) {
    val nextItem: () -> Unit = { state.player.seekToNext() }
    val prevItem: () -> Unit = { state.player.seekToPrevious() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .swipeGestures(
                onSwipeLeft = { nextItem() },
                onSwipeRight = { prevItem() },
                onSwipeUp = { nextItem() },
                onSwipeDown = { prevItem() },
            )
            .recomposeHighlighter()
    ) {
        state.player.GetPlayerView()
        PlayerControlView(
            state = state
        )
    }
    DisposableEffect(Unit) {
        state.startPlay()
        onDispose {
            state.dispose()
        }
    }
}