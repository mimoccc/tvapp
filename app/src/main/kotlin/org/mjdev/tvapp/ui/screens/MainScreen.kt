/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.Screen
import org.mjdev.tvapp.base.network.NetworkStatus
import org.mjdev.tvapp.base.page.Pager
import org.mjdev.tvapp.base.ui.components.complex.ErrorMessage
import org.mjdev.tvapp.base.ui.components.complex.Header
import org.mjdev.tvapp.base.ui.components.complex.ScreenView
import org.mjdev.tvapp.ui.pages.MainPage
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
        val messages = remember { viewModel.messages }.collectAsState()
        val networkState = remember {
            viewModel.networkInfo.networkStatus
        }.collectAsState(null)
//        val pageState = remember { mutableStateOf<Any?>(null) }

        ScreenView(
            navController = navController,
            title = stringResource(titleResId),
            menuItems = menuItems
        ) { screenState ->

            viewModel.handleError { error ->
                screenState.error(error)
            }

            Header(
                title = screenState.titleState.value,
                messagesCount = messages.value.size
            )

            if (networkState.value !is NetworkStatus.Connected) {
                ErrorMessage(
                    error = stringResource(R.string.error_no_network).asException(),
                    backgroundColor = Color.Black,
                    dismissible = false
                )
            }

            Pager {

                MainPage(navController, screenState)

            }

        }

    }
}