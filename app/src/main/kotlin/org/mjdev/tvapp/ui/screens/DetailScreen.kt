/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.navigation.AnyType
import org.mjdev.tvapp.base.screen.Screen
import org.mjdev.tvapp.state.DetailsLoadingState
import org.mjdev.tvapp.ui.components.Details
import org.mjdev.tvapp.ui.components.Loading
import org.mjdev.tvapp.viewmodel.DetailViewModel

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

        fun postError(error: String) = viewModel.postError(error.asException())

        when (dataState.value) {
            is DetailsLoadingState.Ready ->
                Details((dataState.value as DetailsLoadingState.Ready).data)

            is DetailsLoadingState.NotFound -> {
                postError("File or address $data not found.")
                Loading()
            }

            else -> {
                Loading()
            }
        }

    }

}