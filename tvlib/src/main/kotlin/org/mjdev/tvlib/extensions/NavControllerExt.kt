/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.navOptions
import org.mjdev.tvlib.extensions.ListExt.toBundle
import org.mjdev.tvlib.screen.Screen
import kotlin.reflect.full.createInstance

@Suppress("MemberVisibilityCanBePrivate")
object NavControllerExt {

    val NavHostController.currentRoute get() = currentDestination?.route

    @MainThread
    fun NavHostController.open(
        route: String?
    ) {
        if (route != null) {
            val currentRoute = currentRoute
            val equals = currentRoute?.equals(route)
            if (equals != true) {
                navigate(route)
            }
        }
    }

    // todo check route already opened
    @MainThread
    inline fun <reified T : Screen> NavHostController.open(
        vararg values: Any?
    ) {
        val instance = T::class.createInstance()
        val finalRoute = instance.route
        val args = instance.routeArgs.mapIndexed { idx, arg ->
            Pair(arg.name, values[idx])
        }.toBundle()
        navigate(finalRoute, args)
    }

    // todo check route already opened
    @MainThread
    inline fun <reified T : Screen> NavHostController.openAsTop(
        vararg values: Any?
    ) {
        val instance = T::class.createInstance()
        val finalRoute = instance.route
        val args = instance.routeArgs.mapIndexed { idx, arg ->
            Pair(arg.name, values[idx])
        }.toBundle()
        navigate(
            finalRoute,
            args,
            navOptions {
                popBackStack()
                launchSingleTop = true
            },
            null
        )
    }

    fun NavController.navigate(
        route: String,
        args: Bundle,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val nodeId = graph.findNode(route = route)?.id
        if (nodeId != null) {
            navigate(nodeId, args, navOptions, navigatorExtras)
        }
    }

}
