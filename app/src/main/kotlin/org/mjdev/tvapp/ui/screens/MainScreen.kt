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
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.screen.ScreenWithPages
import org.mjdev.tvlib.ui.components.page.PagerScope
import org.mjdev.tvlib.ui.components.page.SearchPage

class MainScreen : ScreenWithPages() {

    override val title: Int = R.string.app_name
    override val menuIcon: ImageVector = Icons.Default.Home
    override val immersive: Boolean = false

    override val pages: (PagerScope.() -> Unit) = {

        page(SearchPage(), menuGravity = Top)

        page(MainPage(), isStartPage = true)
        page(SubscriptionPage())
        page(PluginsPage())

        page(AboutPage(), menuGravity = Bottom)

    }

    @Previews
    @Composable
    override fun Content()  = super.Content()

}