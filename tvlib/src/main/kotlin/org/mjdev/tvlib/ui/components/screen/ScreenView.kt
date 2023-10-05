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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.navigation.Navigation
import org.mjdev.tvlib.ui.components.page.Page

@Suppress("TrailingComma", "unused")
@Previews
@Composable
fun ScreenView(
    modifier: Modifier = Modifier,
    navController: NavHostControllerEx = rememberNavControllerEx(),
    content: @Composable () -> Unit = { Page() }
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        contentAlignment = Alignment.Center,
    ) {
        if (navController.menuState.value) {
            Navigation(
                content = content
            )
        } else content()
    }
}