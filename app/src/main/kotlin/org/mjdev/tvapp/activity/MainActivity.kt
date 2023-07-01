/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvapp.base.activity.ComposableActivity
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.base.screen.Screen.Companion.screen
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.ui.screens.SplashScreen

@AndroidEntryPoint
class MainActivity : ComposableActivity() {

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {

        screen(route = SplashScreen(), isStartScreen = true)
        screen(route = MainScreen(), isHomeScreen = true)
        screen(route = DetailScreen())
        screen(route = PlayerScreen())

    }
}