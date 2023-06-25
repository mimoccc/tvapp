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
import androidx.navigation.navigation
import org.mjdev.tvapp.base.navigation.NavGraphBuilderEx

object NavExt {

    /**
     * Create navigation from extra builder to simplify generation of routes.
     * @see: [androidx.navigation.NavigatorProvider.navigation]
     *
     * @param navController Nav controller
     * @param route Route
     * @param builder Builder
     * @return [NavGraph]
     * @receiver [NavigatorProvider]
     */
    inline fun NavigatorProvider.navigation(
        navController: NavHostController,
        route: String? = null,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = NavGraphBuilderEx(this, route, navController)
        .apply(builder)
        .build()

    /**
     * Create navigation graph from nav controller.
     * Extended just with custom builder.
     *
     * @see: [androidx.navigation.NavController.createGraph]
     *
     * @param route Route
     * @param navController Nav controller
     * @param builder Builder
     * @return [NavGraph]
     * @receiver [NavController]
     */
    inline fun NavController.createGraph(
        route: String? = null,
        navController: NavHostController,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = navigatorProvider.navigation(navController, route, builder)

}