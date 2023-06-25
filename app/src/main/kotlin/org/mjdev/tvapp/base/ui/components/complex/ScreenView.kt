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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.Screen
import org.mjdev.tvapp.base.state.ActivityViewState
import org.mjdev.tvapp.base.state.ActivityViewState.Companion.rememberActivityViewState
import org.mjdev.tvapp.base.ui.components.navigation.Navigation

@Suppress("TrailingComma", "UNUSED_ANONYMOUS_PARAMETER", "unused")
@TvPreview
@Composable
fun ScreenView(
    navController: NavHostController? = null,
    navigationIconMain: Any? = Icons.Filled.Menu,
    navigationIconBack: Any? = Icons.Filled.ArrowBack,
    actions: @Composable RowScope.() -> Unit = {},
    title: Any? = R.string.app_name,
    menuItems: List<MenuItem> = listOf(),
    content: @Composable (
        state: ActivityViewState,
    ) -> Unit = { state ->
        Screen()
    }
) {

    val activityViewState = rememberActivityViewState(title)
    val errorState = remember { activityViewState.errorState }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        Navigation(
            items = menuItems,
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(activityViewState)
                }
            }
        )
        if (errorState.value != null) {
            ErrorMessage(errorState.value)
        }
    }

}