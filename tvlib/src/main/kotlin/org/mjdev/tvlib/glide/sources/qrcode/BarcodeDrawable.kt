/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.qrcode

import android.graphics.*
import android.graphics.drawable.Drawable
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "unused")
class BarcodeDrawable(
    val text: String,
    val format: BarcodeFormat,
    val size: Int = -1,
    private val foregroundColor: Int = BLACK,
    val backgroundColor: Int = WHITE
) : Drawable() {

    private var paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    override fun getIntrinsicWidth(): Int {
        return if (size > 0) size else super.getIntrinsicWidth()
    }

    override fun getIntrinsicHeight(): Int {
        return if (size > 0) size else super.getIntrinsicHeight()
    }

    override fun draw(canvas: Canvas) {
        generateBarcode(
            text,
            bounds.width(),
            bounds.height(),
            format,
            foregroundColor,
            backgroundColor
        )?.also { bmp ->
            canvas.drawBitmap(bmp, null, bounds, paint)
            bmp.recycle()
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith(
            "PixelFormat.OPAQUE",
            "android.graphics.PixelFormat"
        )
    )
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    private fun generateBarcode(
        text: String?,
        width: Int = size,
        height: Int = size,
        barcodeFormat: BarcodeFormat = BarcodeFormat.QR_CODE,
        foregroundColor: Int = BLACK,
        backgroundColor: Int = WHITE
    ): Bitmap? {
        return try {
            BarcodeEncoder.encodeBitmap(
                contents = text,
                format = barcodeFormat,
                width = if (width > 0) width else DEFAULT_WIDTH,
                height = if (height > 0) height else DEFAULT_HEIGHT,
                backgroundColor = backgroundColor,
                foregroundColor = foregroundColor,
            )
        } catch (e: WriterException) {
            Timber.e(e)
            null
        }
    }

    companion object {

        /**
         * Size of EAN8
         */
        const val LENGTH_EAN_8 = 8

        /**
         * Size of EAN13
         */
        const val LENGTH_EAN_13 = 13

        /**
         * Default width of drawable when not present
         */
        const val DEFAULT_WIDTH = 578

        /**
         * Default height of drawable when not present
         */
        const val DEFAULT_HEIGHT = 342

        /**
         * Guess type of requested barcode
         * @param text String?
         * @return BarcodeFormat
         */
        fun guessType(text: String?): BarcodeFormat {
            return if (text == null) {
                BarcodeFormat.CODE_128
            } else {
                when (text.length) {
                    LENGTH_EAN_8 -> BarcodeFormat.EAN_8
                    LENGTH_EAN_13 -> BarcodeFormat.EAN_13
                    else -> BarcodeFormat.CODE_128
                }
            }
        }

        const val BLACK = BarcodeEncoder.BLACK
        const val WHITE = BarcodeEncoder.WHITE

    }

}
