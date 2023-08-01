/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.ui.components.tv.Loading
import org.mjdev.tvapp.state.DetailsLoadingState
import org.mjdev.tvapp.ui.components.MediaPlayer
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.extensions.StringExt.asException
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen

class PlayerScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_movie_player
    override val menuIcon: ImageVector get() = Icons.Filled.PlayArrow
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

        val isEdit = isEditMode()

        val viewModel: DetailViewModel = appViewModel { context ->
            DetailViewModel.mockDetailViewModel(context)
        }

        val data: Any? = remember { args[data] }
        val dataState = remember {
            viewModel.detailsLoadingState(data)
        }.collectAsState()

        fun postError(error: String) = viewModel.postError(error.asException())

        if (isEdit || (dataState.value is DetailsLoadingState.Ready)) {
            MediaPlayer(
                modifier = Modifier.fillMaxSize(),
                data = (dataState.value as? DetailsLoadingState.Ready)?.data
            )
        } else if (dataState.value is DetailsLoadingState.NotFound) {
            postError("Can not play: ${dataState.value}. File not found.")
            Loading()
        } else {
            postError("Can not play: ${dataState.value}. File unknown type.")
            Loading()
        }

    }

}