/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.page.Pager
import org.mjdev.tvapp.base.page.PagerScope
import org.mjdev.tvapp.base.ui.components.complex.ScreenView

open class ScreenWithPages : Screen() {

    open val startPage: Int = 0

    open val pages: @Composable PagerScope.() -> Unit = {}

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    @Composable
    override fun Compose(
        navController: NavHostControllerEx?,
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
                navController = navController,
                screenState = screenState,
                menuItems = menuState,
                startIndex = startPage,
                pages = pages
            )

        }

    }

}