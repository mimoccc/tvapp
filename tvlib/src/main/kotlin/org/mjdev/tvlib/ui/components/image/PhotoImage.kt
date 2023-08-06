/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.image

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.coil.CoilImageState
import org.mjdev.tvlib.R
import org.mjdev.tvlib.extensions.BitmapExt.majorColor
import org.mjdev.tvlib.extensions.ColorExt.contrastAndBrightness
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageLoader
import org.mjdev.tvlib.extensions.DrawableExt.asPhoto
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import timber.log.Timber

@SuppressLint("ModifierParameter")
@Preview
@Composable
fun PhotoImage(
    src: Any? = null,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = {
        ImageAny(
            modifier = modifier,
            src = R.drawable.broken_image
        )
    },
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    @FloatRange(from = 0.0, to = 10.0)
    contrast: Float = 5f,
    @FloatRange(from = -255.0, to = 255.0)
    brightness: Float = -255f,
    borderSize: Dp = 2.dp,
    borderColor: Color = Color.Black,
    backgroundColor: Color = Color.Black,
    roundCornerSize: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null,
    onImageStateChanged: (state: CoilImageState) -> Unit = {}
) {
    val isEdit = isEditMode()
    val context = LocalContext.current
    val imageLoader = rememberImageLoader()
    val imageOptions = ImageOptions(
        contentScale = contentScale,
        alignment = alignment,
        contentDescription = contentDescription,
        alpha = alpha,
        colorFilter = colorFilter,
    )
    CoilImage(
        imageLoader = {
            imageLoader
        },
        imageModel = {
            src
        },
        modifier = modifier
            .recomposeHighlighter()
            .conditional(isEdit) {
                aspectRatio(16f / 9f).defaultMinSize(80.dp)
            }
            .border(
                BorderStroke(borderSize, borderColor),
                shape
            ),
        imageOptions = imageOptions,
        failure = { failure ->
            Timber.e(failure.reason)
            placeholder()
        },
        onImageStateChanged = { state ->
            onImageStateChanged.invoke(state)
        },
        success = { state, _ ->
            val bitmap = state.imageBitmap?.asAndroidBitmap()
            Box(
                modifier = modifier
                    .recomposeHighlighter()
                    .background(bitmap.majorColor(backgroundColor), shape),
            ) {
                ImageAny(
                    src = bitmap
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
        }
    )
}