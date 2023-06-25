/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    private val error: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    private var errorHandler: (error: Throwable) -> Unit = {}

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

}