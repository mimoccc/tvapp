/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.page.TvPager
import org.mjdev.tvapp.base.ui.components.page.PagerScope

open class ScreenWithPages : Screen() {

    open val startPageIndex: Int = 0

    open val pages: PagerScope.() -> Unit = {}

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    @Composable
    override fun Compose(
        navController: NavHostControllerEx,
        backStackEntry: NavBackStackEntry?,
        args: Map<String, Any?>
    ) {
        TvPager(
            navController = navController,
            startIndex = startPageIndex,
            pages = pages
        )
    }

}