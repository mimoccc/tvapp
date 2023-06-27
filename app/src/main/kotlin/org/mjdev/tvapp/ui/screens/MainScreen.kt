/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import org.mjdev.tvapp.ui.pages.AboutPage
import org.mjdev.tvapp.ui.pages.MainPage
import org.mjdev.tvapp.ui.pages.SubscriptionPage

class MainScreen : Screen() {

    override val args = listOf<NamedNavArgument>()

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

        val menuState = remember { mutableListOf<MenuItem>().apply { addAll(menuItems) } }

        ScreenView(
            navController = navController,
            title = if (titleResId > -1) stringResource(titleResId) else R.string.app_name,
            menuItems = menuState
        ) { screenState ->

            Pager(
                navController,
                screenState,
                menuState
            ) {

                page(MainPage())
                page(AboutPage())
                page(SubscriptionPage())

            }

        }

    }
}