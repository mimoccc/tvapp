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
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ColorExt.invert
import org.mjdev.tvlib.extensions.ComposeExt.createVerticalColorBrush
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.helpers.media.MetadataRetriever
import org.mjdev.tvlib.helpers.media.MetadataRetriever.Companion.rememberMetaDataRetriever
import org.mjdev.tvlib.interfaces.ItemWithDate
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.ui.components.text.AutoHideEmptyText

@Previews
@Composable
fun ItemInfo(
    modifier: Modifier = Modifier,
    src: Any? = null,
    visible: Boolean = isEditMode(),
    backgroundColor: Color = Color.Transparent,
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
    val bck = createVerticalColorBrush(backgroundColor, Gravity.TOP)
    val textColor = remember(backgroundColor) {
        derivedStateOf {
            backgroundColor.invert().copy(alpha = 1.0f)
        }
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = slideInVertically(),
        exit = slideOutVertically(),
    ) {
        Box(
            modifier = modifier.background(bck),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                AutoHideEmptyText(
                    text = titleFromItem(),
                    color = textColor.value,
                    fontSize = 24.sp
                )
                AutoHideEmptyText(
                    text = dateFromItem(),
                    color = textColor.value,
                    fontSize = 16.sp
                )
                AutoHideEmptyText(
                    text = detailsFromItem(),
                    color = textColor.value,
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}