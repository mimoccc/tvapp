/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.Screen
import org.mjdev.tvapp.base.state.ScreenState
import org.mjdev.tvapp.base.state.ScreenState.Companion.rememberScreenState
import org.mjdev.tvapp.base.ui.components.navigation.Navigation

@Suppress("TrailingComma", "UNUSED_ANONYMOUS_PARAMETER", "unused")
@TvPreview
@Composable
fun ScreenView(
    navController: NavHostController? = null,
    navigationBackgroundColor:Color = Color(0xff202020),
    actions: @Composable RowScope.() -> Unit = {},
    title: Any? = R.string.app_name,
    menuItems: List<MenuItem> = listOf(),
    content: @Composable (
        state: ScreenState,
    ) -> Unit = { state ->
        Screen()
    }
) {

    val isEdit = isEditMode()
    val screenState = rememberScreenState(title)
    val errorState = remember { screenState.errorState }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {

        Navigation(
            navController = navController,
            backgroundColor = navigationBackgroundColor,
            items = menuItems,
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(screenState)
                }
            }
        )

        if (isEdit || (errorState.value != null)) {
            ErrorMessage(errorState.value)
        }

    }

}