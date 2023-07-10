/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.interfaces.ItemWithBackground
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.base.ui.theme.LocalThemeProvider

@SuppressLint("ModifierParameter")
@TvPreview
@Composable
fun Details(
    data: Any? = null,
    modifier: Modifier = Modifier,
) {
    val themeProvider = LocalThemeProvider.current
    val backgroundImage = (data as? ItemWithBackground<*>)?.background
    val image = (data as? ItemWithImage<*>)?.image
    val title = (data as? ItemWithTitle<*>)?.title
    val subtitle = (data as? ItemWithSubtitle<*>)?.subtitle
    val description = (data as? ItemWithDescription<*>)?.description
    var infoVisible by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    infoVisible = !infoVisible
                }
            },
        contentAlignment = Alignment.BottomStart,
    ) {
        ImageAny(
            src = backgroundImage ?: image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        AnimatedVisibility(
            visible = infoVisible,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth().alpha(0.8f)
                    .background(themeProvider.detailTextBackgroundColor)
                    .padding(
                        vertical = themeProvider.detailTextPaddingVertical,
                        horizontal = themeProvider.detailTextPaddingHorizontal
                    )
            ) {
                TextAny(
                    text = title ?: "-",
                    style = themeProvider.detailsTitleTextStyle,
                    color = themeProvider.detailsTextColor
                )
                TextAny(
                    text = subtitle ?: "-",
                    style = themeProvider.detailsTextStyle,
                    color = themeProvider.detailsTextColor
                )
                TextAny(
                    text = description ?: "-",
                    style = themeProvider.detailsTextStyle,
                    color = themeProvider.detailsTextColor
                )
            }
        }
    }
}