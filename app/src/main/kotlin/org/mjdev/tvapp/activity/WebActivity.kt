/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvlib.activity.ComposableActivity
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.WebScreen
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavControllerExt.openAsTop
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.startScreen
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.navigation.NavHostControllerEx

@AndroidEntryPoint
class WebActivity : ComposableActivity() {

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        startScreen(WebScreen(), true)
    }

    override fun onIntent(navController: NavHostControllerEx, intent: Intent?) {
        val data : Uri = intent?.data ?: "about:blank".parseUri()
        navController.openAsTop<WebScreen>(data)
    }

}