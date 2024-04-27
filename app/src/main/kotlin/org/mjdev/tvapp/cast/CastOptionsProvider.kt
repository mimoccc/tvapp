/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.cast

import android.content.Context
import androidx.annotation.Keep
import com.google.android.gms.cast.CastMediaControlIntent
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider
import com.google.android.gms.cast.framework.media.CastMediaOptions
import com.google.android.gms.cast.framework.media.ImageHints
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.gms.cast.framework.media.MediaIntentReceiver
import com.google.android.gms.cast.framework.media.NotificationOptions
import com.google.android.gms.common.images.WebImage
import org.mjdev.tvapp.activity.MainActivity
import com.google.android.gms.cast.MediaMetadata

@Suppress("unused")
@Keep
class CastOptionsProvider : OptionsProvider {

    private val notificationOptions by lazy {
        NotificationOptions.Builder()
            .setActions(
                listOf(
                    MediaIntentReceiver.ACTION_FORWARD,
                    MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK,
                    MediaIntentReceiver.ACTION_STOP_CASTING
                ),
                intArrayOf(
                    1,
                    2
                )
            )
            .setTargetActivityClassName(MainActivity::class.java.name)
            .build()
    }

    private val mediaOptions by lazy {
        CastMediaOptions.Builder()
            .setImagePicker(ImagePickerImpl())
            .setNotificationOptions(notificationOptions)
            .build()
    }

    override fun getCastOptions(
        context: Context
    ): CastOptions = CastOptions.Builder()
        .setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
        .setCastMediaOptions(mediaOptions)
        .setRemoteToLocalEnabled(true)
        .build()

    override fun getAdditionalSessionProviders(
        context: Context
    ): MutableList<SessionProvider>? = null

    private class ImagePickerImpl : ImagePicker() {

        override fun onPickImage(mediaMetadata: MediaMetadata?, hints: ImageHints): WebImage? {
            val type = hints.type
            if ((mediaMetadata == null) || !mediaMetadata.hasImages()) {
                return null
            }
            val images: List<WebImage> = mediaMetadata.images
            return if (images.size == 1) {
                images[0]
            } else {
                if (type == IMAGE_TYPE_MEDIA_ROUTE_CONTROLLER_DIALOG_BACKGROUND) {
                    images[0]
                } else {
                    images[1]
                }
            }
        }
    }

}
