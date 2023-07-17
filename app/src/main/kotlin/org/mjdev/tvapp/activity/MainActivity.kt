/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.os.Bundle
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvlib.activity.ComposableActivity
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.MainScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.ui.screens.SplashScreen
import org.mjdev.tvlib.helpers.anr.agent.ANRSpyAgent
import org.mjdev.tvlib.helpers.anr.agent.ANRSpyListener
import org.mjdev.tvlib.helpers.anr.models.MethodModel
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComposableActivity() {

    private var anrCallback = object : ANRSpyListener {
        override fun onWait(ms: Long) {
        }

        @Deprecated("This callback will be removed in future")
        override fun onAnrStackTrace(stackstrace: Array<StackTraceElement>) {
        }

        override fun onReportAvailable(methodList: List<MethodModel>) {
        }

        override fun onAnrDetected(
            details: String,
            stackTrace: Array<StackTraceElement>,
            packageMethods: List<String>?
        ) {
            Timber.e(details)
            Timber.e(stackTrace.toString())
            Timber.e(packageMethods.toString())
        }
    }

    private val anrSpyAgent = ANRSpyAgent.Builder(this)
        .setTimeOut(500)
        .setSpyListener(anrCallback)
        .setThrowException(false)
        .enableReportAnnotatedMethods(true)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            anrSpyAgent.start()
        }
    }

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    override val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {

        screen(route = SplashScreen(), isStartScreen = true)
        screen(route = MainScreen(), isHomeScreen = true)
        screen(route = DetailScreen())
        screen(route = PlayerScreen())

    }
}