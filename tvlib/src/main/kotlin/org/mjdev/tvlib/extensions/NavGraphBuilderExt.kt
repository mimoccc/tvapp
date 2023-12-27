/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.composable
import org.mjdev.tvlib.extensions.NavBackStackEntryExt.arg
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.screen.Screen

typealias AnimType<T> = (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> T?)

@Suppress("unused")
object NavGraphBuilderExt {

    fun <T : Screen> NavGraphBuilderEx.startScreen(
        route: T,
        isHomeScreen: Boolean = false,
        deepLinks: List<NavDeepLink> = emptyList(),
        enterTransition: AnimType<EnterTransition>? = { AnimExt.FadeIn },
        exitTransition: AnimType<ExitTransition>? = { AnimExt.FadeOut },
        popEnterTransition: AnimType<EnterTransition>? = enterTransition,
        popExitTransition: AnimType<ExitTransition>? = exitTransition
    ) = screen(
        route = route,
        isStartScreen = true,
        isHomeScreen = isHomeScreen,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    )

    fun <T : Screen> NavGraphBuilderEx.homeScreen(
        route: T,
        isStartScreen: Boolean = false,
        deepLinks: List<NavDeepLink> = emptyList(),
        enterTransition: AnimType<EnterTransition>? = { AnimExt.FadeIn },
        exitTransition: AnimType<ExitTransition>? = { AnimExt.FadeOut },
        popEnterTransition: AnimType<EnterTransition>? = enterTransition,
        popExitTransition: AnimType<ExitTransition>? = exitTransition
    ) = screen(
        route = route,
        isStartScreen = isStartScreen,
        isHomeScreen = true,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    )

    fun <T : Screen> NavGraphBuilderEx.screen(
        route: T,
        isHomeScreen: Boolean = false,
        isStartScreen: Boolean = false,
        deepLinks: List<NavDeepLink> = emptyList(),
        enterTransition: AnimType<EnterTransition>? = { AnimExt.FadeIn },
        exitTransition: AnimType<ExitTransition>? = { AnimExt.FadeOut },
        popEnterTransition: AnimType<EnterTransition>? = enterTransition,
        popExitTransition: AnimType<ExitTransition>? = exitTransition
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
            arguments = route.routeArgs,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            deepLinks = deepLinks,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) { be ->
            val rArgs = mutableMapOf<String, Any?>().apply {
                route.routeArgs.forEach { arg ->
                    put(arg.name, be.arg(arg.name, null))
                }
            }
            route.apply {
                // todo improve ?
                navHostController.menuState.value = !route.immersive
                //
                route.backStackEntry = be
                route.args = rArgs
            }.recompose()
        }
    }

}