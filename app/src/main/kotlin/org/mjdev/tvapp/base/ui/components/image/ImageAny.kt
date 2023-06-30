/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.image

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.extensions.DrawableExt.asImageBitmap
import java.net.URL

// todo placeholder
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun ImageAny(
    modifier: Modifier = Modifier,
    src: Any? = R.drawable.milanj,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    placeholder: @Composable () -> Unit = {}, // todo
) = BoxWithConstraints(
    modifier = modifier
) {

    val width = if(constraints.minWidth  ==0) 1 else constraints.minWidth
    val height = if(constraints.minHeight  ==0) 1 else constraints.minHeight

    when (src) {

        null -> placeholder()

        Color -> Image(
            ColorDrawable((src as Color).toArgb()).asImageBitmap(width, height),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Bitmap -> Image(
            src.asImageBitmap(),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ImageBitmap -> Image(
            src,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ColorDrawable -> Image(
            src.asImageBitmap(1, 1),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Drawable -> Image(
            src.asImageBitmap(),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Int -> Image(
            painterResource(src),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is URL -> AsyncImage(
            modifier = modifier,
            model = src,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )

        is Uri -> AsyncImage(
            modifier = modifier,
            model = src,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )

        is String -> AsyncImage(
            modifier = modifier,
            model = src,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )

        is ImageVector -> Image(
            src,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        else -> throw (RuntimeException("Unknown image format $src."))

    }

}