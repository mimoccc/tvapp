/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import timber.log.Timber

@Suppress("unused", "StaticFieldLeak")
open class BaseViewModel(
    context: Context
) : AndroidViewModel(context.applicationContext as Application), DIAware {

    override val di
        get() = (getApplication() as DIAware).di

    val error: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    private var errorHandler: (error: Throwable) -> Unit = { e -> Timber.e(e) }

    init {
        error.onEach { error ->
            if (error != null) errorHandler.invoke(error)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun postError(e: Throwable) {
        Timber.e(e)
        error.tryEmit(e)
    }

    @Suppress("UNCHECKED_CAST", "unused")
    protected suspend fun <T> runSafe(
        block: suspend () -> T
    ): T = try {
        block.invoke()
    } catch (t: Throwable) {
        postError(t)
    } as T

    fun handleError(block: (error: Throwable) -> Unit) {
        errorHandler = block
    }

    fun <K, V> Flow<Map<K, V>>.stateInViewModel(
        initial: Map<K, V> = mapOf()
    ): StateFlow<Map<K, V>> = stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        initial
    )

    fun <T> Flow<List<T>>.stateInViewModel(
        initial: List<T> = listOf()
    ): StateFlow<List<T>> = stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        initial
    )

    fun <T> Flow<T>.stateInViewModel(
        initial: T
    ): StateFlow<T> = stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        initial
    )

    fun <T> async(
        block: suspend CoroutineScope.() -> T
    ): MutableLiveData<T> {
        val result = MutableLiveData<T>()
        viewModelScope.launch {
            block.invoke(this).let { data ->
                result.postValue(data)
            }
        }
        return result
    }

}