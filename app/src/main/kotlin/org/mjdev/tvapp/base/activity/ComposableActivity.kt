/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.activity

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
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ContextExt.isATv
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.ui.components.complex.ScreenView
import org.mjdev.tvapp.base.ui.components.complex.EmptyScreen
import org.mjdev.tvapp.base.ui.components.navigation.NavGraphBuilderEx
import org.mjdev.tvapp.base.ui.components.complex.NavHostEx
import org.mjdev.tvapp.base.ui.components.navigation.Screen.Companion.screen
import org.mjdev.tvapp.ui.theme.TVAppTheme
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "unused", "LeakingThis")
open class ComposableActivity : ComponentActivity() {

    val activityResultListeners = mutableListOf<ActivityResultHandler<*>>()

    open val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        screen(route = EmptyScreen())
    }

    open val backgroundColor = Color(0xff202020)
    open val roundCornerSize: Dp = 16.dp
    open val backgroundShape: Shape = RoundedCornerShape(roundCornerSize)

    @TvPreview
    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    @CallSuper
    open fun Compose() {

        val navController = rememberNavControllerEx()

        TVAppTheme {
            Surface(
                modifier = Modifier
                    .background(backgroundColor, backgroundShape)
                    .fillMaxSize(),
                shape = RectangleShape,
                colors = NonInteractiveSurfaceDefaults.colors(
                    containerColor = backgroundColor
                )
            ) {
                ScreenView(
                    navController = navController,
                    backgroundColor = backgroundColor
                ) {
                    NavHostEx(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        builder = navGraphBuilder
                    )
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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