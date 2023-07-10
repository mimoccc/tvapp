/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.Navigation

@Suppress("TrailingComma", "unused")
@TvPreview
@Composable
fun ScreenView(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    content: @Composable () -> Unit = { EmptyScreen() }
) {
    Box(
        modifier = Modifier.fillMaxSize().recomposeHighlighter(),
        contentAlignment = Alignment.TopStart,
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