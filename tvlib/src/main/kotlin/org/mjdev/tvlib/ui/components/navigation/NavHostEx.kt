/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavExt.createGraph
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.image.FadingPhotoImage
import org.mjdev.tvlib.ui.components.screen.EmptyScreen
import org.mjdev.tvlib.ui.components.screen.ScreenView

@Previews
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
    FadingPhotoImage(
        modifier = Modifier.fillMaxSize(),
        fadingImageState = navController.backgroundState,
        contrast = 4f,
        alpha = 0.7f,
        brightness = -255f,
        contentScale = ContentScale.Crop
    )
    ScreenView(
        modifier = modifier,
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
            popExitTransition = popExitTransition
        )
    }
}