/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
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
    navController: NavHostControllerEx = rememberNavControllerEx(),
    content: @Composable () -> Unit = { Page() },
//    imageLoader: ImageLoader = rememberImageLoader(),
    menuItems: List<MenuItem> = if (isEditMode()) listOf(
        MenuItem.MENU_ITEM_EXIT,
        MenuItem.MENU_ITEM_SETTINGS,
        MenuItem.MENU_ITEM_SEARCH
    ) else listOf(),
    modal: Boolean = isPortraitMode(),
) {
    navController.addMenuItem(*menuItems.toTypedArray())
    val isEdit: Boolean = isEditMode()
    if (isEdit) navController.openMenu()
    val mainContent: @Composable () -> Unit = {
        Box(
            modifier
                .fillMaxSize()
                .recomposeHighlighter()
        ) {
            content()
        }
    }
    Box(
        modifier
            .fillMaxSize()
            .recomposeHighlighter()
    ) {
        SettingsDrawer(
            modifier = Modifier.fillMaxSize(),
            drawerState = navController.settingsDrawerState,
            onTouchOutside = {
                navController.closeSettings()
            },
//            imageLoader = imageLoader
        ) {
            if (navController.isMenuEnabled) {
                val drawerContent: @Composable (DrawerValue) -> Unit = { state ->
                    Box(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .statusBarsPadding()
                    ) {
                        NavDrawerContent(
                            navController = navController,
                        )
                    }
                    navController.menuDrawerState.setValue(state)
                }
                if (modal) {
                    ModalNavigationDrawer(
                        modifier = modifier.fillMaxHeight(),
                        drawerState = navController.menuDrawerState,
                        content = mainContent,
                        drawerContent = drawerContent,
                        onTouchOutside = {
                            navController.closeMenu()
                        }
                    )
                } else {
                    NavigationDrawer(
                        modifier = modifier.fillMaxHeight(),
                        drawerState = navController.menuDrawerState,
                        content = mainContent,
                        drawerContent = drawerContent,
//                        imageLoader = imageLoader
                    )
                }
            } else {
                mainContent()
            }
        }
    }
}