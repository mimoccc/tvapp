/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvlib.annotations.TvPreview

@ExperimentalTvMaterial3Api
@TvPreview
@Composable
fun ModalNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scrimColor: Color = Color.Black.copy(alpha = 0.5f),
    closeDrawerWidth: Dp = 80.dp,
    drawerContent: @Composable (DrawerValue) -> Unit = {
        Box(
            modifier = Modifier
                .background(Color.LightGray, RectangleShape)
        )
    },
    content: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize())
    }
) {
    val localDensity = LocalDensity.current
    val closedDrawerWidth: MutableState<Dp?> = remember { mutableStateOf(null) }
    val internalDrawerModifier =
        Modifier
            .zIndex(Float.MAX_VALUE)
            .onSizeChanged {
                if (closedDrawerWidth.value == null &&
                    drawerState.currentValue == DrawerValue.Closed
                ) {
                    with(localDensity) {
                        closedDrawerWidth.value = it.width.toDp()
                    }
                }
            }

    Box(modifier = modifier) {
        DrawerSheet(
            modifier = internalDrawerModifier.align(Alignment.CenterStart),
            drawerState = drawerState,
            sizeAnimationFinishedListener = { _, targetSize ->
                if (drawerState.currentValue == DrawerValue.Closed) {
                    with(localDensity) {
                        closedDrawerWidth.value = targetSize.width.toDp()
                    }
                }
            },
            content = drawerContent
        )
        Box(
            modifier = Modifier.padding(
                closedDrawerWidth.value ?: closeDrawerWidth,
                0.dp,
                0.dp,
                0.dp
            )
        ) {
            content()
            if (drawerState.currentValue == DrawerValue.Open) {
                Canvas(Modifier.fillMaxSize()) {
                    drawRect(scrimColor)
                }
            }
        }
    }
}