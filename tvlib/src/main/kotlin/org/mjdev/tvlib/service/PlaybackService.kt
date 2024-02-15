/*
*  Copyright (c) Milan Jurkulák 2023.
*  Contact:
*  e: mimoccc@gmail.com
*  e: mj@mjdev.org
*  w: https://mjdev.org
*/

package org.mjdev.tvlib.service

import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

@UnstableApi
class PlaybackService : MediaSessionService() {

    private var mediaSession: MediaSession? = null

    val player by lazy {
        ExoPlayer.Builder(baseContext)
            .setDeviceVolumeControlEnabled(true)
            .setPauseAtEndOfMediaItems(true)
            .setSkipSilenceEnabled(true)
            .setUseLazyPreparation(true)
            .setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .setAllowedCapturePolicy(C.ALLOW_CAPTURE_BY_ALL)
                    .build(), true
            ).build()
    }

    override fun onGetSession(
        controllerInfo: MediaSession.ControllerInfo
    ): MediaSession? = mediaSession

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

}
