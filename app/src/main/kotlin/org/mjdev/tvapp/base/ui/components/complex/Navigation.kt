/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.MenuItem
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.NavigationState
import org.mjdev.tvapp.base.ui.components.navigation.rememberNavigationState

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    navigationState: NavigationState = rememberNavigationState(
        navController = navController,
        drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        )
    ),
    backgroundColor: Color = Color(0xff202020),
    roundCornerSize: Dp = 8.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    content: @Composable () -> Unit = {},
    menuItems: List<MenuItem> = listOf()
) {

    val isEdit = isEditMode()

    if (isEdit) navigationState.openDrawer()

    navController.addMenuItem(*menuItems.toTypedArray())

    if (navController.menuState.value) {
        NavigationDrawer(
            modifier = modifier
                .fillMaxHeight()
                .background(backgroundColor, shape)
                .border(borderSize, borderColor, shape),
            content = content,
            drawerState = navigationState.drawerState,
            drawerContent = { state ->
                NavDrawerContent(
                    backgroundColor = backgroundColor,
                    navController = navController,
                    navigationState = navigationState,
                )
                navigationState.drawerState.setValue(state)
            }
        )
    } else {
        content()
    }

}