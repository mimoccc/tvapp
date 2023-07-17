/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package org.mjdev.tvlib.screen

import androidx.annotation.CallSuper
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
import androidx.tv.material3.Text
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx

@Suppress("unused", "LeakingThis")
open class Screen {

    open val route: String
        get() = (this::class.simpleName ?: "none")

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
            menuRoute = route
        ) else null

    var navController: NavHostControllerEx? = null
    var backStackEntry: NavBackStackEntry? = null
    var args: Map<String, Any?> = emptyMap()

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

}