/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx

@Suppress("TrailingComma", "unused")
@TvPreview
@Composable
fun ScreenView(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    backgroundColor: Color = Color.DarkGray,
    roundCornerSize: Dp = 16.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    content: @Composable () -> Unit = { EmptyScreen() }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor, shape),
        contentAlignment = Alignment.BottomCenter,
    ) {
        if (navController.menuState.value) {
            Navigation(
                navController = navController,
                backgroundColor = backgroundColor,
                content = {
                    content()
                }
            )
        } else {
            content()
        }
    }
}