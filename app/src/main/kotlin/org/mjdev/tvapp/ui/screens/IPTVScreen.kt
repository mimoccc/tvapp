/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.media.MediaPlayerContainer
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem

class IPTVScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_iptv
    override val menuIcon: ImageVector get() = Icons.Filled.Tv
    override val immersive: Boolean = true
    override val showOnce: Boolean = true

    override val routeArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @TvPreview
    @Composable
    override fun ComposeScreen() {

//        val viewModel: DetailViewModel = appViewModel { context ->
//            DetailViewModel.mockDetailViewModel(context)
//        }

        val data: Any? = remember { args[data] }

        // todo anr
//        val dataList :List<MediaItem> = remember {
//            viewModel.mediaItemsFor(data)
//        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, RectangleShape),
        ) {
            MediaPlayerContainer(
                modifier = Modifier.fillMaxSize(),
                items = listOf(data.mediaItem),
                itemToPlay = 0
            )
        }

    }

}