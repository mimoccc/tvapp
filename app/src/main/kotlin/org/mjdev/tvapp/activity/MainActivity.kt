/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.content.Context
import androidx.compose.runtime.Composable
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton
import org.mjdev.tvapp.app.Application
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.module.ViewModelsModule
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.SplashScreen
import org.mjdev.tvlib.activity.TvActivity
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.homeScreen
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.startScreen

@Suppress("PreviewShouldNotBeCalledRecursively")
class MainActivity : TvActivity(), DIAware {

    override val di by DI.lazy {
        bind<Context>() with singleton { this@MainActivity }
        bind<DAO>() with singleton { (applicationContext as Application).DAO }
        import(ViewModelsModule)
    }

    @Previews
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        startScreen(SplashScreen())
        homeScreen(MainScreen())
    }

}