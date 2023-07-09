/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.NavExt.createGraph
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvapp.base.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.screen.EmptyScreen
import org.mjdev.tvapp.base.ui.components.screen.ScreenView

@TvPreview
@Composable
fun NavHostEx(
    modifier: Modifier = Modifier,
    route: String? = null,
    startRoute: String? = null,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(700))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(700))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        exitTransition,
    builder: NavGraphBuilderEx.() -> Unit = {
        screen(route = EmptyScreen())
    }
) {
    ScreenView(
        navController = navController,
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            graph = remember(route, builder) {
                navController.createGraph(
                    navController,
                    route,
                    startRoute,
                    builder
                )
            },
            contentAlignment = contentAlignment,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
        )
    }
}