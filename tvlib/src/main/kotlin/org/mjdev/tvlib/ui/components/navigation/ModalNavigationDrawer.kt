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
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberDrawerState

@ExperimentalTvMaterial3Api
@Previews
@Composable
fun ModalNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(),
    scrimColor: Color = Color.Black.copy(alpha = 0.5f),
    visible: Boolean = true,
    closeDrawerWidth: Dp = if (visible) 200.dp else 0.dp,
    contentAlignment: Alignment = Alignment.TopStart,
    closedDrawerWidth: MutableState<Dp?> = remember { mutableStateOf(null) },
    localDensity: Density = LocalDensity.current,
    onTouchOutside: (() -> Unit)? = null,
    drawerContent: @Composable (DrawerValue) -> Unit = {
        Box(
            modifier = Modifier.background(Color.LightGray, RectangleShape)
        )
    },
    content: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize()
        )
    }
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        DrawerSheet(
            modifier = Modifier
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
                .align(Alignment.CenterStart),
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
            modifier = when (contentAlignment) {
                Alignment.TopStart -> Modifier
                    .padding(
                        closedDrawerWidth.value ?: closeDrawerWidth,
                        0.dp,
                        0.dp,
                        0.dp
                    )

                Alignment.TopEnd -> Modifier
                    .padding(
                        0.dp,
                        0.dp,
                        closedDrawerWidth.value ?: closeDrawerWidth,
                        0.dp
                    )

                else -> Modifier.padding(0.dp)
            },
            contentAlignment = contentAlignment
        ) {
            content()
            if (drawerState.currentValue == DrawerValue.Open) {
                Canvas(
                    Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    onTouchOutside?.invoke()
                                }
                            )
                        }
                ) {
                    drawRect(scrimColor)
                }
            }
        }
    }
}