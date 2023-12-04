/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.content.Intent
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.ui.screens.GalleryScreen
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.IPTVScreen
import org.mjdev.tvapp.ui.screens.LoadingScreen
import org.mjdev.tvlib.activity.TvActivity
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavControllerExt.openAsTop
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.navigation.NavHostControllerEx

@AndroidEntryPoint
class IPTVActivity : TvActivity() {

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {

        screen(
            route = LoadingScreen(),
            isHomeScreen = true,
            isStartScreen = true,
        )
        screen(route = IPTVScreen())
        screen(route = GalleryScreen())

    }

    @Suppress("DEPRECATION", "MoveVariableDeclarationIntoWhen")
    override fun onIntent(navController: NavHostControllerEx, intent: Intent?) {
        val data = intent?.getSerializableExtra(IPTV_DATA)
        when (data) {
            null -> finish()
            is Movie -> navController.openAsTop<IPTVScreen>(data)
            is ItemAudio -> navController.openAsTop<IPTVScreen>(data)
            is ItemVideo -> navController.openAsTop<IPTVScreen>(data)
            is ItemPhoto -> navController.openAsTop<GalleryScreen>(data)
            else -> navController.openAsTop<GalleryScreen>(data)
        }
    }

    companion object {

        const val IPTV_DATA = "IPTV_DATA"

    }

}