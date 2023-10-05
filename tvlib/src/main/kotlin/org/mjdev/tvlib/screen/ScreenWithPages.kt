/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.screen

import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.page.Page.Companion.EMPTY_PAGE
import org.mjdev.tvlib.ui.components.page.PagerScope
import org.mjdev.tvlib.ui.components.page.TvPager

open class ScreenWithPages : Screen() {

    open val startPageIndex: Int = 0

    open val pages: PagerScope.() -> Unit = {
        page(EMPTY_PAGE)
    }

    @Previews
    @Composable
    @CallSuper
    override fun Content() {
        TvPager(
            startIndex = startPageIndex,
            pages = pages,
        )
    }

}