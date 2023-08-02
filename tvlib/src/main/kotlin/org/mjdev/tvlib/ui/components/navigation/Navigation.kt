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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.page.Page

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    backgroundColor: Color = Color(0xff202020),
    roundCornerSize: Dp = 8.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    content: @Composable () -> Unit = {
        Page()
    },
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
            modifier = Modifier.fillMaxSize()
        ) {
            if (navController.isMenuEnabled) {
                val drawerContent: @Composable (DrawerValue) -> Unit = { state ->
                    NavDrawerContent(
                        backgroundColor = backgroundColor,
                        navController = navController,
                    )
                    navController.menuDrawerState.setValue(state)
                }
                if (modal) {
                    ModalNavigationDrawer(
                        modifier = modifier
                            .fillMaxHeight()
                            .background(backgroundColor, shape)
                            .border(borderSize, borderColor, shape),
                        drawerState = navController.menuDrawerState,
                        content = mainContent,
                        drawerContent = drawerContent,
                    )
                } else {
                    NavigationDrawer(
                        modifier = modifier
                            .fillMaxHeight()
                            .background(backgroundColor, shape)
                            .border(borderSize, borderColor, shape),
                        drawerState = navController.menuDrawerState,
                        content = mainContent,
                        drawerContent = drawerContent
                    )
                }
            } else {
                mainContent()
            }
        }
    }
}