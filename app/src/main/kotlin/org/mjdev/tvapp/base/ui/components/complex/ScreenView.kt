/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx

@Suppress("TrailingComma", "unused")
@TvPreview
@Composable
fun ScreenView(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    content: @Composable () -> Unit = { EmptyScreen() }
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        if (navController.menuState.value) {
            Navigation(
                navController = navController,
                content = {
                    content()
                }
            )
        } else {
            content()
        }
    }
}