/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.net.Uri
import androidx.compose.runtime.Composable

interface IMediaPlayer {

    @Composable
    fun GetPlayerView()

    fun setMediaUri(uri: Uri)

    fun prepare()

    fun play()

    fun pause()

    fun resume()

    fun stop()

    fun dispose()

    fun seekTo(value: Long)

    companion object {

        val EMPTY = object : IMediaPlayer {

            @Composable
            override fun GetPlayerView() {
            }

            override fun setMediaUri(uri: Uri) {}

            override fun prepare() {}

            override fun play() {}

            override fun pause() {}

            override fun resume() {}

            override fun stop() {}

            override fun dispose() {}

            override fun seekTo(value: Long) {}
        }

    }

}