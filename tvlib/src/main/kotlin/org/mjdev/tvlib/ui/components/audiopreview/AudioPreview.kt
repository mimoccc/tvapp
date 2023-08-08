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
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.mjdev.tvlib.ui.components.audiopreview.dailymotion.DailyMotionVideoView
import org.mjdev.tvlib.ui.components.media.MediaPlayerState
import timber.log.Timber

//todo multiple engines
@SuppressLint("ViewConstructor")
class AudioPreview constructor(
    context: Context,
    val uri: Uri = Uri.EMPTY
) : FrameLayout(context) {

    private val previewEngine: IPreviewEngine by lazy {
        DailyMotionVideoView(context)
    }

    private var networkListener: NetworkReceiver? = null

    init {
        background = ColorDrawable(0)
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
                success = {},
                error = { error ->
                    Timber.e(error)
                }
            )
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        previewEngine.pause()
        if (networkListener != null) {
            networkListener!!.unregister(context)
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    companion object {

        // todo video stop pause resume & etc
        @Suppress("UNUSED_PARAMETER")
        @Composable
        fun AudioPreview(
            modifier: Modifier = Modifier,
            uri: Uri = Uri.EMPTY,
            state: MediaPlayerState,
            onError: @Composable () -> Unit = {},
        ) {
            AndroidView(
                modifier = modifier,
                factory = { context ->
                    AudioPreview(context, uri)
                }
            )
        }

    }

}