/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gallery

import android.view.Gravity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.ui.components.text.AutoHideEmptyText
import org.mjdev.tvlib.ui.components.image.ImageBackground

@TvPreview
@Composable
fun ItemInfo(
    src: Any? = null,
    infoVisible: MutableState<Boolean> = remember { mutableStateOf(true) },
    createColorBrush: (
        color: Color,
        gravity: Int
    ) -> Brush = { color, g ->
        Brush.verticalGradient(
            when (g) {
                Gravity.TOP -> listOf(
                    color,
                    color,
                    color,
                    color.copy(alpha = 0.8f),
                    color.copy(alpha = 0.5f),
                    Color.Transparent
                )

                else -> listOf(
                    Color.Transparent,
                    color.copy(alpha = 0.5f),
                    color.copy(alpha = 0.8f),
                    color,
                    color,
                    color,
                )
            }
        )
    },
    imageFromItem: () -> Any? = {
        val photo = (src as? ItemPhoto)?.uri
        val image = (src as? ItemWithImage<*>)?.image
        val background = (src as? ItemWithBackground<*>)?.background
        photo ?: image ?: background
    },
    titleFromItem: () -> Any? = {
        (src as? ItemWithTitle<*>)?.title
    },
    dateFromItem: () -> Any? = {
        (src as? ItemWithDate)?.date
    },
    detailsFromItem: () -> Any? = {
        (src as? ItemWithDescription<*>)?.description
    },
) {
    AnimatedVisibility(
        visible = infoVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.TopStart
        ) {
            ImageBackground(
                modifier = Modifier.fillMaxWidth(),
                image = imageFromItem(),
                transform = { color -> createColorBrush(color, Gravity.TOP) }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    AutoHideEmptyText(
                        text = titleFromItem(),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                    AutoHideEmptyText(
                        text = dateFromItem(),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    AutoHideEmptyText(
                        text = detailsFromItem(),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}