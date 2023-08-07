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
import org.mjdev.tvapp.ui.pages.AboutPage
import org.mjdev.tvapp.ui.pages.MainPage
import org.mjdev.tvapp.ui.pages.PluginsPage
import org.mjdev.tvapp.ui.pages.SubscriptionPage
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.screen.ScreenWithPages
import org.mjdev.tvlib.ui.components.page.PagerScope
import org.mjdev.tvlib.ui.components.page.SearchPage
import org.mjdev.tvlib.ui.components.page.SettingsPage

class MainScreen : ScreenWithPages() {

    override val title: Int = R.string.app_name
    override val menuIcon: ImageVector = Icons.Default.Home
    override val immersive: Boolean = false

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    override val pages: (PagerScope.() -> Unit) = {

        page(SearchPage(), menuGravity = Top)

        page(MainPage(), isStartPage = true)
        page(SubscriptionPage())
        page(PluginsPage())

        page(SettingsPage(), menuGravity = Bottom)
        page(AboutPage(), menuGravity = Bottom)

    }

}