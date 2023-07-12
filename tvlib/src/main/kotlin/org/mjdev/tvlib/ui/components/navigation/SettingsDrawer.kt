/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun SettingsDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scrimColor: Color = Color.DarkGray.copy(alpha = 0.5f),
    drawerWidth: Dp = 200.dp,
    isEdit: Boolean = isEditMode(),
    content: @Composable () -> Unit = {}
) {
//    val focusRequester = rememberFocusRequester()
//    if (isEdit) drawerState.setValue(DrawerValue.Open)
    content()
//    Box(
//        modifier = Modifier
//            .conditional(drawerState.currentValue == DrawerValue.Open) {
//                navigationBarsPadding()
//                    .statusBarsPadding()
//                    .fillMaxSize()
//            }
//            .conditional(drawerState.currentValue == DrawerValue.Closed) {
//                width(0.dp)
//            }
//            .focusable()
//            .onFocusChanged { state ->
//                if (state.isFocused || state.hasFocus) {
//                    focusRequester.freeFocus()
//                }
//            }
//            .requestFocusOnTouch(focusRequester)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.linearGradient(
//                        colors = listOf(Color.Transparent, scrimColor),
//                    ), RectangleShape
//                ),
//            horizontalAlignment = Alignment.End,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Column(
//                modifier = modifier
//                    .width(
//                        when (drawerState.currentValue) {
//                            DrawerValue.Open -> drawerWidth
//                            else -> 0.dp
//                        }
//                    )
//                    .fillMaxHeight(),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.Top
//            ) {
//                TextAny(
//                    text = "settings",
//                    color = Color.White
//                )
//            }
//        }
//    }
}