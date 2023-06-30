/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.graphics.Bitmap

object BitmapExt {

    fun Bitmap.bimapCopy() = copy(Bitmap.Config.ARGB_8888, false)

}