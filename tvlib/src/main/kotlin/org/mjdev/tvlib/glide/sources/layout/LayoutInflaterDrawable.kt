/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes

/**
 * Custom drawable that inflate view to itself
 * Used for loading image error customized replacement
 * @property context Context
 * @property resId Int
 * @property paint Paint
 * @constructor
 */
@Suppress("unused")
class LayoutInflaterDrawable(
    val context: Context,
    @LayoutRes val resId: Int
) : Drawable() {
    private var paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    override fun draw(canvas: Canvas) {
        val displaySize = context.resources.displayMetrics
        var width = bounds.width()
        var height = bounds.height()
        if ((width == 0 && height == 0)) {
            width = intrinsicWidth
            height = intrinsicHeight
        }
        if ((width == 0 && height == 0)) {
            width = displaySize.widthPixels
            height = displaySize.heightPixels
        }
        resId.let {
            if (it == View.NO_ID)
                View(context)
            else
                LayoutInflater.from(context).inflate(resId, null, false)
        }.let { v ->
            v.measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
            )
            v.layout(0, 0, v.measuredWidth, v.measuredHeight)
            v.background?.draw(canvas)
            v.draw(canvas)
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith(
            "PixelFormat.TRANSLUCENT",
            "android.graphics.PixelFormat"
        )
    )
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    companion object {
        fun Int.toDrawable(context: Context): Drawable {
            return LayoutInflaterDrawable(context, this)
        }
    }
}
