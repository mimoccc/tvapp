/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.other

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.view.View
import android.view.WindowInsets.Type.navigationBars
import android.view.WindowInsets.Type.statusBars
import android.view.WindowInsetsController.BEHAVIOR_DEFAULT
import android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.mjdev.tvlib.activity.ComposableActivity

@Suppress("DEPRECATION", "unused", "MemberVisibilityCanBePrivate")
class SystemUIController(
    val activity: ComposableActivity?,
    val onChanges: SystemUIController.() -> Unit
) : ConfigChangeCallback, DefaultLifecycleObserver {

    val window
        get() = runCatching {
            activity?.window
        }.getOrNull()

    var configuration: Configuration? = runCatching {
        activity?.resources?.configuration
    }.getOrNull()

    var systemUIVisible: Boolean = true

    val isLandscape
        get() = (configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) ||
                ((configuration?.screenWidthDp ?: 0) > (configuration?.screenHeightDp ?: 0))


    val isPortrait
        get() = (configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) ||
                ((configuration?.screenHeightDp ?: 0) > (configuration?.screenWidthDp ?: 0))

    init {
        activity?.lifecycle?.addObserver(this)
    }

    fun register() {
        activity?.configChangeCallbacks?.also {
            it.remove(this)
            it.add(this)
        }
    }

    private fun unregister() {
        activity?.configChangeCallbacks?.remove(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        register()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        unregister()
        activity?.lifecycle?.removeObserver(this)
    }

    override fun invoke(newConfig: Configuration) {
        this.configuration = newConfig
        onChanges(this)
    }

    @SuppressLint("NewApi")
    fun hideSystemUI() {
        if (systemUIVisible) {
            systemUIVisible = false

            window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            try {
                window?.decorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } catch (t: Throwable) {
                t.printStackTrace()
            }

            try {
                window?.attributes?.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } catch (t: Throwable) {
                t.printStackTrace()
            }

            try {
                window?.setDecorFitsSystemWindows(false)
                window?.insetsController?.apply {
                    hide(statusBars() or navigationBars())
                    systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    @SuppressLint("InlinedApi")
    fun showSystemUI() {
        if (!systemUIVisible) {
            systemUIVisible = true
            window?.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            try {
                window?.decorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_VISIBLE
            } catch (t: Throwable) {
                t.printStackTrace()
            }

            try {
                window?.attributes?.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } catch (t: Throwable) {
                t.printStackTrace()
            }

            try {
                window?.setDecorFitsSystemWindows(true)
                window?.insetsController?.apply {
                    show(statusBars() or navigationBars())
                    systemBarsBehavior = BEHAVIOR_DEFAULT
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

//    companion object {
//
//        @Composable
//        fun rememberSystemUIController(
//            onChanges: SystemUIController.() -> Unit
//        ): SystemUIController {
//            val isEdit = isEditMode()
//            val activity = if (isEdit) null else LocalContext.current as ComposableActivity
//            return remember { SystemUIController(activity, onChanges) }
//        }
//
//    }

}