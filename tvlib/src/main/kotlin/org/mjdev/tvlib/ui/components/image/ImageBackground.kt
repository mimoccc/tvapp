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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.request.CachePolicy
import coil.request.ImageRequest
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.BitmapExt.majorColor
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageLoader
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage

@Previews
@Composable
fun ImageBackground(
    modifier: Modifier = Modifier,
    initialColor: Color = Color.DarkGray,
    imageState: MutableState<Any?> = mutableStateOf(initialColor),
    imageFromItem: (data:Any?) -> Any? = { data ->
            val photo = (data as? ItemPhoto)?.uri
            val img = (data as? ItemWithImage<*>)?.image
            val background = (data as? ItemWithBackground<*>)?.background
            val color = data as? Color
            val string = data.toString()
            color ?: photo ?: img ?: background ?: string
    },
    shape: Shape = RectangleShape,
    transform: ((color: Color) -> Brush)? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable (bckColor: Color) -> Unit = {}
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
                background(transform!!.invoke(bckColor.value), shape)
            },
        contentAlignment = contentAlignment
    ) {
        content(bckColor.value)
    }
    LaunchedEffect(imageState.value) {
        bckColor.value = imageLoader.execute(
            ImageRequest.Builder(context)
                .data(imageFromItem(imageState.value))
                .diskCachePolicy(CachePolicy.DISABLED)
                .build()
        ).drawable?.toBitmap()?.majorColor(Color.Black) ?: Color.Black
    }
}