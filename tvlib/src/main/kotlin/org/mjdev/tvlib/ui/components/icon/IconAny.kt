/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.icon

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.LocalContentColor
import coil.compose.AsyncImage
import org.mjdev.tvlib.extensions.DrawableExt.asImageBitmap
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import java.net.URL

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun IconAny(
    modifier: Modifier = Modifier,
    src: Any? = Icons.Default.Menu,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) = BoxWithConstraints(
    modifier = modifier
) {

    val width = constraints.minWidth
    val height = constraints.minHeight

    when (src) {
        null -> Icon(
            ColorDrawable(0).asImageBitmap(width, height),
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is Bitmap -> Icon(
            src.asImageBitmap(),
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is ImageBitmap -> Icon(
            src,
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is Drawable -> Icon(
            src.asImageBitmap(width, height),
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is Int -> Icon(
            painterResource(src),
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is Color -> Icon(
            ColorDrawable(0).asImageBitmap(width, height),
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        is URL -> AsyncImage(
            model = src,
            contentDescription = contentDescription,
            modifier = modifier.recomposeHighlighter(),
            colorFilter = ColorFilter.tint(tint)
        )

        is Uri -> AsyncImage(
            model = src,
            contentDescription = contentDescription,
            modifier = modifier.recomposeHighlighter(),
            colorFilter = ColorFilter.tint(tint)
        )

        is String -> AsyncImage(
            model = src,
            contentDescription = contentDescription,
            modifier = modifier.recomposeHighlighter(),
            colorFilter = ColorFilter.tint(tint)
        )

        is ImageVector -> Icon(
            src,
            contentDescription,
            modifier.recomposeHighlighter(),
            tint
        )

        else -> throw (RuntimeException("Unknown image format $src."))
    }

}