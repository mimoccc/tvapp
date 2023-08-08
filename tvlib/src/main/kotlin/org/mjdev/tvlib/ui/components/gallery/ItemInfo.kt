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
import org.mjdev.tvlib.extensions.ColorExt.invert
import org.mjdev.tvlib.helpers.media.MetadataRetriever
import org.mjdev.tvlib.helpers.media.MetadataRetriever.Companion.rememberMetaDataRetriever
import org.mjdev.tvlib.interfaces.ItemWithDate
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
    initialColor: Color = Color.DarkGray,
    imageState: MutableState<Any?> = mutableStateOf(src),
    metadataRetriever: MetadataRetriever = rememberMetaDataRetriever(),
    titleFromItem: () -> Any? = {
        (src as? ItemWithTitle<*>)?.title
    },
    dateFromItem: () -> Any? = {
        (src as? ItemWithDate)?.date ?: "-"
    },
    detailsFromItem: () -> Any? = {
        metadataRetriever.getInfo(src)
    },
) {
    // todo color invert text does not work properly?
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
                initialColor = initialColor,
                imageState = imageState,
                transform = { color -> createColorBrush(color, Gravity.TOP) }
            ) { bckColor ->
                Column(
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    AutoHideEmptyText(
                        text = titleFromItem(),
                        color = bckColor.invert().copy(alpha = 1.0f),
                        fontSize = 24.sp
                    )
                    AutoHideEmptyText(
                        text = dateFromItem(),
                        color = bckColor.invert().copy(alpha = 1.0f),
                        fontSize = 16.sp
                    )
                    AutoHideEmptyText(
                        text = detailsFromItem(),
                        color = bckColor.invert().copy(alpha = 1.0f),
                        fontSize = 12.sp,
                        lineHeight = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}