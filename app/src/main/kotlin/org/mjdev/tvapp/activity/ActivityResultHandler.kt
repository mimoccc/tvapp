/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.activity

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.mjdev.tvapp.base.activity.ComposableActivity

/**
 * Activity result handler.
 *
 * @param I
 * @constructor Create [ActivityResultHandler]
 * @property lifecycle lifecycle handler
 * @property onLaunch function to start activity
 * @property onActivityResult result handler
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ActivityResultHandler<I>(
    private val activity: ComposableActivity,
    val lifecycle: Lifecycle,
    val onLaunch: (args: List<I>) -> Unit,
    val onActivityResult: (requestCode: Int, resultCode: Int, intent: Intent?) -> Unit,
) {

    private val observer = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                unregister()
            }

            else -> {}
        }
    }

    init {
        lifecycle.addObserver(observer)
        activity.activityResultListeners.add(this)
    }

    /**
     * Function to register result handler.
     * It is called automatically upon lifecycle state.
     * */
    fun unregister() {
        lifecycle.removeObserver(observer)
        activity.activityResultListeners.remove(this)
    }

    /**
     * Post result to handler.
     *
     * @param requestCode Request code
     * @param resultCode Result code
     * @param intent Intent result data
     */
    fun postResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        onActivityResult(requestCode, resultCode, intent)
    }

    /**
     * Launch the onLaunch handler.
     *
     * @param args Args
     */
    fun launch(vararg args: I) {
        onLaunch(args.toList())
    }

}