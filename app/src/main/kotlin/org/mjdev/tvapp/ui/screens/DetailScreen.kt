/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.navArgument
import kotlinx.coroutines.delay
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.image.FadingPhotoImage
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.text.AutoHideEmptyText
import org.mjdev.tvlib.ui.theme.ThemeHelper.themeProvider

class DetailScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_movie_detail

    override val menuIcon: ImageVector get() = Icons.Filled.Info

    override val immersive: Boolean = true

    override val routeArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val data: Any? = args[data]

        val photo = (data as? ItemPhoto)?.uri
        val backgroundImage = (data as? ItemWithBackground<*>)?.background
        val image = (data as? ItemWithImage<*>)?.image
        val title = (data as? ItemWithTitle<*>)?.title
        val subtitle = (data as? ItemWithSubtitle<*>)?.subtitle
        val description = (data as? ItemWithDescription<*>)?.description
        var infoVisible by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        infoVisible = !infoVisible
                    }
                },
            contentAlignment = Alignment.BottomCenter,
        ) {
            FadingPhotoImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black, RectangleShape),
                initialImage = backgroundImage ?: photo ?: image,
                contrast = 4f,
                alpha = 0.6f,
                brightness = -255f,
                contentScale = ContentScale.Crop
            )
            ImageAny(
                src = photo ?: backgroundImage ?: image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
            AnimatedVisibility(
                visible = infoVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.8f)
                        .background(themeProvider.detailTextBackgroundColor)
                        .padding(
                            vertical = themeProvider.detailTextPaddingVertical,
                            horizontal = themeProvider.detailTextPaddingHorizontal
                        )
                ) {
                    AutoHideEmptyText(
                        text = title,
                        style = themeProvider.detailsTitleTextStyle,
                        color = themeProvider.detailsTextColor
                    )
                    AutoHideEmptyText(
                        text = subtitle,
                        style = themeProvider.detailsTextStyle,
                        color = themeProvider.detailsTextColor
                    )
                    AutoHideEmptyText(
                        text = description,
                        style = themeProvider.detailsTextStyle,
                        color = themeProvider.detailsTextColor
                    )
                }
            }
            LaunchedEffect(infoVisible) {
                delay(2000)
                infoVisible = false
            }
        }

    }

}