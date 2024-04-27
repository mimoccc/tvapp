/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode
import org.mjdev.tvlib.extensions.ComposeExt.isOpen
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.focusState
import org.mjdev.tvlib.extensions.NavControllerExt.open
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.complex.FocusableBox
import org.mjdev.tvlib.ui.components.icon.IconAny
import org.mjdev.tvlib.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    iconColor: Color = Color.White,
    focusedColor: Color = Color.Green,
    unFocusedColor: Color = Color.Transparent,
    expandedWidth: Dp = 150.dp,
    roundCornerSize: Dp = 32.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    strokeWidth: Dp = 1.dp,
    margin: Dp = 0.dp,
    padding: Dp = 4.dp,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    menuItem: MenuItem = MenuItem.MENU_ITEM_EXIT,
) {
    val id = navController.indexOfMenuItem(menuItem)
    val focusRequester: FocusRequester = rememberFocusRequester()
    val onDrawerItemFocus: () -> Unit = {
        navController.menuItem(id).let { menuItem ->
            if (menuItem.isPage) {
                navController.onMenuItemClick(menuItem)
            }
        }
    }
    val onFocus: () -> Unit = {
        navController.openMenu()
        navController.selectedMenuItem.intValue = id
        onDrawerItemFocus()
    }
    val drawerState = navController.menuDrawerState
    val selected = (navController.selectedMenuItem.intValue == id)
    val focusState = rememberFocusState()
    val isEdit = isEditMode()
    val isShown = isLandscapeMode() || drawerState.isOpen
    val isPortrait = isPortraitMode()
    val enter = if (isPortrait) expandVertically()
    else (fadeIn() + expandVertically())
    val exit = if (isPortrait) shrinkVertically()
    else (fadeOut() + shrinkVertically())
    val onClick: () -> Unit = {
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
    }
    if (isShown) {
        FocusableBox(
            modifier = modifier
                .padding(margin)
                .border(
                    BorderStroke(
                        strokeWidth,
                        if (isEdit || selected) focusedColor else unFocusedColor
                    ),
                    shape
                )
                .focusState(focusState),
            shape = shape,
            focusedColor = focusedColor.copy(alpha = 0.5f),
            focusRequester = focusRequester,
            unFocusedColor = unFocusedColor,
            onFocusChange = { state, _ -> // todo from user ?
                if (state.isFocused || state.hasFocus) {
                    onFocus()
                }
            },
            onClick = { onClick() },
        ) {
            Row(
                modifier = Modifier.padding(padding),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(
                            if (isEdit || focusState.isFocused) focusedColor else unFocusedColor,
                            CircleShape
                        )
                ) {
                    IconAny(
                        src = menuItem.menuIcon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
                AnimatedVisibility(
                    enter = enter,
                    exit = exit,
                    visible = (isEdit || (drawerState.currentValue == DrawerValue.Open))
                ) {
                    TextAny(
                        text = menuItem.menuText,
                        softWrap = false,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(expandedWidth),
                        color = textColor,
                        textAlign = TextAlign.Left
                    )
                }
            }
        }
    }
}