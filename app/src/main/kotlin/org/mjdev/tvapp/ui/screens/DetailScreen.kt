/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("MoveVariableDeclarationIntoWhen")

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.extensions.ListExt.indexOf
import org.mjdev.tvlib.extensions.MediaItemExt.uri
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.gallery.Gallery

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

        val viewModel: DetailViewModel = appViewModel { context ->
            DetailViewModel.mockDetailViewModel(context)
        }

        val data: Any? = remember { args[data] }

        val dataList = remember(data) {
            viewModel.mediaItemsFor(data)
        }

        Gallery(
            modifier = Modifier.fillMaxSize(),
            list = dataList,
            index = dataList.indexOf<Any?> { item -> item.uri == data.uri },
        )

    }

}