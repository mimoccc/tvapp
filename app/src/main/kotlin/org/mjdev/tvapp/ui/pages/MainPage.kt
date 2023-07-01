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
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithVideoUri
import org.mjdev.tvapp.base.screen.Screen.Companion.open
import org.mjdev.tvapp.base.ui.components.page.Page
import org.mjdev.tvapp.base.ui.components.tv.BrowseView
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

        val categoryList = remember { viewModel.categoryList }.collectAsState()
        val featuredMovieList = remember { viewModel.featuredMovieList }.collectAsState()
        val messages = remember { viewModel.messages }.collectAsState()
        val networkState = remember { viewModel.networkInfo.networkStatus }.collectAsState(null)
        val errorState = remember { mutableStateOf<Throwable?>(null) }
        val titleState = remember { mutableStateOf<Any?>(R.string.app_name) }

        val onItemClick: (item: Any?) -> Unit = { item ->
            val isVideo = ((item as? ItemWithVideoUri)?.hasVideoUri == true)
            val isId = ((item as? ItemWithId)?.hasId == true)
            val id = (item as? ItemWithId)?.id
            if ((item == null) || (!isId)) {
                viewModel.postError("No media found.".asException())
            } else if (isVideo) {
                navController?.open<PlayerScreen>(id)
            } else {
                navController?.open<DetailScreen>(id)
            }
        }

        viewModel.handleError { error ->
            errorState.value = error
        }

        BrowseView(
            modifier = Modifier.fillMaxSize(),
            title = titleState.value,
            messages = messages.value,
            categories = categoryList.value,
            featuredItems = featuredMovieList.value,
            categoriesAndItemsMap = categoryList.value.associateWith { it.movieList },
            networkState = networkState,
            errorState = errorState,
            backgroundColor = Color.DarkGray,
            onItemClick = onItemClick
        )

    }

}