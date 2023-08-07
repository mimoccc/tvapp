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
import com.github.anrwatchdog.ANRWatchDog
import com.google.ads.interactivemedia.v3.internal.anr
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ContextExt.isATv
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.navigation.NavHostEx
import org.mjdev.tvlib.ui.components.screen.EmptyScreen
import timber.log.Timber

// todo theme
@Suppress("MemberVisibilityCanBePrivate", "unused", "LeakingThis")
open class ComposableActivity : ComponentActivity() {

    @Suppress("PropertyName")
    val ANR_TIMEOUT = 5000

    val activityResultListeners = mutableListOf<ActivityResultHandler<*>>()

    open val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        screen(route = EmptyScreen())
    }

    open val backgroundColor = Color(0xff202020)
    open val roundCornerSize: Dp = 0.dp
    open val backgroundShape: Shape = RoundedCornerShape(roundCornerSize)

    fun reportANR() {
        ANRWatchDog(ANR_TIMEOUT).setANRListener { anr ->
            anr.printStackTrace()
        }.start()
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
        onIntent(navController, intent)
    }

    open fun onIntent(navController: NavHostControllerEx, intent: Intent?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Activity ${this::class.simpleName} created.")
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