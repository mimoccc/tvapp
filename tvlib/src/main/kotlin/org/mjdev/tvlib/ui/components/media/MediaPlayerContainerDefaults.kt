/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.content.Context

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Suppress("unused", "MemberVisibilityCanBePrivate")
object MediaPlayerContainerDefaults {

    fun exoPlayer(context: Context): IMediaPlayer =
        ExoPlayerImpl(context)

    fun internalPlayer(context: Context): IMediaPlayer =
        NativePlayerImpl(context)

}