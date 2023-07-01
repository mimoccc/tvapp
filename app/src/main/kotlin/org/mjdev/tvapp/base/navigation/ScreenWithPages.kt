/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.compose.runtime.Composable
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.page.TvPager
import org.mjdev.tvapp.base.ui.components.page.PagerScope

open class ScreenWithPages : Screen() {

    open val startPageIndex: Int = 0

    open val pages: PagerScope.() -> Unit = {}

    @TvPreview
    @Composable
    override fun ComposeScreen() {
        TvPager(
            navController = navController,
            startIndex = startPageIndex,
            pages = pages
        )
    }

}