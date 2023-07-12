/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.annotation.FloatRange
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.ui.components.image.PhotoImage

@Composable
fun FadingPhotoImage(
    modifier: Modifier = Modifier,
    initialImage: Any? = null,
    fadingImageState: MutableState<Any?> = remember { mutableStateOf(initialImage) },
    animationDuration: Int = 1000,
    alpha: Float = 0.6f,
    blurRadius: Dp = 4.dp,
    @FloatRange(from = 0.0, to = 10.0)
    contrast: Float = 4f,
    @FloatRange(from = -255.0, to = 255.0)
    brightness: Float = -255f,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: @Composable () -> Unit = {},
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    roundCornerSize: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
) {
    Crossfade(
        fadingImageState.value,
        animationSpec = tween(animationDuration)
    ) { targetState ->
        PhotoImage(
            modifier = modifier
                .alpha(alpha)
                .blur(radius = blurRadius),
            src = targetState,
            contrast = contrast,
            brightness = brightness,
            contentScale = contentScale,
            placeholder = placeholder,
            borderSize = borderSize,
            borderColor = borderColor,
            shape = shape,
            colorFilter = colorFilter,
            contentDescription = contentDescription,
            alignment = alignment
        )
    }
}