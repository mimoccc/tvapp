/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import android.annotation.SuppressLint
import android.content.ComponentName
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.R
import org.mjdev.tvapp.activity.IPTVActivity
import org.mjdev.tvapp.activity.IPTVActivity.Companion.IPTV_DATA
import org.mjdev.tvapp.activity.MainActivity
import org.mjdev.tvapp.data.events.SyncEvent
import org.mjdev.tvapp.sync.SyncAdapter.Companion.pauseSync
import org.mjdev.tvlib.data.local.User
import org.mjdev.tvlib.auth.AuthManager.Companion.rememberAuthManager
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.tv.AppsRow
import org.mjdev.tvlib.ui.components.tv.BrowseView
import org.mjdev.tvlib.ui.components.tv.LocalAudioRow
import org.mjdev.tvlib.ui.components.tv.LocalPhotosRow
import org.mjdev.tvlib.ui.components.tv.LocalVideoRow
import org.mjdev.tvapp.viewmodel.MainViewModel
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.observedEvents
import org.mjdev.tvlib.extensions.KodeinExt.rememberInstance
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

    @SuppressLint("OpaqueUnitKey")
    @Previews
    @Composable
    override fun Content() {
        val navController = rememberNavControllerEx()
        val viewModel: MainViewModel by rememberInstance()
        val syncEvents by observedEvents<SyncEvent>()
        // refresh every 32 items, todo improve
        val needRefresh by remember {
            derivedStateOf {
                syncEvents.size > 32
                syncEvents.clear()
            }
        }

        val titleState by remember { mutableIntStateOf(R.string.app_name) }
        // todo improve
        val streamingData by remember(needRefresh) { viewModel.movieList }.collectAsState()
        val countryList by remember { viewModel.countryList }.collectAsState()
        val featuredMovieList by remember { viewModel.featuredMovieList }.collectAsState()
        val messages by remember { viewModel.messages }.collectAsState()
        var user by remember { mutableStateOf<User?>(null) }
        val errorState = remember(viewModel.error) {
            mutableStateOf(viewModel.error.value)
        }
        val authManager = rememberAuthManager(
            allowedPackages = listOf(BuildConfig.APPLICATION_ID)
        ) { u -> user = u }

        val onItemClick: (
            item: Any?
        ) -> Unit = { item ->
            pauseSync()
            navController.startActivity<IPTVActivity>(IPTV_DATA to (item as Serializable))
        }
        val onItemSelect: (
            item: Any?,
            fromUser: Boolean
        ) -> Unit = { item, fromUser ->
            if (fromUser) pauseSync()
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

        // todo move to viewmodel and events
        viewModel.handleError { error ->
            errorState.value = error
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            BrowseView(
                modifier = Modifier.fillMaxSize(),
                appIcon = R.mipmap.ic_launcher_foreground,
                userIcon = user?.pictureUrl ?: org.mjdev.tvlib.R.drawable.person,
                githubUser = BuildConfig.GITHUB_USER,
                githubRepository = BuildConfig.GITHUB_REPOSITORY,
                title = titleState,
                messages = messages,
                categories = countryList,
                featuredItems = featuredMovieList,
                categoriesAndItemsMap = streamingData,
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
                            excludedActivities = listOf(
                                ComponentName(
                                    BuildConfig.APPLICATION_ID,
                                    MainActivity::class.java.name
                                )
                            )
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
//            BlurDialog(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                title = "Welcome",
//                noiseAlpha = 1f,
//                backgroundAlpha = 0.5f
//            ) {
//                // todo
//            }
        }
    }

}