/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.screen.Screen

class LoadingScreen : Screen() {

//    private val appInfo = ApplicationInfo()

    override val title = R.string.msg_loading
    override val menuIcon: ImageVector get() = Icons.Filled.Tv
    override val immersive: Boolean = true
    override val showOnce: Boolean = true

//    @OptIn(ExperimentalTvMaterial3Api::class)
    @Previews
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
//                .background(Color.Black, RectangleShape),
            contentAlignment = Alignment.Center,
        ) {
//            Text(
//                text = appInfo.getInfo()
//            )
        }
    }

}
