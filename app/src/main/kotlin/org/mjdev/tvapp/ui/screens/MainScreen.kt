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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.Screen
import org.mjdev.tvapp.base.page.Pager
import org.mjdev.tvapp.base.ui.components.complex.ScreenView
import org.mjdev.tvapp.ui.pages.MainPage

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

        ScreenView(
            navController = navController,
            title = stringResource(titleResId),
            menuItems = menuItems
        ) { screenState ->

            Pager(
                screenState = screenState
            ) {

                MainPage(navController, screenState)

            }

        }

    }
}