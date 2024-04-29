/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.base

import android.content.Context
import androidx.annotation.CallSuper
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mjdev.tvapp.data.events.PauseEvent
import org.mjdev.tvapp.data.events.SyncEnded
import org.mjdev.tvapp.data.events.SyncStarted
import org.mjdev.tvapp.sync.SyncAdapter
import org.mjdev.tvlib.events.observe.observeEvent
import org.mjdev.tvlib.events.post.postEvent

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class Syncer(
    val adapter: SyncAdapter,
    val syncerName: String = Syncer::class.java.simpleName,
    val pauseState: MutableState<Boolean> = mutableStateOf(false)
) {
    val context: Context get() = adapter.context
    val dao get() = adapter.dao
    val apiService get() = adapter.apiService
    val movieDao get() = adapter.movieDao

    private var pauseJob: Job? = null

    private fun pause(
        coroutineScope: CoroutineScope,
        timeout: Long
    ) = with(coroutineScope) {
        pauseJob?.cancel()
        pauseJob = launch {
            pauseState.value = true
            delay(timeout)
            pauseState.value = false
            pauseJob = null
        }
    }

    @CallSuper
    open suspend fun onSyncStart() {
        postEvent(SyncStarted(syncerName))
    }

    @CallSuper
    suspend fun sync(coroutineScope: CoroutineScope) {
        with(coroutineScope) {
            while(pauseState.value)
                delay(500)
            onSyncStart()
            observeEvent<PauseEvent> { event ->
                pause(coroutineScope, event.timeout)
            }
            doSync()
        }
    }

    open suspend fun doSync() {
    }

    open suspend fun onSyncFinish() {
        postEvent(SyncEnded(syncerName))
    }

}