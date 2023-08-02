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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun SettingsDrawer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xff202020),
    roundCornerSize: Dp = 16.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    drawerState: DrawerState = rememberDrawerState(
        if (ComposeExt.isEditMode()) DrawerValue.Open else DrawerValue.Closed
    ),
    content: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize())
    },
) {
    ModalNavigationDrawer(
        contentAlignment = Alignment.TopEnd,
        drawerState = drawerState,
        drawerContent = {
            SettingsDrawerContent(
                backgroundColor = backgroundColor,
                roundCornerSize = roundCornerSize,
                borderSize = borderSize,
                borderColor = borderColor,
                shape = shape
            )
        }
    ) {
        content()
    }
}