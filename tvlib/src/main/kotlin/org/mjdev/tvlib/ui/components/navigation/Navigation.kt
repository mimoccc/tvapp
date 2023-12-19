/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode
import org.mjdev.tvlib.extensions.ComposeExt.isOpen
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ModifierExt.onlyPortrait
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.page.Page

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem> = if (isEditMode()) listOf(
        MenuItem.MENU_ITEM_EXIT,
        MenuItem.MENU_ITEM_SETTINGS,
        MenuItem.MENU_ITEM_SEARCH
    ) else listOf(),
    modal: Boolean = isPortraitMode(),
    backgroundColor: Color = Color.Black.copy(alpha = 0.7f),
    navController: NavHostControllerEx = rememberNavControllerEx(menuItems),
    minPortraitMenuWidth: Dp = 4.dp,
    content: @Composable () -> Unit = { Page() },
) {
    if (isEditMode()) navController.openMenu()
    val mainContent: @Composable () -> Unit = {
        Box(
            modifier.fillMaxSize()
        ) {
            content()
        }
    }
    val drawerContent: @Composable (DrawerValue) -> Unit = { state ->
        Box(
            modifier = Modifier
                .background(
                    if (isLandscapeMode() || navController.menuDrawerState.isOpen)  backgroundColor
                    else Color.Transparent
                )
        ) {
            NavDrawerContent(
                minPortraitMenuWidth = minPortraitMenuWidth,
                navController = navController,
            )
        }
        navController.menuDrawerState.setValue(state)
    }
    Box(
        modifier
            .fillMaxSize()
            .onlyPortrait {
                // todo not working on lollipop
                navigationBarsPadding().statusBarsPadding()
            }
    ) {
        SettingsDrawer(
            modifier = Modifier.fillMaxSize(),
            drawerState = navController.settingsDrawerState,
            onTouchOutside = {
                navController.closeSettings()
            }
        ) {
            if (modal) {
                ModalNavigationDrawer(
                    modifier = modifier.fillMaxSize(),
                    drawerState = navController.menuDrawerState,
                    content = mainContent,
                    drawerContent = drawerContent,
                    onTouchOutside = {
                        navController.closeMenu()
                    },
                    visible = navController.menuState.value,
                )
            } else {
                NavigationDrawer(
                    modifier = modifier.fillMaxSize(),
                    drawerState = navController.menuDrawerState,
                    content = mainContent,
                    drawerContent = drawerContent,
                    visible = navController.menuState.value,
                )
            }
        }
    }
}