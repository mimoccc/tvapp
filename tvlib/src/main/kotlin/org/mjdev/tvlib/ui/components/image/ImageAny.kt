/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.image

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.DrawableExt.asImageBitmap
import java.net.URL

@Suppress("MoveVariableDeclarationIntoWhen")
@SuppressLint("ModifierParameter")
@Previews
@Composable
fun ImageAny(
    modifier: Modifier = Modifier,
    src: Any? = null,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    placeholder : @Composable () -> Unit = {
        Image(
            painterResource(R.drawable.broken_image),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )
    }
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    val width = if (constraints.minWidth == 0) 1 else constraints.minWidth
    val height = if (constraints.minHeight == 0) 1 else constraints.minHeight

    val imageSrc = if(src is State<*>) src.value else src

    when (imageSrc) {

        null, Unit, "0", 0 -> {
            placeholder()
        }

        Color -> Image(
            ColorDrawable((imageSrc as Color).toArgb()).asImageBitmap(width, height),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Bitmap -> Image(
            imageSrc.asImageBitmap(),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ImageBitmap -> Image(
            imageSrc,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ColorDrawable -> Image(
            imageSrc.asImageBitmap(1, 1),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Drawable -> Image(
            imageSrc.asImageBitmap(),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Int -> Image(
            painterResource(imageSrc),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is URL -> GlideImage(
            imageModel = { imageSrc },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = alignment,
                contentDescription = contentDescription,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        )

        is Uri -> GlideImage(
            imageModel = { imageSrc },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = alignment,
                contentDescription = contentDescription,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        )

        is String -> GlideImage(
            imageModel = { imageSrc },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = alignment,
                contentDescription = contentDescription,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        )

        is ImageVector -> Image(
            imageSrc,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        else -> throw (RuntimeException("Unknown image format $imageSrc."))

    }

}