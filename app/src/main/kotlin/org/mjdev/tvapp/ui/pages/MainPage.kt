/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.extensions.NavControllerExt.open
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.tv.AppsRow
import org.mjdev.tvlib.ui.components.tv.BrowseView
import org.mjdev.tvlib.ui.components.tv.LocalAudioRow
import org.mjdev.tvlib.ui.components.tv.LocalPhotosRow
import org.mjdev.tvlib.ui.components.tv.LocalVideoRow
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.viewmodel.MainViewModel
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo

@SuppressLint("ComposableNaming")
class MainPage : Page() {

    override val title: Int = R.string.title_home
    override val icon: ImageVector = Icons.Default.Home

    @TvPreview
    @Composable
    override fun Content() {
        val viewModel: MainViewModel = appViewModel { context ->
            MainViewModel.mockMainViewModel(context)
        }

        val categoryList = remember(viewModel.categoryList) {
            viewModel.categoryList
        }.collectAsState()
        val featuredMovieList = remember(viewModel.featuredMovieList) {
            viewModel.featuredMovieList
        }.collectAsState()
        val messages = remember(viewModel.messages) {
            viewModel.messages
        }.collectAsState()
        val networkState = remember(viewModel.networkInfo.networkStatus) {
            viewModel.networkInfo.networkStatus
        }.collectAsState(null)
        val errorState = remember(viewModel.error) {
            mutableStateOf(viewModel.error.value)
        }
        val titleState = remember {
            mutableStateOf<Any?>(R.string.app_name)
        }

        val onItemClick: (item: Any?) -> Unit = { item ->
            val dataId = (item as? ItemWithId)?.id
            if (dataId != null) {
                navController?.open<PlayerScreen>(dataId)
            } else {
                when (item) {
                    is ItemAudio -> {
                        val dataUri = (item as? ItemAudio)?.uri
                        navController?.open<PlayerScreen>(dataUri)
                    }
                    is ItemVideo -> {
                        val dataUri = (item as? ItemVideo)?.uri
                        navController?.open<PlayerScreen>(dataUri)
                    }
                    is ItemPhoto -> {
                        navController?.open<DetailScreen>(item)
                    }
                    else -> {
                        navController?.open<DetailScreen>(item)
                    }
                }
            }
        }

        viewModel.handleError { error ->
            errorState.value = error
        }

        BrowseView(
            modifier = Modifier.fillMaxSize(),
            appIcon = R.mipmap.ic_launcher,
            userIcon = R.drawable.milanj,
            title = titleState.value,
            messages = messages.value,
            categories = categoryList.value.map { it.key }.distinct(),
            featuredItems = featuredMovieList.value,
            categoriesAndItemsMap = categoryList.value,
            networkState = networkState,
            errorState = errorState,
            backgroundColor = Color.DarkGray,
            onTitleClicked = {
                navController?.openMenu()
            },
            onItemClicked = onItemClick,
            customRows = mutableListOf<@Composable () -> Unit>().apply {
                if (viewModel.appsList.size > 0) {
                    add { AppsRow(apps = viewModel.appsList) }
                }
                if (viewModel.localAudioCursor.count > 0) {
                    add {
                        LocalAudioRow(
                            cursor = viewModel.localAudioCursor,
                            openItem = { item -> onItemClick(item) }
                        )
                    }
                }
                if (viewModel.localVideoCursor.count > 0) {
                    add {
                        LocalVideoRow(
                            cursor = viewModel.localVideoCursor,
                            openItem = { item -> onItemClick(item) }
                        )
                    }
                }
                if (viewModel.localPhotoCursor.count > 0) {
                    add {
                        LocalPhotosRow(
                            cursor = viewModel.localPhotoCursor,
                            openItem = { item -> onItemClick(item) }
                        )
                    }
                }
            }
        )
    }

}