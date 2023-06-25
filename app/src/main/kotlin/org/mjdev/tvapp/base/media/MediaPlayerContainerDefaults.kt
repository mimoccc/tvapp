/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.media

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer

@Suppress("unused", "MemberVisibilityCanBePrivate")
object MediaPlayerContainerDefaults {

    val engines
        @Composable
        get() = arrayOf(
            exoPlayer, internalPlayer
        )

    val exoPlayer
        @Composable
        get() = run {
            val context = LocalContext.current
            remember { ExoPlayerImpl(ExoPlayer.Builder(context).build()) }
        }

    val internalPlayer
        @Composable
        get() = run {
            remember { NativePlayerImpl(MediaPlayer()) }
        }

}