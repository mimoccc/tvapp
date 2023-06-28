/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package org.mjdev.tvapp.base.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.tv.material3.Text
import org.mjdev.tvapp.base.annotations.TvPreview
import kotlin.reflect.full.createInstance

/**
 * Screen class.
 *
 * This is base of all screens in application and auto creation of route and nav graph.
 * This object represents one screen at time.
 * Can contain menu that is exported to navigation drawer.
 *
 * @constructor Create empty constructor for screen
 */
@Suppress("unused", "LeakingThis")
open class Screen : NavController.OnDestinationChangedListener {

    private val routeBase: String
        get() = (this::class.simpleName ?: "-") + "?"

    open val completeRoute: String
        get() = routeBase.let { rb ->
            var routeImpl = rb
            args.forEach { arg ->
                routeImpl = routeImpl.plus("${arg.name}={${arg.name}}")
            }
            routeImpl
        }

    open val titleResId: Int = -1

    open val args: List<NamedNavArgument> = emptyList()

    open val menuTitleResId: Int = -1

    open val menuIcon: ImageVector? = null

    open val showOnce = false

    open val backgroundColor :Color = Color.DarkGray

    open val roundRadius : Dp = 0.dp

    open val background : Shape = RoundedCornerShape(roundRadius)

    val menuItem
        get() = if (menuTitleResId >= 0) MenuItem(menuTitleResId, menuIcon, completeRoute)
        else null

    @TvPreview
    @Composable
    @CallSuper
    open fun Compose() {
        Compose(null, null, emptyList(), mapOf())
    }

    @Composable
    open fun Compose(
        navController: NavHostControllerEx?,
        backStackEntry: NavBackStackEntry?,
        menuItems: List<MenuItem>,
        args: Map<String, Any?>
    ) {

        if (showOnce) {
            navController?.addOnDestinationChangedListener(this)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor, background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Empty Screen",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (showOnce && destination.route == completeRoute) {
            controller.popBackStack()
        }
    }

    companion object {

        val SlideInVertically = slideInVertically(animationSpec = tween(1000))
        val SlideOutVertically = slideOutVertically(animationSpec = tween(1000))

        @Suppress("UNCHECKED_CAST", "DEPRECATION")
        private fun <T> NavBackStackEntry.arg(
            argId: String,
            defaultValue: T
        ): T = (arguments?.get(argId) as? T?) ?: defaultValue

        @MainThread
        inline fun <reified T : Screen> NavHostController.open(
            vararg values: Any?
        ) {
            val instance = T::class.createInstance()
            instance.completeRoute.let { r ->
                var routeImpl = r
                instance.args.forEachIndexed { idx, arg ->
                    routeImpl = routeImpl.replace(
                        "{${arg.name}}",
                        (values[idx] ?: "").toString()
                    )
                }
                routeImpl
            }.also { finalRoute ->
                val currentRoute = currentRoute
                val equals = currentRoute?.equals(finalRoute)
                if (equals != true) {
//                    try {
                        navigate(finalRoute)
//                    } catch (e: Exception) {
//                        Timber.e(e)
//                    }
                }
            }
        }

        @MainThread
        inline fun <reified T : Screen> NavHostController.openClear(
            vararg values: Any?
        ) {
            val instance = T::class.createInstance()
            instance.completeRoute.let { r ->
                var routeImpl = r
                instance.args.forEachIndexed { idx, arg ->
                    routeImpl = routeImpl.replace(
                        "{${arg.name}}",
                        (values[idx] ?: "").toString()
                    )
                }
                routeImpl
            }.also { finalRoute ->
                val currentRoute = currentRoute
                val equals = currentRoute?.equals(finalRoute)
                if (equals != true) {
//                    try {
                        navigate(finalRoute)
//                    } catch (e: Exception) {
//                        Timber.e(e)
//                    }
                }
            }
        }

        val NavHostController.currentRoute
            get() = currentDestination?.route

        fun <T : Screen> NavGraphBuilderEx.screen(
            route: T,
            isHomeScreen: Boolean = false,
            isStartScreen: Boolean = false,
            deepLinks: List<NavDeepLink> = emptyList(),
            enterTransition: (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
                SlideInVertically
            },
            exitTransition: (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
                SlideOutVertically
            },
            popEnterTransition: (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
                enterTransition,
            popExitTransition: (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
                exitTransition,
        ) {
            if (isStartScreen) {
                splashDestinationRoute = route.completeRoute
            }
            if (isHomeScreen) {
                homeDestinationRoute = route.completeRoute
            }
            route.menuItem?.also { menuItem ->
                menuItems.add(menuItem)
            }
            composable(
                route = route.completeRoute,
                arguments = route.args,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                deepLinks = deepLinks,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) { be ->
                val rArgs = mutableMapOf<String, Any?>().apply {
                    route.args.forEach { arg ->
                        put(arg.name, be.arg(arg.name, null))
                    }
                }
                route.Compose(navHostController, be, menuItems, rArgs)
            }
        }

    }

}