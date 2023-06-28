/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.runtime.Composable
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.navigation.ScreenWithPages
import org.mjdev.tvapp.base.page.PagerScope
import org.mjdev.tvapp.ui.pages.AboutPage
import org.mjdev.tvapp.ui.pages.MainPage
import org.mjdev.tvapp.ui.pages.SubscriptionPage

class MainScreen : ScreenWithPages() {

    override val startPage: Int = 1

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    override val pages: @Composable (PagerScope.() -> Unit) = {

        page(AboutPage())
        page(MainPage())
        page(SubscriptionPage())

    }

}