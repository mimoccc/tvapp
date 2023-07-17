/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.annotation.SuppressLint
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.allViews
import androidx.media3.ui.PlayerControlView
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState

@SuppressLint("PrivateResource", "ModifierParameter")
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@TvPreview
@Composable
fun MediaPlayerControls(
    state: MediaPlayerState = rememberMediaPlayerState(null),
    modifier: Modifier = Modifier,
    views: MutableMap<Int, View?> = remember { mutableMapOf() }
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            PlayerControlView(context).apply {
                player = state.player
                allViews.forEach { v ->
                    if (v.id >= 0) views[v.id] = v
                }
            }
        }
    )

}