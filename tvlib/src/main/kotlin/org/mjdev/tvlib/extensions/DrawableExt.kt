/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import org.mjdev.tvlib.ui.drawable.PhotoDrawable

@Suppress("unused")
object DrawableExt {

    // todo check width and height
    fun Drawable.asImageBitmap(
        width: Int = intrinsicWidth,
        height: Int = intrinsicHeight
    ): ImageBitmap = toBitmap(
        if (width == 0) 1 else width,
        if (height == 0) 1 else height
    ).asImageBitmap()

    fun Drawable.asPhoto(): Drawable = PhotoDrawable(this)

}
