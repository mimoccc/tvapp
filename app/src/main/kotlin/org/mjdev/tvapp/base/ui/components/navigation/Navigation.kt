/*
 * Copyright (c) Milan Jurkulák 2023. 
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.navigation.MenuItem

const val SETTINGS_ITEM = 65535
const val SEARCH_ITEM = 65534

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    items: List<MenuItem> = listOf(),
    backgroundColor: Color = Color(0xFF202020),
    roundSize: Dp = 8.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.White,
    showSearch: Boolean = true,
    showSettings: Boolean = true,
    searchArrangement: Arrangement.Vertical = Arrangement.Top,
    settingsArrangement: Arrangement.Vertical = Arrangement.Bottom,
    onDrawerItemClick: (id: Int) -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    val isEdit = isEditMode()
    val drawerState = rememberDrawerState(if (isEdit) DrawerValue.Open else DrawerValue.Closed)
    val focused = remember { mutableIntStateOf(-1) }

    NavigationDrawer(
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor)
            .apply {
                if (borderSize > 0.dp) {
                    border(borderSize, borderColor, RoundedCornerShape(roundSize))
                }
            },
        content = content,
        drawerState = drawerState,
        drawerContent = { state ->

            focused.value.apply {

                if (showSettings) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = settingsArrangement,
                        horizontalAlignment = Alignment.Start,
                    ) {

                        NavigationRow(
                            id = SETTINGS_ITEM,
                            drawerValue = state,
                            icon = Icons.Outlined.Settings,
                            text = "Settings",
                            onFocus = { id ->
                                drawerState.setValue(DrawerValue.Open)
                                focused.value = id
                            },
                            onClick = { id ->
                                onDrawerItemClick(id)
                            }
                        )

                    }
                }

                if (showSearch) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = searchArrangement,
                        horizontalAlignment = Alignment.Start,
                    ) {

                        NavigationRow(
                            id = SEARCH_ITEM,
                            drawerValue = state,
                            icon = Icons.Outlined.Search,
                            text = "Search",
                            onFocus = { id ->
                                drawerState.setValue(DrawerValue.Open)
                                focused.value = id
                            },
                            onClick = { id ->
                                onDrawerItemClick(id)
                            }
                        )

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {

                    items.forEachIndexed { idx, menuItem ->
                        NavigationRow(
                            id = idx,
                            drawerValue = state,
                            text = menuItem.menuText,
                            icon = menuItem.menuIcon,
                            onFocus = { id ->
                                drawerState.setValue(DrawerValue.Open)
                                focused.value = id
                            },
                            onClick = { id ->
                                onDrawerItemClick(id)
                            }
                        )
                    }

                }

            }
        }
    )

}