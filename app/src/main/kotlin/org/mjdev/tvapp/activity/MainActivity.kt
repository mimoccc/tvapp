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
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.SplashScreen
import org.mjdev.tvlib.activity.TvActivity
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.homeScreen
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.startScreen

@AndroidEntryPoint
class MainActivity : TvActivity() {

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        startScreen(SplashScreen())
        homeScreen(MainScreen())
    }

}