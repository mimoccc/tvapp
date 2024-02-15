/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.events.post

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.mjdev.tvlib.events.core.EventBusCore
import org.mjdev.tvlib.events.store.ApplicationScopeViewModelProvider

inline fun <reified T> postEvent(
    event: T,
    timeMillis: Long = 0L
) {
    ApplicationScopeViewModelProvider
        .getApplicationScopeViewModel(EventBusCore::class.java)
        .postEvent(
            T::class.java.name,
            event!!,
            timeMillis
        )
}

inline fun <reified T> postEvent(
    scope: ViewModelStoreOwner,
    event: T,
    timeMillis: Long = 0L
) {
    ViewModelProvider(scope)[EventBusCore::class.java].postEvent(
        T::class.java.name,
        event!!,
        timeMillis
    )
}
