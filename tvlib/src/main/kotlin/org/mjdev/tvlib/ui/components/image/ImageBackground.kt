/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.request.ImageRequest
import org.mjdev.tvlib.extensions.BitmapExt.majorColor
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageLoader
import org.mjdev.tvlib.extensions.ModifierExt.conditional

@Composable
fun ImageBackground(
    modifier: Modifier = Modifier,
    image: Any? = null,
    transform: ((color: Color) -> Brush)? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val imageLoader = rememberImageLoader()
    val bckColor = remember { mutableStateOf(Color.Transparent) }
    Box(
        modifier = modifier
            .conditional(transform == null) {
                background(bckColor.value, RectangleShape)
            }
            .conditional(transform != null) {
                background(transform!!.invoke(bckColor.value), RectangleShape)
            },
        contentAlignment = contentAlignment
    ) {
        content()
    }
    LaunchedEffect(image) {
        bckColor.value = imageLoader.execute(
            ImageRequest.Builder(context).data(image).build()
        ).drawable?.toBitmap()?.majorColor(Color.Black) ?: Color.Black
    }
}