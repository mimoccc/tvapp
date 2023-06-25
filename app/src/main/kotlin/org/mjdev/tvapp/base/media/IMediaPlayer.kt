/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.media

import android.net.Uri
import androidx.compose.runtime.Composable

interface IMediaPlayer {

    @Composable
    fun PlayerView()

    fun setMediaUri(uri: Uri)

    fun prepare()

    fun play()

    fun pause()

    fun resume()

    fun stop()

    fun dispose()

    fun seekTo(value: Long)

}