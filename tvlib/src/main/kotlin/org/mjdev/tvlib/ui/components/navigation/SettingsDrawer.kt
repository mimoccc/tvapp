/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    drawerState: DrawerState = rememberDrawerState(
        if (isEditMode()) DrawerValue.Open
        else DrawerValue.Closed
    ),
    onTouchOutside: () -> Unit = {},
    content: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize())
    },
) {
    ModalNavigationDrawer(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd,
        drawerState = drawerState,
        onTouchOutside = onTouchOutside,
        drawerContent = {
            SettingsDrawerContent(
            )
        }
    ) {
        content()
    }
}