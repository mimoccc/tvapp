/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.audiopreview

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import org.mjdev.tvlib.ui.components.audiopreview.dailymotion.DailyMotionVideoView
import timber.log.Timber
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata

@SuppressLint("ViewConstructor")
class AudioPreview constructor(
    context: Context,
    val uri: Uri = Uri.EMPTY
) : FrameLayout(context) {

    // todo : multiple engines
    private val previewEngine: IPreviewEngine by lazy {
        DailyMotionVideoView(context)
    }

    private var networkListener: NetworkReceiver? = null

    init {
        addView(
            previewEngine as View,
            LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
        )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        networkListener = NetworkReceiver(context, object : NetworkListener {
            override fun onNetworkAvailable() {
                this@AudioPreview.onNetworkAvailable()
            }

            override fun onNetworkUnavailable() {
                this@AudioPreview.onNetworkUnavailable()
            }
        })
    }

    fun onNetworkUnavailable() {
        previewEngine.pause()
    }

    fun onNetworkAvailable() {
        if (uri != Uri.EMPTY) {
            previewEngine.searchAndPlayIfFound(
                filePath = uri.toString(),
                muted = true,
                success = null,
                error = { error ->
                    Timber.e(error)
                }
            )
        }
    }

    fun setData(mediaItem: MediaItem?) {
        if (mediaItem?.mediaMetadata?.mediaType == MediaMetadata.MEDIA_TYPE_MUSIC) {
            previewEngine.searchAndPlayIfFound(mediaItem.localConfiguration?.uri?.toString())
        } else {
            previewEngine.stop()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        networkListener?.unregister(context)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    fun stop() {
        previewEngine.stop()
    }

    fun pause() {
        previewEngine.pause()
    }

    fun resume() {
        previewEngine.resume()
    }

}