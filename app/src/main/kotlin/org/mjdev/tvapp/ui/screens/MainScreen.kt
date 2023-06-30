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
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.navigation.ScreenWithPages
import org.mjdev.tvapp.base.ui.components.page.PagerScope
import org.mjdev.tvapp.base.ui.components.page.SearchPage
import org.mjdev.tvapp.base.ui.components.page.SettingsPage
import org.mjdev.tvapp.ui.pages.AboutPage
import org.mjdev.tvapp.ui.pages.MainPage
import org.mjdev.tvapp.ui.pages.PluginsPage
import org.mjdev.tvapp.ui.pages.SubscriptionPage

class MainScreen : ScreenWithPages() {

    override val title: Int = R.string.app_name
    override val menuIcon: ImageVector = Icons.Default.Home
    override val immersive: Boolean = false

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    override val pages: (PagerScope.() -> Unit) = {

        page(SearchPage())

        page(AboutPage())
        page(MainPage(), isStartPage = true)
        page(SubscriptionPage())

        page(PluginsPage())
        page(SettingsPage())

    }

}