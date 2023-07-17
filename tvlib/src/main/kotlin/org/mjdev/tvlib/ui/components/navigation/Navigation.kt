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
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NavigationDrawer
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx

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
    content: @Composable () -> Unit = {},
    menuItems: List<MenuItem> = listOf(),
    isEdit: Boolean = isEditMode()
) {
    if (isEdit) navController.openMenu()
    navController.addMenuItem(*menuItems.toTypedArray())
    if (navController.isMenuEnabled) {
        SettingsDrawer(
            drawerState = navController.settingsDrawerState,
            modifier = Modifier.fillMaxHeight().recomposeHighlighter()
        ) {
            NavigationDrawer(
                modifier = modifier
                    .fillMaxHeight()
                    .background(backgroundColor, shape)
                    .border(borderSize, borderColor, shape),
                drawerState = navController.menuDrawerState,
                content = {
                    Box(
                        modifier.fillMaxSize().recomposeHighlighter()
                    ) {
                        content()
                    }
                },
                drawerContent = { state ->
                    NavDrawerContent(
                        backgroundColor = backgroundColor,
                        navController = navController,
                    )
                    navController.menuDrawerState.setValue(state)
                }
            )
        }
    } else {
        Box(
            modifier.fillMaxHeight().recomposeHighlighter()
        ) {
            content()
        }
    }
}