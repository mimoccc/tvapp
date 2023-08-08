/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

@Suppress("unused")
interface DailyMotionVideoViewEventListener {
    fun onError(message: String?)
    fun onTimeChanged(time: Int)
    fun onEndReached()
    fun onPlaying(duration: Int)
    fun onPaused()
    fun onSeekPerformed()
    fun onBuffering(buffering: Int)
    fun onReady()
    fun onNetworkAvailable()
    fun onNetworkUnAvailable()
}