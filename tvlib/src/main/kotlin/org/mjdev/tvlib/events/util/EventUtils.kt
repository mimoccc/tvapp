@file:Suppress("unused")

package org.mjdev.tvlib.events.util

import androidx.lifecycle.*
import org.mjdev.tvlib.events.core.EventBusCore
import org.mjdev.tvlib.events.store.ApplicationScopeViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

inline fun <reified T> getEventObserverCount(
    event: Class<T>
): Int = ApplicationScopeViewModelProvider
    .getApplicationScopeViewModel(EventBusCore::class.java)
    .getEventObserverCount(event.name)

inline fun <reified T> getEventObserverCount(
    scope: ViewModelStoreOwner,
    event: Class<T>
): Int = ViewModelProvider(scope)[EventBusCore::class.java]
        .getEventObserverCount(event.name)


inline fun <reified T> removeStickyEvent(
    event: Class<T>
) {
    ApplicationScopeViewModelProvider
        .getApplicationScopeViewModel(EventBusCore::class.java)
        .removeStickEvent(event.name)
}

inline fun <reified T> removeStickyEvent(
    scope: ViewModelStoreOwner,
    event: Class<T>
) {
    ViewModelProvider(scope)[EventBusCore::class.java]
        .removeStickEvent(event.name)
}


inline fun <reified T> clearStickyEvent(
    event: Class<T>
) {
    ApplicationScopeViewModelProvider.getApplicationScopeViewModel(EventBusCore::class.java)
        .clearStickEvent(event.name)
}

inline fun <reified T> clearStickyEvent(
    scope: ViewModelStoreOwner,
    event: Class<T>
) {
    ViewModelProvider(scope)[EventBusCore::class.java]
        .clearStickEvent(event.name)
}


fun <T> LifecycleOwner.launchWhenStateAtLeast(
    minState: Lifecycle.State,
    block: suspend CoroutineScope.() -> T
): Job {
    return lifecycleScope.launch {
        @Suppress("DEPRECATION")
        lifecycle.whenStateAtLeast(minState, block)
    }
}
