/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavigatorProvider
import org.mjdev.tvapp.base.navigation.EmptyScreen.Companion.ROUTE_NONE

/**
 * Custom nav graph builder.
 *
 * Just extended of [navHostController] for simplify navigation auto creation.
 * @see : [androidx.navigation.NavGraphBuilder]
 *
 * @param provider
 * @param route
 * @constructor Create [NavGraphBuilderEx]
 * @property navHostController
 */
class NavGraphBuilderEx(
    provider: NavigatorProvider,
    route: String? = null,
    startRoute: String? = null,
    val navHostController: NavHostController
) : NavGraphBuilder(provider, startRoute ?: "none", route) {

    var splashDestinationRoute: String? = startRoute
    var homeDestinationRoute: String = startRoute ?: ROUTE_NONE
    var menuItems: MutableList<MenuItem> = mutableListOf()

    override fun build(): NavGraph {
        val navGraph = super.build()
        (splashDestinationRoute ?: homeDestinationRoute).also { route ->
            navGraph.setStartDestination(route)
        }
        return navGraph
    }

}