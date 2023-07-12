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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.ui.components.tv.Loading
import org.mjdev.tvapp.state.DetailsLoadingState
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.extensions.StringExt.asException
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.text.TextAny
import org.mjdev.tvlib.ui.theme.ThemeHelper.themeProvider

class DetailScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_movie_detail

    override val menuIcon: ImageVector get() = Icons.Filled.Info

    override val immersive: Boolean = true

    override val pageArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val viewModel: DetailViewModel = appViewModel { context ->
            DetailViewModel.mockDetailViewModel(context)
        }

        val data: Any? = args[data]

        val dataState = remember {
            viewModel.detailsLoadingState(data)
        }.collectAsState()

        val photo = (data as? ItemPhoto)?.uri
        val backgroundImage = (data as? ItemWithBackground<*>)?.background
        val image = (data as? ItemWithImage<*>)?.image
        val title = (data as? ItemWithTitle<*>)?.title
        val subtitle = (data as? ItemWithSubtitle<*>)?.subtitle
        val description = (data as? ItemWithDescription<*>)?.description
        var infoVisible by remember { mutableStateOf(true) }

        fun postError(error: String) = viewModel.postError(error.asException())

        when (dataState.value) {
            is DetailsLoadingState.Ready -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                infoVisible = !infoVisible
                            }
                        },
                    contentAlignment = Alignment.BottomStart,
                ) {
                    ImageAny(
                        src = photo ?: backgroundImage ?: image,
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
            DetailsLoadingState.Loading -> {
                Loading()
            }
            else -> {
                Loading()
                postError("File or address $data not found.")
            }
        }

    }

}