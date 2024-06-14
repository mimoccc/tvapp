/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.onlyPortrait
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx

@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun NavDrawerContent(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    // todo, padding
    minPortraitMenuWidth: Dp = 4.dp,
) {
    val topItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Top
    }
    val bottomItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Bottom
    }
    val centerItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Center
    }
    // todo
    val paddingState = remember(navController.menuDrawerState.currentValue) {
        derivedStateOf {
            when (navController.menuDrawerState.currentValue) {
                DrawerValue.Closed -> minPortraitMenuWidth
                DrawerValue.Open -> 0.dp
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .onlyPortrait {
                widthIn(min = paddingState.value)
            },
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            bottomItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    NavigationRow(
                        menuItem = menuItem,
                        navController = navController,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            topItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    NavigationRow(
                        menuItem = menuItem,
                        navController = navController
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            centerItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    NavigationRow(
                        menuItem = menuItem,
                        navController = navController
                    )
                }
            }
        }
    }
}