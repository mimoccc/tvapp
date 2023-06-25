/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Text
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.Screen
import org.mjdev.tvapp.base.network.NetworkStatus
import org.mjdev.tvapp.base.ui.components.complex.ErrorMessage
import org.mjdev.tvapp.base.ui.components.complex.ScreenView
import org.mjdev.tvapp.base.ui.components.complex.ScrollableTvLazyRow
import org.mjdev.tvapp.base.ui.components.complex.bigCarousel
import org.mjdev.tvapp.base.ui.components.complex.header
import org.mjdev.tvapp.base.ui.components.complex.tabs
import org.mjdev.tvapp.ui.components.MovieCard
import org.mjdev.tvapp.viewmodel.MainViewModel

class MainScreen : Screen() {

    override val titleResId = R.string.app_name

    override val args = listOf<NamedNavArgument>()

    override val menuTitleResId: Int = R.string.menu_item_home

    override val menuIcon: ImageVector get() = Icons.Filled.Home

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    @Composable
    override fun Compose(
        navController: NavHostController?,
        backStackEntry: NavBackStackEntry?,
        menuItems: List<MenuItem>,
        args: Map<String, Any?>
    ) {

        val viewModel = appViewModel<MainViewModel>()

        val categoryList = remember { viewModel.categoryList }.collectAsState()
        val featuredMovieList = remember { viewModel.featuredMovieList }.collectAsState()
        val messages = remember { viewModel.messages }.collectAsState()
        val networkState = remember { viewModel.networkInfo.networkStatus }.collectAsState(null)

        ScreenView(
            navController = navController,
            title = stringResource(titleResId),
            menuItems = menuItems
        ) { state ->

            viewModel.handleError { error ->
                state.error(error)
            }

            ScrollableTvLazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                header(
                    title = state.titleState.value,
                    backgroundColor = Color.DarkGray,
                    messagesCount = messages.value.size
                )

                if (networkState.value !is NetworkStatus.Connected) {
                    item {
                        ErrorMessage(
                            error = stringResource(R.string.no_network).asException(),
                            backgroundColor = Color.Black
                        )
                    }
                }

                tabs(
                    items = categoryList.value.map { it.name }
                )

                if (featuredMovieList.value.isNotEmpty()) {
                    bigCarousel(
                        items = featuredMovieList.value
                    )
                }

                items(categoryList.value) { category ->

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 16.dp),
                        text = category.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    TvLazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.height(200.dp),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 16.dp
                        )
                    ) {

                        items(category.movieList) { item ->

                            MovieCard(
                                movie = item,
                                onClick = { movie ->
                                    if (movie == null) {
                                        viewModel.postError(Exception("No media found."))
                                    } else if (movie.hasVideoUri) {
                                        open<PlayerScreen>(navController, movie.id)
                                    } else {
                                        open<DetailScreen>(navController, movie.id)
                                    }
                                }
                            )

                        }

                    }

                }

            }

        }

    }

}