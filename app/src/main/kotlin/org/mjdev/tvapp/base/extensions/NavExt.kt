/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavigatorProvider
import org.mjdev.tvapp.base.navigation.NavGraphBuilderEx

object NavExt {

    inline fun NavController.createGraph(
        navController: NavHostController,
        route: String? = null,
        startRoute: String? = null,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = navigatorProvider.navigation(navController, route, startRoute, builder)


    inline fun NavigatorProvider.navigation(
        navController: NavHostController,
        route: String? = null,
        startRoute: String? = null,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = NavGraphBuilderEx(this, route, startRoute, navController)
        .apply(builder)
        .build()

}