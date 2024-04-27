/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.WebScreen
import org.mjdev.tvlib.activity.TvActivity
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavControllerExt.openAsTop
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.startScreen
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.navigation.NavHostControllerEx

@Suppress("PreviewShouldNotBeCalledRecursively")
@AndroidEntryPoint
class WebActivity : TvActivity() {

    override val backgroundColor: Color = Color.Black

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        startScreen(WebScreen(), true)
    }

    override fun onIntent(navController: NavHostControllerEx, intent: Intent?) {
        val data: Any? = when (intent?.action) {
            Intent.ACTION_VIEW -> intent.data ?: "about:blank".parseUri()
            Intent.ACTION_SEND -> intent.clipData.let {
                it?.getItemAt(0)
            }?.text
            else -> null
        }
        navController.openAsTop<WebScreen>(data)
    }

}