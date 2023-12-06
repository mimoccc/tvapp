/*
 *  Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.special

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.mjdev.tvlib.annotations.Previews
import java.util.Random
import java.util.Timer
import java.util.TimerTask
import kotlin.math.max

class AnalogTvNoise @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mRandom = Random(System.currentTimeMillis())
    private var mTileWidth = 0
    private var mTileHeight = 0
    private var mScaledBitmap: Bitmap? = null
    private var mBitmap: Bitmap? = null
    private var mPixels: IntArray? = null
    private var mTimer: Timer? = null

    override fun onDraw(canvas: Canvas) {
        if (mScaledBitmap == null) return
        for (i in 0 until DIVIDER_WIDTH_SCALED) {
            canvas.drawBitmap(mScaledBitmap!!, (i * mScaledBitmap!!.width).toFloat(), 0f, null)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val tileWidth = max(1, width / DIVIDER_WIDTH)
        val tileHeight = max(1, height / DIVIDER_HEIGHT)
        if (mTileWidth != tileWidth || mTileHeight != tileHeight || mPixels == null) {
            mPixels = IntArray(tileWidth * tileHeight)
            mBitmap = Bitmap.createBitmap(tileWidth, tileHeight, Bitmap.Config.RGB_565)
        }
        mTileWidth = tileWidth
        mTileHeight = tileHeight
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startTimer(150)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTimer()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == GONE || visibility == INVISIBLE) {
            stopTimer()
        } else if (visibility == VISIBLE) {
            startTimer(0)
        }
    }

    private fun startTimer(delay: Long) {
        mTimer = Timer("", false)
        mTimer?.schedule(object : TimerTask() {
            override fun run() {
                generateNoise()
                postInvalidate()
            }
        }, delay, ANIM_INTERVAL.toLong())
    }

    private fun stopTimer() {
        mTimer?.cancel()
        mTimer?.purge()
        mTimer = null
    }

    private fun generateNoise() {
        if (mPixels == null || mBitmap == null) return
        for (i in 0 until mTileWidth * mTileHeight) {
            val pix = max(40, mRandom.nextInt(255))
            mPixels!![i] = Color.rgb(pix, pix, pix)
        }
        val width = width / DIVIDER_WIDTH_SCALED
        mBitmap?.setPixels(mPixels!!, 0, mTileWidth, 0, 0, mTileWidth, mTileHeight)
        mScaledBitmap = Bitmap.createScaledBitmap(
            mBitmap!!,
            max(1, width),
            max(1, height),
            false
        )
    }

    companion object {
        private const val DIVIDER_WIDTH = 8
        private const val DIVIDER_HEIGHT = 4
        private const val DIVIDER_WIDTH_SCALED = 2
        private const val ANIM_INTERVAL = 50
    }

}

@Previews
@Composable
fun AnalogTvNoise(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AnalogTvNoise(context)
        }
    )
}