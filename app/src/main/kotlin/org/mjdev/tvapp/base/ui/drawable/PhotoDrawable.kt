package org.mjdev.tvapp.base.ui.drawable

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable

class PhotoDrawable(
    d: Drawable?
) : LayerDrawable(arrayOf(d, getPDrawable(d), getLight(d))) {

    constructor(bmp: Bitmap) : this(BitmapDrawable(bmp))

    init {
        setDither(true)
        setLayerInset(0, 2, 2, 2, 2)
    }

    companion object {

        private fun getPDrawable(bmp: Bitmap): Drawable {
            val gd = GradientDrawable(
                GradientDrawable.Orientation.TR_BL, intArrayOf(
                    centerColor,
                    roundColor
                )
            )
            gd.gradientType = GradientDrawable.RADIAL_GRADIENT
            gd.gradientRadius = (bmp.width / 2).toFloat()
            return gd
        }

        private fun getLight(d: Drawable?): Drawable {
//        ShapeDrawable sd = new ShapeDrawable(new OvalShape());
//        sd.setIntrinsicHeight(d.getIntrinsicWidth());
//        sd.setIntrinsicWidth(d.getIntrinsicHeight());
//        sd.setBounds(new Rect(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight()));
//        sd.getPaint().setColor(getLightColor());
//        return sd;
            return ColorDrawable(0)
        }

        private fun getPDrawable(d: Drawable?): Drawable {
            val gd = GradientDrawable(
                GradientDrawable.Orientation.TR_BL,
                intArrayOf(centerColor, roundColor)
            )
            gd.gradientType = GradientDrawable.RADIAL_GRADIENT
            if (d != null) {
                gd.gradientRadius = (d.intrinsicWidth / 2).toFloat()
            }
            return gd
        }

        private val roundColor: Int = 0xd0000000.toInt()
        private val lightColor: Int = 0x20ffffff
        private val centerColor: Int = 0x00000000

    }

}