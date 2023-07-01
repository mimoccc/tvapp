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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.screen.Screen
import org.mjdev.tvapp.state.DetailsLoadingState
import org.mjdev.tvapp.ui.components.Loading
import org.mjdev.tvapp.ui.components.MediaPlayer
import org.mjdev.tvapp.viewmodel.DetailViewModel

class PlayerScreen : Screen() {

    private val movieId = "movieId"

    override val title = R.string.title_movie_player
    override val menuIcon: ImageVector get() = Icons.Filled.PlayArrow
    override val immersive: Boolean = true

    override val pageArgs = listOf(
        navArgument(movieId) {
            defaultValue = -1
            type = NavType.LongType
        }
    )

    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val isEdit = isEditMode()

        val viewModel: DetailViewModel = appViewModel { context ->
            DetailViewModel.mockDetailViewModel(context)
        }

        val movieId: Long? = remember { args[movieId] as Long? }
        val movieState = remember { viewModel.detailsLoadingState(movieId) }.collectAsState()

        fun postError(error: String) = viewModel.postError(error.asException())

        if (isEdit || (movieState.value is DetailsLoadingState.Ready)) {
            MediaPlayer(
                modifier = Modifier.fillMaxSize(),
                movie = (movieState.value as? DetailsLoadingState.Ready)?.movie
            )
        } else if (movieState.value is DetailsLoadingState.NotFound) {
            postError("Movie for id: $movieId not found.")
            Loading()
        } else {
            postError("Movie for id: $movieId not found.")
            Loading()
        }

    }

}