/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Suppress("unused")
object LifecycleExt {

    @SuppressLint("ComposableNaming")
    @Composable
    fun rememberLifeCycleEventObserver(
        key1: Any? = Unit,
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        lifecycle: Lifecycle = lifecycleOwner.lifecycle,
        onEvent: (source: LifecycleOwner, event: Lifecycle.Event) -> Unit
    ) = DisposableEffect(key1) {
        val observer = LifecycleEventObserver { owner, event ->
            onEvent(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun rememberPlayPauseLifeCycleObserver(
        key1: Any? = Unit,
        onPause: (() -> Unit)? = null,
        onResume:( () -> Unit)? = null
    ) = rememberLifeCycleEventObserver(key1) { _, event ->
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {
                onPause?.invoke()
            }

            Lifecycle.Event.ON_RESUME -> {
                onResume?.invoke()
            }

            else -> {
            }
        }
    }

}
