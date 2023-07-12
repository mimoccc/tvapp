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
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.NavControllerExt.open
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithUri
import org.mjdev.tvapp.base.ui.components.page.Page
import org.mjdev.tvapp.base.ui.components.tv.AppsRow
import org.mjdev.tvapp.base.ui.components.tv.BrowseView
import org.mjdev.tvapp.base.ui.components.tv.LocalAudioRow
import org.mjdev.tvapp.base.ui.components.tv.LocalPhotosRow
import org.mjdev.tvapp.base.ui.components.tv.LocalVideoRow
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.viewmodel.MainViewModel

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
            val dataUri = (item as? ItemWithUri<*>)?.uri
            val dataId = (item as? ItemWithId)?.id
            if (item == null) {
                viewModel.postError("No media found.".asException())
            } else if (dataId != null) {
                navController?.open<PlayerScreen>(dataId)
            } else if (dataUri != null) {
                navController?.open<PlayerScreen>(dataUri)
            } else {
                navController?.open<DetailScreen>(item)
            }
        }

        viewModel.handleError { error ->
            errorState.value = error
        }

        BrowseView(
            modifier = Modifier.fillMaxSize(),
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