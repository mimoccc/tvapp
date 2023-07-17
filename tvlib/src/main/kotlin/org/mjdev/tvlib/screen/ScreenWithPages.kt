/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.screen

import androidx.compose.runtime.Composable
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.ui.components.page.PagerScope
import org.mjdev.tvlib.ui.components.page.TvPager

open class ScreenWithPages : Screen() {

    open val startPageIndex: Int = 0

    open val pages: PagerScope.() -> Unit = {}

    @TvPreview
    @Composable
    override fun ComposeScreen() {
        val navController = navController ?: rememberNavControllerEx()
        TvPager(
            navController = navController,
            startIndex = startPageIndex,
            pages = pages
        )
    }

}