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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.DrawableExt.asImageBitmap
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import java.net.URL

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
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    val width = if (constraints.minWidth == 0) 1 else constraints.minWidth
    val height = if (constraints.minHeight == 0) 1 else constraints.minHeight

    val imageSrc = if(src is State<*>) src.value else src

    when (imageSrc) {

        null, Unit, "0", 0 -> {
            Image(
                painterResource(R.drawable.broken_image),
                contentDescription,
                modifier
                    .padding(16.dp)
                    .recomposeHighlighter(),
                alignment,
                contentScale,
                alpha,
                colorFilter
            )
        }

        Color -> Image(
            ColorDrawable((imageSrc as Color).toArgb()).asImageBitmap(width, height),
            contentDescription,
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Bitmap -> Image(
            imageSrc.asImageBitmap(),
            contentDescription,
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ImageBitmap -> Image(
            imageSrc,
            contentDescription,
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is ColorDrawable -> Image(
            imageSrc.asImageBitmap(1, 1),
            contentDescription,
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Drawable -> Image(
            imageSrc.asImageBitmap(),
            contentDescription,
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        is Int -> Image(
            painterResource(imageSrc),
            contentDescription,
            modifier.recomposeHighlighter(),
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
            modifier.recomposeHighlighter(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )

        else -> throw (RuntimeException("Unknown image format $imageSrc."))

    }

}