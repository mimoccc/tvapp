/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.view.Window
import android.widget.ImageButton
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.allViews
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView

@Suppress("unused")
object ViewExt {

    inline fun <reified T : View> View.findView(): View? =
        allViews.filter { v -> v is T }.firstOrNull()

    fun captureView(view: View, window: Window, bitmapCallback: (Bitmap) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val location = IntArray(2)
            view.getLocationInWindow(location)
            PixelCopy.request(
                window,
                Rect(
                    location[0],
                    location[1],
                    location[0] + view.width,
                    location[1] + view.height
                ),
                bitmap,
                {
                    if (it == PixelCopy.SUCCESS) {
                        bitmapCallback.invoke(bitmap)
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } else {
            val tBitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.RGB_565
            )
            val canvas = Canvas(tBitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
            bitmapCallback.invoke(tBitmap)
        }
    }

    val PlayerView.controller: PlayerControlView?
        @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
        get() = allViews.firstOrNull { v -> v is PlayerControlView } as? PlayerControlView

    val PlayerView.buttons: List<ImageButton>
        get() = allViews.map { v -> v as? ImageButton }.filterNotNull().toList()

    fun tintButton(button: ImageButton, color: Int) {
        val drawable = DrawableCompat.wrap(button.drawable)
        DrawableCompat.setTintList(drawable.mutate(), ColorStateList.valueOf(color))
        button.setImageDrawable(drawable)
    }

    fun tintButtonBackground(button: ImageButton, color: Int) {
        val drawable = DrawableCompat.wrap(button.background)
        DrawableCompat.setTintList(drawable.mutate(), ColorStateList.valueOf(color))
        button.setImageDrawable(drawable)
    }

}
