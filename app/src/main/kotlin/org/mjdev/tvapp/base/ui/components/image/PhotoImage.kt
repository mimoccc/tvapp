/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.image

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import org.mjdev.tvapp.base.extensions.ColorFilterExt.contrastAndBrightness
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil.ImageLoader
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.DrawableExt.asPhoto
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter

@SuppressLint("ModifierParameter")
@Preview
@Composable
fun PhotoImage(
    src: Any? = R.drawable.milanj,
    placeholder: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    @FloatRange(from = 0.0, to = 10.0)
    contrast: Float = 5f,
    @FloatRange(from = -255.0, to = 255.0)
    brightness: Float = -255f,
    borderSize: Dp = 2.dp,
    borderColor: Color = Color.Black,
    roundCornerSize: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null,
) {
    val isEdit = isEditMode()
    val context = LocalContext.current
    // todo coil image does not shows
    CoilImage(
        imageLoader = {
            ImageLoader(context).newBuilder()
                .allowHardware(false)
                .build()
        },
        imageModel = { src },
        modifier = modifier.recomposeHighlighter()
            .conditional(isEdit) {
                aspectRatio(16f / 9f).defaultMinSize(80.dp)
            }
            .border(
                BorderStroke(borderSize, borderColor),
                shape
            ),
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = alignment,
            contentDescription = contentDescription,
            alpha = alpha,
            colorFilter = colorFilter,
        ),
        failure = {
            placeholder()
        },
        onImageStateChanged = {
        },
        success = { state, _ ->
            ImageAny(
                src = state.imageBitmap
                    ?.asAndroidBitmap()
                    ?.toDrawable(context.resources)
                    ?.asPhoto(),
                modifier = modifier.recomposeHighlighter(),
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = contrastAndBrightness(
                    contrast,
                    brightness
                ),
                contentDescription = contentDescription,
            )
        }
    )
}