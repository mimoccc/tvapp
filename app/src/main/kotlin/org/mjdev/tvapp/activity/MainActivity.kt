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
import org.mjdev.tvlib.activity.ComposableActivity
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.SplashScreen
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.helpers.other.SystemUIController

@AndroidEntryPoint
class MainActivity : ComposableActivity() {

    private val systemUIController by lazy {
        SystemUIController(this) {
            if (isLandscape) hideSystemUI() else showSystemUI()
        }
    }

    init {
        systemUIController.register()
    }

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {

        screen(route = SplashScreen(), isStartScreen = true)
        screen(route = MainScreen(), isHomeScreen = true)

    }
}