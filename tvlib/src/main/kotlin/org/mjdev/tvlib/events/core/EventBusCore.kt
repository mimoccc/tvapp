package org.mjdev.tvlib.events.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.mjdev.tvlib.events.EventBusInitializer
import org.mjdev.tvlib.events.util.launchWhenStateAtLeast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.logging.Level
import kotlin.collections.set

@Suppress("UNCHECKED_CAST")
class EventBusCore : ViewModel() {

    private val eventFlows: HashMap<String, MutableSharedFlow<Any>> = HashMap()
    private val stickyEventFlows: HashMap<String, MutableSharedFlow<Any>> = HashMap()

    private fun getEventFlow(
        eventName: String,
        isSticky: Boolean
    ): MutableSharedFlow<Any> {
        return if (isSticky) {
            stickyEventFlows[eventName]
        } else {
            eventFlows[eventName]
        } ?: MutableSharedFlow<Any>(
            replay = if (isSticky) 1 else 0,
            extraBufferCapacity = Int.MAX_VALUE
        ).also {
            if (isSticky) {
                stickyEventFlows[eventName] = it
            } else {
                eventFlows[eventName] = it
            }
        }
    }

    fun <T : Any> observeEvent(
        lifecycleOwner: LifecycleOwner,
        eventName: String,
        minState: Lifecycle.State,
        dispatcher: CoroutineDispatcher,
        isSticky: Boolean,
        onReceived: (T) -> Unit
    ): Job {
        EventBusInitializer.logger?.log(Level.WARNING, "observe Event:$eventName")
        return lifecycleOwner.launchWhenStateAtLeast(minState) {
            getEventFlow(eventName, isSticky).collect { value ->
                this.launch(dispatcher) {
                    invokeReceived(value, onReceived)
                }
            }
        }
    }

    suspend fun <T : Any> observeWithoutLifecycle(
        eventName: String,
        isSticky: Boolean,
        onReceived: (T) -> Unit
    ) {
        getEventFlow(eventName, isSticky).collect { value ->
            invokeReceived(value, onReceived)
        }
    }

    fun postEvent(
        eventName: String,
        value: Any,
        timeMillis: Long
    ) {
        EventBusInitializer.logger?.log(Level.WARNING, "post Event:$eventName")
        listOfNotNull(
            getEventFlow(eventName, false),
            getEventFlow(eventName, true)
        ).forEach { flow ->
            viewModelScope.launch {
                delay(timeMillis)
                flow.emit(value)
            }
        }
    }

    fun removeStickEvent(
        eventName: String
    ) {
        stickyEventFlows.remove(eventName)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun clearStickEvent(
        eventName: String
    ) {
        stickyEventFlows[eventName]?.resetReplayCache()
    }

    private fun <T : Any> invokeReceived(
        value: Any,
        onReceived: (T) -> Unit
    ) {
        try {
            onReceived.invoke(value as T)
        } catch (e: ClassCastException) {
            EventBusInitializer.logger?.log(
                Level.WARNING,
                "class cast error on message received: $value",
                e
            )
        } catch (e: Exception) {
            EventBusInitializer.logger?.log(
                Level.WARNING,
                "error on message received: $value",
                e
            )
        }
    }

    fun getEventObserverCount(
        eventName: String
    ): Int {
        val stickyObserverCount = stickyEventFlows[eventName]?.subscriptionCount?.value ?: 0
        val normalObserverCount = eventFlows[eventName]?.subscriptionCount?.value ?: 0
        return stickyObserverCount + normalObserverCount
    }

}