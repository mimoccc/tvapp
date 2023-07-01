/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvapp.base.annotations.TvPreview

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun SettingsDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scrimColor: Color = MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f),
    content: @Composable () -> Unit = {}
) = ModalNavigationDrawer(
    modifier = modifier,
    drawerState = drawerState,
    scrimColor = scrimColor,
    content = content,
    drawerContent = {
        // todo
    },

    )