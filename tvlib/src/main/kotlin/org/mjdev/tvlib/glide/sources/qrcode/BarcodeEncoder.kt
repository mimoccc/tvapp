/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

@Suppress("unused")
object BarcodeEncoder {

    private fun createBitmap(
        matrix: BitMatrix,
        backgroundColor: Int = WHITE,
        foregroundColor: Int = BLACK
    ): Bitmap {
        val width = matrix.width
        val height = matrix.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (matrix[x, y]) foregroundColor else backgroundColor
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    @Throws(WriterException::class)
    private fun encode(contents: String?, format: BarcodeFormat?, width: Int, height: Int): BitMatrix {
        return try {
            MultiFormatWriter().encode(contents, format, width, height)
        } catch (e: WriterException) {
            throw e
        } catch (e: Exception) {
            throw WriterException(e)
        }
    }

    @Throws(WriterException::class)
    private fun encode(
        contents: String?,
        format: BarcodeFormat?,
        width: Int,
        height: Int,
        hints: Map<EncodeHintType?, *>?
    ): BitMatrix {
        return try {
            MultiFormatWriter().encode(contents, format, width, height, hints)
        } catch (e: WriterException) {
            throw e
        } catch (e: Exception) {
            throw WriterException(e)
        }
    }

    @Throws(WriterException::class)
    fun encodeBitmap(
        contents: String?,
        format: BarcodeFormat?,
        width: Int,
        height: Int,
        backgroundColor: Int = WHITE,
        foregroundColor: Int = Color.BLACK,
    ): Bitmap {
        return createBitmap(
            matrix = encode(contents, format, width, height),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor
        )
    }

    @Throws(WriterException::class)
    fun encodeBitmap(
        contents: String?,
        format: BarcodeFormat?,
        width: Int,
        height: Int,
        backgroundColor: Int = WHITE,
        foregroundColor: Int = Color.BLACK,
        hints: Map<EncodeHintType?, *>?,
    ): Bitmap {
        return createBitmap(
            matrix = encode(
                contents,
                format,
                width,
                height,
                hints
            ),
            backgroundColor = backgroundColor,
            foregroundColor = foregroundColor
        )
    }

    const val WHITE = Color.WHITE

    const val BLACK = Color.BLACK

}