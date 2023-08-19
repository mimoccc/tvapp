/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.text.TextAny

class LoadingScreen : Screen() {

    override val title = R.string.title_loading
    override val menuIcon: ImageVector get() = Icons.Filled.Tv
    override val immersive: Boolean = true
    override val showOnce: Boolean = true

    @TvPreview
    @Composable
    override fun ComposeScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, RectangleShape),
            contentAlignment = Alignment.Center,
        ) {
            TextAny(
                text = stringResource(id = title),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}