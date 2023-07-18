/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ContextExt.isATv
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.helpers.anr.agent.ANRSpyAgent
import org.mjdev.tvlib.helpers.anr.agent.ANRSpyListener
import org.mjdev.tvlib.helpers.anr.models.MethodModel
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.ui.components.navigation.NavHostEx
import org.mjdev.tvlib.ui.components.screen.EmptyScreen
import timber.log.Timber

// todo theme
@Suppress("MemberVisibilityCanBePrivate", "unused", "LeakingThis")
open class ComposableActivity : ComponentActivity() {

    val activityResultListeners = mutableListOf<ActivityResultHandler<*>>()

    open val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        screen(route = EmptyScreen())
    }

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

    open val backgroundColor = Color(0xff202020)
    open val roundCornerSize: Dp = 0.dp
    open val backgroundShape: Shape = RoundedCornerShape(roundCornerSize)

    fun reportANR() {
        anrSpyAgent.start()
    }

    @TvPreview
    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    @CallSuper
    open fun Compose() {

        val navController = rememberNavControllerEx()

        MaterialTheme {
            Surface(
                modifier = Modifier
                    .background(backgroundColor, backgroundShape)
                    .fillMaxSize(),
                shape = RectangleShape,
                colors = NonInteractiveSurfaceDefaults.colors(
                    containerColor = backgroundColor
                )
            ) {
                NavHostEx(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    builder = navGraphBuilder
                )
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            reportANR()
        }
        WindowCompat.setDecorFitsSystemWindows(window, !isATv)
        setContent {
            Compose()
        }
    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        activityResultListeners.forEach { listener ->
            try {
                listener.postResult(requestCode, resultCode, intent)
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

    fun <T> registerForActivityResult(
        onLaunch: (args: List<T>) -> Unit,
        onActivityResult: (requestCode: Int, resultCode: Int, intent: Intent?) -> Unit
    ): ActivityResultHandler<T> = ActivityResultHandler(
        this,
        lifecycle,
        onLaunch,
        onActivityResult
    )

}