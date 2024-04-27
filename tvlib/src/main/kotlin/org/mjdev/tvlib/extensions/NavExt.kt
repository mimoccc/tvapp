/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import org.mjdev.tvlib.activity.ComposableActivity
import org.mjdev.tvlib.extensions.ContextExt.activity
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.navigation.NavHostControllerEx

@Suppress("unused")
object NavExt {

    inline fun NavHostControllerEx.createGraph(
        navController: NavHostControllerEx,
        route: String? = null,
        startRoute: String? = null,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = navigatorProvider.navigation(navController, route, startRoute, builder)


    inline fun NavigatorProvider.navigation(
        navController: NavHostControllerEx,
        route: String? = null,
        startRoute: String? = null,
        builder: NavGraphBuilderEx.() -> Unit
    ): NavGraph = NavGraphBuilderEx(this, route, startRoute, navController)
        .apply(builder)
        .build()

    @Composable
    fun rememberNavControllerEx(
        vararg navigators: Navigator<out NavDestination>
    ): NavHostControllerEx {
        val context = LocalContext.current
        val activity = runCatching { context.activity<ComposableActivity>() }.getOrNull()
        val navigatorCached = activity?.navController
        // todo navigators check
        return (navigatorCached ?: rememberSaveable(
            inputs = arrayOf(context, navigators),
            saver = NavControllerSaver(context)
        ) {
            createNavController(context)
        }).apply {
            for (navigator in navigators) {
                navigatorProvider.addNavigator(navigator)
            }
        }
    }

    @Composable
    fun rememberNavControllerEx(menuItems: List<MenuItem>) = rememberNavControllerEx().apply {
        addMenuItem(*menuItems.toTypedArray())
    }

    fun navControllerEx(
        context: Context,
        vararg navigators: Navigator<out NavDestination>
    ): NavHostControllerEx {
        return createNavController(context).apply {
            for (navigator in navigators) {
                navigatorProvider.addNavigator(navigator)
            }
        }
    }

    private fun createNavController(context: Context) =
        NavHostControllerEx(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }

    @Suppress("FunctionName")
    private fun NavControllerSaver(
        context: Context
    ): Saver<NavHostControllerEx, *> = Saver(
        save = { it.saveState() },
        restore = { createNavController(context).apply { restoreState(it) } }
    )

}
