/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import org.mjdev.tvapp.R
import org.mjdev.tvapp.activity.IPTVActivity
import org.mjdev.tvapp.activity.IPTVActivity.Companion.IPTV_DATA
import org.mjdev.tvapp.data.local.User
import org.mjdev.tvapp.helpers.AuthManager.Companion.rememberAuthManager
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.tv.AppsRow
import org.mjdev.tvlib.ui.components.tv.BrowseView
import org.mjdev.tvlib.ui.components.tv.LocalAudioRow
import org.mjdev.tvlib.ui.components.tv.LocalPhotosRow
import org.mjdev.tvlib.ui.components.tv.LocalVideoRow
import org.mjdev.tvapp.viewmodel.MainViewModel
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.ui.components.image.FadingPhotoImage
import java.io.Serializable
import java.net.URL

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

        val streamingData = viewModel.categoryList.collectAsState()
        val countryList = viewModel.countryList.collectAsState()
        val featuredMovieList = viewModel.featuredMovieList.collectAsState()
        val messages = viewModel.messages.collectAsState()
        val networkState = viewModel.networkInfo.networkStatus.collectAsState(null)

        val errorState = remember(viewModel.error) { mutableStateOf(viewModel.error.value) }
        val titleState = remember { mutableStateOf<Any?>(R.string.app_name) }
        val backgroundState: MutableState<Any?> = remember { mutableStateOf(null) }

        val onItemClick: (item: Any?) -> Unit = { item ->
            val dataId = (item as? ItemWithId)?.id
            if (dataId != null) {
                viewModel.findMovie(dataId)?.let { movie ->
                    navController?.startActivity<IPTVActivity>(IPTV_DATA to movie)
                }
            } else if (item is Serializable) {
                navController?.startActivity<IPTVActivity>(IPTV_DATA to item)
            }
        }

        val onItemSelect: (item: Any?) -> Unit = { item ->
            when (item) {
                is String -> item
                is URL -> item
                is Uri -> item
                is ItemPhoto -> item.uri
                is ItemWithBackground<*> -> item.background
                is ItemWithImage<*> -> item.image
                else -> item.toString()
            }.also { uri ->
                backgroundState.value = uri
            }
        }

        viewModel.handleError { error ->
            errorState.value = error
        }

        val user = remember { mutableStateOf<User?>(null) }
        val authManager = rememberAuthManager { u -> user.value = u }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            FadingPhotoImage(
                modifier = Modifier.fillMaxSize(),
                fadingImageState = backgroundState,
                contrast = 4f,
                alpha = 0.7f,
                brightness = -255f,
                contentScale = ContentScale.Crop
            )
            BrowseView(
                modifier = Modifier.fillMaxSize(),
                appIcon = R.mipmap.ic_launcher_foreground,
                userIcon = user.value?.pictureUrl ?: org.mjdev.tvlib.R.drawable.person,
                title = titleState.value,
                messages = messages.value,
                categories = countryList.value,
                featuredItems = featuredMovieList.value,
                categoriesAndItemsMap = streamingData.value,
                networkState = networkState,
                errorState = errorState,
                onTitleClicked = {
                    navController?.openMenu()
                },
                onItemFocused = onItemSelect,
                onItemClicked = onItemClick,
                onUserPicClicked = {
                    if (authManager.isUserLoggedIn) {
                        // todo show details / settings
                    } else {
                        authManager.login()
                    }
                },
                customRows = mutableListOf<@Composable () -> Unit>().apply {
                    if (viewModel.appsList.size > 0) add {
                        AppsRow(apps = viewModel.appsList)
                    }
                    if (viewModel.localAudioCursor.count > 0) add {
                        LocalAudioRow(
                            cursor = viewModel.localAudioCursor,
                            openItem = { item -> onItemClick(item) },
                            onItemFocus = onItemSelect,
                        )
                    }
                    if (viewModel.localVideoCursor.count > 0) add {
                        LocalVideoRow(
                            cursor = viewModel.localVideoCursor,
                            openItem = { item -> onItemClick(item) },
                            onItemFocus = onItemSelect,
                        )
                    }
                    if (viewModel.localPhotoCursor.count > 0) add {
                        LocalPhotosRow(
                            cursor = viewModel.localPhotoCursor,
                            openItem = { item -> onItemClick(item) },
                            onItemFocus = onItemSelect,
                        )
                    }
                }
            )
        }
    }

}