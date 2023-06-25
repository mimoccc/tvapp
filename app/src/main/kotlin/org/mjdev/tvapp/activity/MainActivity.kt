/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvapp.base.activity.ComposableActivity
import org.mjdev.tvapp.base.navigation.Screen.Companion.screen
import org.mjdev.tvapp.ui.screens.AboutScreen
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.ui.screens.SubscriptionScreen

@AndroidEntryPoint
class MainActivity : ComposableActivity ({

    screen(route = MainScreen(), isHomeScreen = true)
    screen(route = DetailScreen())
    screen(route = PlayerScreen())
    screen(route = SubscriptionScreen())
    screen(route = AboutScreen())

})