/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap

object DrawableExt {

    fun Drawable.asImageBitmap(
        width: Int = intrinsicWidth,
        height: Int = intrinsicHeight
    ): ImageBitmap = toBitmap(width, height).asImageBitmap()

}