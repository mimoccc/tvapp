/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NetworkCell
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.sync.SyncAdapter.Companion.pauseSyncUntilGone
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.web.WebView

class WebScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_web
    override val menuIcon: ImageVector get() = Icons.Filled.NetworkCell
    override val immersive: Boolean = true
    override val showOnce: Boolean = true

    override val routeArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @Previews
    @Composable
    override fun Content() {
        val data: Any? = remember(args) { args[data] }
        pauseSyncUntilGone()
        WebView(
            modifier = Modifier.fillMaxSize(),
            url = data?.toString() ?: "about:blank",
        )
    }

}
