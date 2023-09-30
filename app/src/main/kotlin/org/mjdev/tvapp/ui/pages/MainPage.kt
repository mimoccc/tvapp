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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.R
import org.mjdev.tvapp.activity.IPTVActivity
import org.mjdev.tvapp.activity.IPTVActivity.Companion.IPTV_DATA
import org.mjdev.tvapp.data.local.User
import org.mjdev.tvapp.helpers.AuthManager.Companion.rememberAuthManager
import org.mjdev.tvlib.extensions.HiltExt.appViewModel
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.tv.AppsRow
import org.mjdev.tvlib.ui.components.tv.BrowseView
import org.mjdev.tvlib.ui.components.tv.LocalAudioRow
import org.mjdev.tvlib.ui.components.tv.LocalPhotosRow
import org.mjdev.tvlib.ui.components.tv.LocalVideoRow
import org.mjdev.tvapp.viewmodel.MainViewModel
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import java.io.Serializable
import java.net.URL

@SuppressLint("ComposableNaming")
class MainPage : Page() {

    override val title: Int = R.string.title_home
    override val icon: ImageVector = Icons.Default.Home

    @Previews
    @Composable
    override fun Content() {
        val navController = rememberNavControllerEx()

        val viewModel: MainViewModel = appViewModel { context ->
            MainViewModel.mock(context)
        }

        val streamingData = viewModel.movieList.collectAsState()
        val countryList = viewModel.countryList.collectAsState()
        val featuredMovieList = viewModel.featuredMovieList.collectAsState()
        val messages = viewModel.messages.collectAsState()

        val errorState = remember(viewModel.error) { mutableStateOf(viewModel.error.value) }
        val titleState = remember { mutableStateOf<Any?>(R.string.app_name) }

        val onItemClick: (item: Any?) -> Unit = { item ->
            val dataId = (item as? ItemWithId)?.id
            if (dataId != null) {
                viewModel.findMovie(dataId) { movie ->
                    navController.startActivity<IPTVActivity>(IPTV_DATA to movie)
                }
            } else if (item is Serializable) {
                navController.startActivity<IPTVActivity>(IPTV_DATA to item)
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
                navController.backgroundState.value = uri
            }
        }

        viewModel.handleError { error ->
            errorState.value = error
        }

        val user = remember { mutableStateOf<User?>(null) }
        val authManager = rememberAuthManager { u -> user.value = u }
        val apps = remember { viewModel.apps }.collectAsState(emptyList())

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            BrowseView(
                modifier = Modifier.fillMaxSize(),
                appIcon = R.mipmap.ic_launcher_foreground,
                userIcon = user.value?.pictureUrl ?: org.mjdev.tvlib.R.drawable.person,
                githubUser = BuildConfig.GITHUB_USER,
                githubRepository = BuildConfig.GITHUB_REPOSITORY,
                title = titleState.value,
                messages = messages.value,
                categories = countryList.value,
                featuredItems = featuredMovieList.value,
                categoriesAndItemsMap = streamingData.value,
                errorState = errorState,
                onTitleClicked = {
                    navController.openMenu()
                },
                onItemFocused = onItemSelect,
                onItemClicked = onItemClick,
                onUserPicClicked = {
                    if (authManager.isUserLoggedIn) {
                        navController.openSettings()
                    } else {
                        authManager.login()
                    }
                },
                customRows = mutableListOf<@Composable () -> Unit>().apply {
                    add {
                        AppsRow(
                            apps = apps
                        )
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