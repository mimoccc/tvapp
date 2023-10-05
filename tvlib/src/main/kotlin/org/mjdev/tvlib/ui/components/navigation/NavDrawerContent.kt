/*
 * Copyright (c) Milan Jurkulák 2023.
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.onlyPortrait
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.NavControllerExt.open
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun NavDrawerContent(
    backgroundColor: Color = Color.DarkGray,
    shape: Shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 0.dp
    ),
    navController: NavHostControllerEx = rememberNavControllerEx(),
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
    focusedIdx: MutableIntState = remember { mutableIntStateOf(-1) },
    itemId: (menuItem: MenuItem) -> Int = { menuItem ->
        navController.indexOfMenuItem(menuItem)
    },
    // todo, padding
    minPortraitMenuWidth: Dp = 4.dp,
    menuDrawerState: DrawerState = navController.menuDrawerState
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
    val paddingState = remember(menuDrawerState.currentValue) {
        derivedStateOf {
            when(menuDrawerState.currentValue) {
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
            }
            .recomposeHighlighter(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .recomposeHighlighter(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            bottomItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    val id = itemId(menuItem)
                    NavigationRow(
                        id = id,
                        focused = (focusedIdx.value == id),
                        drawerState = navController.menuDrawerState,
                        icon = menuItem.menuIcon,
                        text = menuItem.menuText,
                        onFocus = { focusId ->
                            navController.openMenu()
                            focusedIdx.value = focusId
                            onDrawerItemFocus(focusId)
                        },
                        onClick = { clickId ->
                            onDrawerItemClick(clickId)
                        }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .recomposeHighlighter(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            topItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    val id = itemId(menuItem)
                    NavigationRow(
                        id = id,
                        focused = (focusedIdx.value == id),
                        drawerState = navController.menuDrawerState,
                        icon = menuItem.menuIcon,
                        text = menuItem.menuText,
                        onFocus = { focusId ->
                            navController.openMenu()
                            focusedIdx.value = focusId
                            onDrawerItemFocus(focusId)
                        },
                        onClick = { clickId ->
                            onDrawerItemClick(clickId)
                        }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .recomposeHighlighter(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            centerItems.forEach { menuItem ->
                if (menuItem.isEnabled) {
                    val id = itemId(menuItem)
                    NavigationRow(
                        id = id,
                        focused = (focusedIdx.value == id),
                        drawerState = navController.menuDrawerState,
                        text = menuItem.menuText,
                        icon = menuItem.menuIcon,
                        onFocus = { focusId ->
                            navController.openMenu()
                            focusedIdx.value = focusId
                            onDrawerItemFocus(focusId)
                        },
                        onClick = { clickId ->
                            onDrawerItemClick(clickId)
                        }
                    )
                }
            }
        }
    }
}