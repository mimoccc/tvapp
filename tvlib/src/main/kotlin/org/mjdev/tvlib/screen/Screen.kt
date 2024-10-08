/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package org.mjdev.tvlib.screen

import android.annotation.SuppressLint
import androidx.annotation.CallSuper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToQueue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.tv.material3.Text
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.onlyPortrait
import org.mjdev.tvlib.navigation.MenuItem

@Suppress("unused", "PrivatePropertyName")
open class Screen {

    private val TAG = this::class.simpleName.toString()

    open val route: String get() = (this::class.simpleName ?: "none")

    open val routeArgs: List<NamedNavArgument> = emptyList()

    open val title: Any? = null

    open val menuTitle: Any? = null

    open val menuIcon: ImageVector? = Icons.Filled.AddToQueue

    open val showOnce = false

    open val immersive: Boolean = false

    val menuItem
        get() = if (menuTitle != null) MenuItem(
            menuText = menuTitle,
            menuIcon = menuIcon,
            menuRoute = route
        ) else null

    var backStackEntry: NavBackStackEntry? = null
    var args: Map<String, Any?> = emptyMap()

    @SuppressLint("ComposableNaming")
    @Previews
    @Composable
    @CallSuper
    open fun recompose() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onlyPortrait {
                    navigationBarsPadding().statusBarsPadding()
                }
        ) {
            Content()
        }
    }

    @Composable
    open fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (title ?: TAG).toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}
