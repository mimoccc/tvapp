/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.compose.runtime.Composable
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState

@Suppress("unused")
@OptIn(ExperimentalTvMaterial3Api::class)
class NavigationState constructor(
    val navController: NavHostControllerEx,
    val drawerState: DrawerState = DrawerState(DrawerValue.Open),
) {

    fun openDrawer() {
        drawerState.open()
    }

    fun closeDrawer() {
        drawerState.close()
    }

}

@OptIn(ExperimentalTvMaterial3Api::class)
fun DrawerState.open() {
    if (currentValue == DrawerValue.Closed) {
        setValue(DrawerValue.Open)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun DrawerState.close() {
    if (currentValue == DrawerValue.Open) {
        setValue(DrawerValue.Closed)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun rememberNavigationState(
    navController: NavHostControllerEx,
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Open
    ),
) = NavigationState(
    navController = navController,
    drawerState = drawerState,
)