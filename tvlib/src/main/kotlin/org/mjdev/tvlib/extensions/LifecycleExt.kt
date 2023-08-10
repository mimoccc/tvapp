/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

object LifecycleExt {

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

}