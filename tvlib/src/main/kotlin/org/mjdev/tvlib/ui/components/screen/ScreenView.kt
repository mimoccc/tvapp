/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.navigation.Navigation

@Suppress("TrailingComma", "unused")
@TvPreview
@Composable
fun ScreenView(
    modifier: Modifier = Modifier,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    content: @Composable () -> Unit = { EmptyScreen() }
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .recomposeHighlighter()
            .navigationBarsPadding()
            .statusBarsPadding(),
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