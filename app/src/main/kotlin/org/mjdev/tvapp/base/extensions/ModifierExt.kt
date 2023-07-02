/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate")
object ModifierExt {

    fun Modifier.conditional(
        condition: Boolean,
        other: Modifier.() -> Modifier
    ): Modifier {
        return when (condition) {
            true -> this.then(other.invoke(this))
            else -> this
        }
    }

    fun Modifier.tvAspectRatio(
        ratio: Float?,
        matchHeightConstraintsFirst: Boolean = false
    ): Modifier = conditional(ratio != null) {
        aspectRatio(
            ratio!!,
            matchHeightConstraintsFirst
        )
    }

    fun Modifier.focusState(
        focusState: MutableState<FocusState?>
    ): Modifier = onFocusChanged { state ->
        focusState.value = state
    }

    fun Modifier.requestFocusOnTouch(
        focusRequester: FocusRequester,
        onTouch: () -> Unit = {}
    ): Modifier = this then focusRequester(
        focusRequester
    ).pointerInput(this) {
        detectTapGestures {
            try {
                onTouch()
                focusRequester.requestFocus()
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

}