/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.activity

import android.app.WallpaperManager
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import org.mjdev.tvlib.annotations.Previews
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "PreviewShouldNotBeCalledRecursively")
open class TvActivity : ComposableActivity(), View.OnApplyWindowInsetsListener {

    open val backgroundColor: Color? = null

    open var background: Drawable? = null
        get() = field ?: backgroundColor?.let { ColorDrawable(it.toArgb()) }

    var systemUIVisible = true

    val decorView
        get() = window.decorView

    val windowInsetsController
        get() = WindowCompat.getInsetsController(window, decorView)

    var systemBarsBehavior
        get() = windowInsetsController.systemBarsBehavior
        set(value) {
            windowInsetsController.systemBarsBehavior = value
        }

    val configuration
        get() = resources?.configuration

    val isLandscape
        get() = (configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) ||
                ((configuration?.screenWidthDp ?: 0) > (configuration?.screenHeightDp ?: 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            )
        )
        decorView.setOnApplyWindowInsetsListener(this)
        if (background == null) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(this)
                val wallpaperDrawable = wallpaperManager.drawable
                decorView.background = wallpaperDrawable
            } catch (e: Exception) {
                Timber.e(e)
            }
        } else {
            decorView.background = background
        }
        with(window.attributes) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
                } else {
                    LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        decorView.setOnApplyWindowInsetsListener(null)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        decorView.requestApplyInsets()
    }

    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        WindowCompat.setDecorFitsSystemWindows(window, !isLandscape)
        if (isLandscape) {
            systemUIVisible = false
            if (systemBarsBehavior != BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE) {
                systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsetsCompat.Type.navigationBars())) {
                    windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
                }
                if (insets.isVisible(WindowInsetsCompat.Type.statusBars())) {
                    windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    if (insets.isVisible(WindowInsetsCompat.Type.displayCutout())) {
                        windowInsetsController.hide(WindowInsetsCompat.Type.displayCutout())
                    }
                }
            } else {
                windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
                windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    windowInsetsController.hide(WindowInsetsCompat.Type.displayCutout())
                }
            }
            v.onApplyWindowInsets(insets)
        } else {
            systemUIVisible = true
            if (systemBarsBehavior != BEHAVIOR_DEFAULT) {
                systemBarsBehavior = BEHAVIOR_DEFAULT
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (!insets.isVisible(WindowInsetsCompat.Type.navigationBars())) {
                    windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
                }
                if (!insets.isVisible(WindowInsetsCompat.Type.statusBars())) {
                    windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    if (!insets.isVisible(WindowInsetsCompat.Type.displayCutout())) {
                        windowInsetsController.show(WindowInsetsCompat.Type.displayCutout())
                    }
                }
            } else {
                windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
                windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    windowInsetsController.show(WindowInsetsCompat.Type.displayCutout())
                }
            }
            v.onApplyWindowInsets(insets)
        }
        return insets
    }

    @Previews
    @Composable
    override fun Compose() = super.Compose()

}
