/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.ui.components.MediaPlayer
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen

class IPTVScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_iptv
    override val menuIcon: ImageVector get() = Icons.Filled.PlayArrow
    override val immersive: Boolean = true

    override val pageArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val data: Any? = remember { args[data] }

        MediaPlayer(
            modifier = Modifier.fillMaxSize(),
            data = data
        )

    }

}