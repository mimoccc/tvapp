/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.screen.Screen.Companion.open

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun NavDrawerContent(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    backgroundColor: Color = Color(0xff202020),
    onDrawerItemClick: (id: Int) -> Unit = { id ->
        navController.menuItem(id).let { menuItem ->
            if (menuItem.isAction) {
                menuItem.menuAction?.invoke()
            }
            if (menuItem.isRoute) {
                navController.open(menuItem.menuRoute)
            }
            if (menuItem.isPage) {
                navController.onMenuItemClick(menuItem)
            }
        }
    },
    onDrawerItemFocus: (id: Int) -> Unit = { id ->
        navController.menuItem(id).let { menuItem ->
            if (menuItem.isPage) {
                navController.onMenuItemClick(menuItem)
            }
        }
    },
) {
    val focusedIdx = remember { mutableIntStateOf(-1) }
    val topItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Top
    }
    val bottomItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Bottom
    }
    val centerItems = navController.menuItems.filter { mi ->
        mi.menuGravity == MenuItem.Gravity.Center
    }
    val itemId: (menuItem: MenuItem) -> Int = { menuItem ->
        navController.indexOfMenuItem(menuItem)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        bottomItems.forEach { menuItem ->
            if (menuItem.isEnabled) {
                NavigationRow(
                    id = itemId(menuItem),
                    drawerState = navController.menuDrawerState,
                    icon = menuItem.menuIcon,
                    text = menuItem.menuText,
                    onFocus = { id ->
                        navController.openMenu()
                        focusedIdx.value = id
                        onDrawerItemFocus(id)
                    },
                    onClick = { id ->
                        onDrawerItemClick(id)
                    }
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        topItems.forEach { menuItem ->
            if (menuItem.isEnabled) {
                NavigationRow(
                    id = itemId(menuItem),
                    drawerState = navController.menuDrawerState,
                    icon = menuItem.menuIcon,
                    text = menuItem.menuText,
                    onFocus = { id ->
                        navController.openMenu()
                        focusedIdx.value = id
                        onDrawerItemFocus(id)
                    },
                    onClick = { id ->
                        onDrawerItemClick(id)
                    }
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        centerItems.forEach { menuItem ->
            if (menuItem.isEnabled) {
                NavigationRow(
                    id = itemId(menuItem),
                    drawerState = navController.menuDrawerState,
                    text = menuItem.menuText,
                    icon = menuItem.menuIcon,
                    onFocus = { focusId ->
                        navController.openMenu()
                        focusedIdx.value = focusId
                        onDrawerItemFocus(focusId)
                    },
                    onClick = { id ->
                        onDrawerItemClick(id)
                    }
                )
            }
        }
    }

}