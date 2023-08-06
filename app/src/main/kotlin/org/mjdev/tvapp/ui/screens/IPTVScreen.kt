/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.media.MediaPlayerContainer
import java.net.URL

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

        val viewModel: DetailViewModel = appViewModel { context ->
            DetailViewModel.mockDetailViewModel(context)
        }

        val data: Any? = remember { args[data] }

        val dataList = remember {
            when (data) {
                is ItemAudio -> viewModel.localAudioCursor
                is ItemVideo -> viewModel.localVideoCursor
                is ItemPhoto -> viewModel.localPhotoCursor
                is Movie -> viewModel.movieList()
                else -> listOf(data)
            }
        }

        val currentIndex = remember {
            mutableIntStateOf(dataList.indexOf(data))
        }

        val currentData = dataList[currentIndex.intValue]

        val mediaUri: String? = when (currentData) {
            null -> null
            is ItemWithUri<*> -> currentData.uri.toString()
            is String -> currentData.toString()
            is URL -> currentData.toString()
            is Uri -> currentData.toString()
            else -> currentData.toString()
        }

        val mediaTitle: String? = when (currentData) {
            is ItemWithTitle<*> -> currentData.title.toString()
            else -> null
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, RectangleShape),
        ) {
            MediaPlayerContainer(
                modifier = Modifier.fillMaxSize(),
                uri = mediaUri?.parseUri(),
                title = mediaTitle,
                subtitle = mediaUri
            )
        }

    }

}