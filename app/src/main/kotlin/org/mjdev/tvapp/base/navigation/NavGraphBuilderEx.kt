/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavigatorProvider
import org.mjdev.tvapp.base.ui.components.screen.EmptyScreen.Companion.ROUTE_NONE

class NavGraphBuilderEx(
    provider: NavigatorProvider,
    route: String? = null,
    startRoute: String? = null,
    val navHostController: NavHostControllerEx
) : NavGraphBuilder(provider, startRoute ?: "none", route) {

    var splashDestinationRoute: String? = startRoute
    var homeDestinationRoute: String = startRoute ?: ROUTE_NONE

    override fun build(): NavGraph {
        val navGraph = super.build()
        (splashDestinationRoute ?: homeDestinationRoute).also { route ->
            navGraph.setStartDestination(route)
        }
        return navGraph
    }

}