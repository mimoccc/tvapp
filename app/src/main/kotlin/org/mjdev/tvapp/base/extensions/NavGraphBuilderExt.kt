/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.composable
import org.mjdev.tvapp.base.extensions.NavBackStackEntryExt.arg
import org.mjdev.tvapp.base.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.base.screen.Screen

object NavGraphBuilderExt {

    fun <T : Screen> NavGraphBuilderEx.screen(
        route: T,
        isHomeScreen: Boolean = false,
        isStartScreen: Boolean = false,
        deepLinks: List<NavDeepLink> = emptyList(),
        enterTransition: (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
            AnimExt.FadeIn
        },
        exitTransition: (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
            AnimExt.FadeOut
        },
        popEnterTransition: (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
            enterTransition,
        popExitTransition: (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
            exitTransition,
    ) {
        if (isStartScreen) {
            splashDestinationRoute = route.route
        }
        if (isHomeScreen) {
            homeDestinationRoute = route.route
        }
        route.menuItem?.also { menuItem ->
            navHostController.addMenuItem(menuItem)
        }
        composable(
            route = route.route,
            arguments = route.pageArgs,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            deepLinks = deepLinks,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) { be ->
            val rArgs = mutableMapOf<String, Any?>().apply {
                route.pageArgs.forEach { arg ->
                    put(arg.name, be.arg(arg.name, null))
                }
            }
            route.Compose(navHostController, be, rArgs)
        }
    }

}