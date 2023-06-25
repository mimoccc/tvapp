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
    route: String?,
    val navHostController: NavHostController
) : NavGraphBuilder(provider, "none", route) {

    var startDestinationRouteEx: String? = null

    var menuItems: MutableList<MenuItem> = mutableListOf()

    override fun build(): NavGraph {
        val navGraph = super.build()
        if (startDestinationRouteEx == null) {
            throw(RuntimeException("One class in ComposeActivity should have start property set."))
        }
        navGraph.setStartDestination(startDestinationRouteEx!!)
        return navGraph
    }

}