/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.extensions.NavExt.createGraph

/**
 * Custom NavHost object to extend functionality and simplify
 * navigation across application.
 *
 * @param modifier Modifier modifier as usual for composable
 * @param route Route route expected
 * @param navController Nav controller navigation controller
 * @param builder Builder builder for routes
 */
@Composable
fun NavHostEx(
    modifier: Modifier = Modifier,
    route: String? = null,
    navController: NavHostController,
    builder: NavGraphBuilderEx.() -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController,
        remember(route, builder) {
            navController.createGraph(
                route,
                navController,
                builder
            )
        },
        modifier
    )
}