/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.util.UnstableApi
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode

@UnstableApi @Suppress("unused", "MemberVisibilityCanBePrivate")
object MediaPlayerContainerDefaults {

    val isEdit: Boolean
        @Composable
        get() = isEditMode()

    val engines: Array<IMediaPlayer?>
        @Composable
        get() = arrayOf(
            exoPlayer,
            internalPlayer
        )

    val  exoPlayer: IMediaPlayer
        @Composable
        get() = run {
            val context = LocalContext.current
            if (isEdit) IMediaPlayer.EMPTY
            else remember {
                ExoPlayerImpl(context)
            }
        }

    val internalPlayer: IMediaPlayer
        @Composable
        get() = run {
            val context = LocalContext.current
            if (isEdit) IMediaPlayer.EMPTY
            else remember {
                NativePlayerImpl(context)
            }
        }

}