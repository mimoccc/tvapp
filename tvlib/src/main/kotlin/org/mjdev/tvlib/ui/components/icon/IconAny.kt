/*
 *  Copyright (c) Milan Jurkulák 2024.
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.Icon
import androidx.tv.material3.LocalContentColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.DrawableExt.asImageBitmap
import java.net.URL

@SuppressLint("ModifierParameter")
@Previews
@Composable
fun IconAny(
    modifier: Modifier = Modifier,
    src: Any? = Icons.Default.Menu,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {

    val width = constraints.minWidth
    val height = constraints.minHeight

    when (src) {
        null -> Icon(
            ColorDrawable(0).asImageBitmap(width, height),
            contentDescription,
            modifier,
            tint
        )

        is Bitmap -> Icon(
            src.asImageBitmap(),
            contentDescription,
            modifier,
            tint
        )

        is ImageBitmap -> Icon(
            src,
            contentDescription,
            modifier,
            tint
        )

        is Drawable -> Icon(
            src.asImageBitmap(width, height),
            contentDescription,
            modifier,
            tint
        )

        is Int -> Icon(
            painterResource(src),
            contentDescription,
            modifier,
            tint
        )

        is Color -> Icon(
            ColorDrawable(0).asImageBitmap(width, height),
            contentDescription,
            modifier,
            tint
        )

        is URL -> GlideImage(
            imageModel = { src },
            modifier = modifier,
            imageOptions = ImageOptions(
                colorFilter = ColorFilter.tint(tint)
            )
        )

        is Uri -> GlideImage(
            imageModel = { src },
            modifier = modifier,
            imageOptions = ImageOptions(
                colorFilter = ColorFilter.tint(tint)
            )
        )

        is String -> GlideImage(
            imageModel = { src },
            modifier = modifier,
            imageOptions = ImageOptions(
                colorFilter = ColorFilter.tint(tint)
            )
        )

        is ImageVector -> Icon(
            src,
            contentDescription,
            modifier,
            tint
        )

        else -> throw (RuntimeException("Unknown image format $src."))
    }

}