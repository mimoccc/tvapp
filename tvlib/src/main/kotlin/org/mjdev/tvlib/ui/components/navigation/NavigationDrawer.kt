/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberDrawerState
import org.mjdev.tvlib.extensions.ModifierExt.conditional

@ExperimentalTvMaterial3Api
@Previews
@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    drawerState: DrawerState = rememberDrawerState(),
    drawerContent: @Composable (DrawerValue) -> Unit = {
        Box(
            modifier = Modifier
                .conditional(visible) {
                    width(200.dp)
                }
                .background(Color.LightGray, RectangleShape)
        )
    },
    content: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize())
    }
) {
    Row(
        modifier = modifier
    ) {
        DrawerSheet(
            drawerState = drawerState,
            content = drawerContent
        )
        content()
    }
}