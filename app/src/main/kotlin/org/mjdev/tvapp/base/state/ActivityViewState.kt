/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * Activity view state.
 * Used to keep states and change in activity.
 *
 * @param title
 * @param error
 * @constructor Create [ActivityViewState]
 */
@Suppress( "unused")
class ActivityViewState(
    title: Any? = null,
    error: Throwable? = null
) {

    val errorState: MutableState<Throwable?> = mutableStateOf(error)
    val titleState: MutableState<Any?> = mutableStateOf(title)

    /**
     * Clear error.
     * */
    fun clearError() {
        errorState.value = null
    }

    /**
     * Set Error.
     *
     * @param message Message of an error
     */
    fun error(message: String) {
        errorState.value = Exception(message)
    }

    /**
     * Set Error.
     *
     * @param e E Throwable to set error for
     */
    fun error(e: Throwable?) {
        errorState.value = Exception(e)
    }

    /**
     * Set info message.
     *
     * @param message Message
     */
    fun info(message: String) {
        errorState.value = Exception(message)
    }

    /**
     * Set info from throwable.
     *
     * @param e E info throwable
     */
    fun info(e: Throwable) {
        errorState.value = Exception(e)
    }

    /**
     * Set title.
     *
     * @param title Title
     */
    fun setTitle(title: Any?) {
        titleState.value = title
    }

    companion object {

        /**
         * Remember activity view state.
         *
         * Composable helper
         *
         * @param title Title
         * @param error Error
         * @return [ActivityViewState]
         */
        @Composable
        fun rememberActivityViewState(
            title: Any? = null,
            error: Throwable? = null
        ): ActivityViewState = remember { ActivityViewState(title, error) }

    }

}