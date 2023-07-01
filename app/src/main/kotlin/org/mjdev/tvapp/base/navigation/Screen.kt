/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package org.mjdev.tvapp.base.navigation

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.tv.material3.Text
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.AnimExt.FadeIn
import org.mjdev.tvapp.base.extensions.AnimExt.FadeOut
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import kotlin.reflect.full.createInstance

@Suppress("unused", "LeakingThis")
open class Screen {

    private val routeBase: String
        get() = (this::class.simpleName ?: "-") + "?"

    open val completeRoute: String
        get() = routeBase.let { rb ->
            var routeImpl = rb
            pageArgs.forEach { arg ->
                routeImpl = routeImpl.plus("${arg.name}={${arg.name}}")
            }
            routeImpl
        }

    open val pageArgs: List<NamedNavArgument> = emptyList()

    open val title: Any? = null

    open val menuTitle: Any? = null

    open val menuIcon: ImageVector? = null

    open val backgroundColor: Color = Color.DarkGray

    open val roundRadius: Dp = 0.dp

    open val background: Shape = RoundedCornerShape(roundRadius)

    open val showOnce = false

    open val immersive: Boolean = false

    val menuItem
        get() = if (menuTitle != null) MenuItem(
            menuText = menuTitle,
            menuIcon = menuIcon,
            menuRoute = completeRoute
        ) else null

    lateinit var navController: NavHostControllerEx
    var backStackEntry: NavBackStackEntry? = null
    lateinit var args: Map<String, Any?>

    @TvPreview
    @Composable
    @CallSuper
    open fun Compose() {
        val navController = rememberNavControllerEx()
        Compose(
            navController,
            null,
            mapOf()
        )
    }

    @Composable
    open fun Compose(
        navController: NavHostControllerEx,
        backStackEntry: NavBackStackEntry?,
        args: Map<String, Any?>
    ) {
        this.navController = navController
        this.backStackEntry = backStackEntry
        this.args = args
        navController.menuState.value = !immersive
        ComposeScreen()
    }

    @Composable
    open fun ComposeScreen() {
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

    companion object {

        @Suppress("UNCHECKED_CAST", "DEPRECATION")
        private fun <T> NavBackStackEntry.arg(
            argId: String,
            defaultValue: T
        ): T = (arguments?.get(argId) as? T?) ?: defaultValue

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

        @MainThread
        inline fun <reified T : Screen> NavHostController.openAsTop(
            vararg values: Any?
        ) {
            val instance = T::class.createInstance()
            instance.completeRoute.let { r ->
                var routeImpl = r
                instance.pageArgs.forEachIndexed { idx, arg ->
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
                    navigate(
                        finalRoute,
                        navOptions {
                            popBackStack()
                            launchSingleTop = true
                        },
                        null
                    )
                }
            }
        }

        @MainThread
        inline fun <reified T : Screen> NavHostController.open(
            vararg values: Any?
        ) {
            val instance = T::class.createInstance()
            instance.completeRoute.let { r ->
                var routeImpl = r
                instance.pageArgs.forEachIndexed { idx, arg ->
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
                    navigate(finalRoute)
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
                FadeIn
            },
            exitTransition: (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
                FadeOut
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
                navHostController.addMenuItem(menuItem)
            }
            composable(
                route = route.completeRoute,
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

}