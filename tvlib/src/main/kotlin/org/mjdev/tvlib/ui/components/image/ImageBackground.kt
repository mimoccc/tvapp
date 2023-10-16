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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberColorFromImage
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageFromItem
import org.mjdev.tvlib.extensions.ModifierExt.conditional

@Previews
@Composable
fun ImageColoredBackground(
    modifier: Modifier = Modifier,
    image: Any? = null,
    shape: Shape = RectangleShape,
    transform: ((color: Color) -> Brush)? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable (bckColor: Color) -> Unit = {}
) {
    val imageSrc = rememberImageFromItem(image)
    val bckColor = rememberColorFromImage(imageSrc.value)

    Box(
        modifier = modifier
            .conditional(transform == null) {
                background(bckColor.value, RectangleShape)
            }
            .conditional(transform != null) {
                background(
                    transform!!.invoke(bckColor.value),
                    shape
                )
            },
        contentAlignment = contentAlignment
    ) {
        content(bckColor.value)
    }
}


