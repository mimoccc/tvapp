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
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode

@Suppress("unused", "MemberVisibilityCanBePrivate")
object MediaPlayerContainerDefaults {

    val engines: Array<IMediaPlayer?>
        @Composable
        get() = arrayOf(
            exoPlayer,
            internalPlayer
        )

    val exoPlayer: IMediaPlayer
        @Composable
        get() = run {
            val context = LocalContext.current
            val isEdit = isEditMode()
            remember {
                if (isEdit) IMediaPlayer.EMPTY
                else ExoPlayerImpl(ExoPlayer.Builder(context).build())
            }
        }

    val internalPlayer: IMediaPlayer
        @Composable
        get() = run {
            val isEdit = isEditMode()
            remember {
                if (isEdit) IMediaPlayer.EMPTY
                else NativePlayerImpl(MediaPlayer())
            }
        }

}