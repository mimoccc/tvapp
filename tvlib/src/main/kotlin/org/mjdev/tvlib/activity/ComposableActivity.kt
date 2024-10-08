/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import com.github.anrwatchdog.ANRWatchDog
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.swipeGestures
import org.mjdev.tvlib.extensions.NavExt.navControllerEx
import org.mjdev.tvlib.extensions.NavGraphBuilderExt.screen
import org.mjdev.tvlib.navigation.NavGraphBuilderEx
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.navigation.NavHostEx
import org.mjdev.tvlib.ui.components.screen.EmptyScreen
import org.mjdev.tvlib.ui.theme.TVAppTheme
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class ComposableActivity : ComponentActivity() {

    @Suppress("PropertyName")
    val ANR_TIMEOUT = 2000

    val activityResultListeners = mutableListOf<ActivityResultHandler<*>>()

    open val navGraphBuilder: NavGraphBuilderEx.() -> Unit = {
        screen(route = EmptyScreen())
    }

    val navController: NavHostControllerEx by lazy {
        navControllerEx(this)
    }

    var lastIntent: Intent? = null

    val anrWatchDog: ANRWatchDog by lazy {
        ANRWatchDog(ANR_TIMEOUT)
            .setIgnoreDebugger(true)
            .setANRListener { anr ->
                anr.printStackTrace()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Activity ${this::class.simpleName} created.")
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            setDecorFitsSystemWindows(window, false)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        } else {
            setDecorFitsSystemWindows(window, true)
        }
        if (BuildConfig.DEBUG) {
            anrWatchDog.start()
        }
        setContent {
            Compose()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if ((lastIntent == null) || (lastIntent != intent)) {
            lastIntent = intent
            onIntent(navController, intent)
        }
    }

    @CallSuper
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultListeners.forEach { listener ->
            try {
                listener.postResult(requestCode, resultCode, data)
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityResultListeners.clear()
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

    open fun onIntent(navController: NavHostControllerEx, intent: Intent?) {
    }

    @Previews
    @Composable
    @CallSuper
    open fun Compose() {
        TVAppTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .swipeGestures(
                        onSwipeRight = {
                            navController.openMenu()
                        },
                        onSwipeLeft = {
                            navController.closeMenu()
                        }
                    ),
                shape = RectangleShape,
                colors = SurfaceDefaults.colors(
                    containerColor = Color.Companion.Transparent
                    // MaterialTheme.colorScheme.background
                )
            ) {
                NavHostEx(
                    modifier = Modifier.fillMaxSize(),
                    builder = navGraphBuilder,
                )
            }
        }
        if (intent != null) {
            if ((lastIntent == null) || (lastIntent != intent)) {
                lastIntent = intent
                onIntent(navController, intent)
            }
        }
    }
}