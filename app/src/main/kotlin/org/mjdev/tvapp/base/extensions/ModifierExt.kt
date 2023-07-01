/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import org.mjdev.tvapp.base.extensions.ComposeExt.isFocused
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import timber.log.Timber

object ModifierExt {

    fun Modifier.conditional(
        condition: Boolean,
        other: Modifier.() -> Modifier
    ): Modifier {
        return when(condition) {
            true -> this.then(other.invoke(this))
            else -> this
        }
    }

    @SuppressLint("ComposableModifierFactory")
    @Composable
    // todo focus problem, requester not registered
    fun Modifier.touchable(
        state: MutableState<FocusState?> = rememberFocusState(),
        focusRequester: FocusRequester = rememberFocusRequester(),
        onFocus: FocusRequester.() -> Unit = {},
        onClick: FocusRequester.() -> Unit = {},
    ): Modifier = focusRequester(
        focusRequester
    ).pointerInput(Unit) {
        detectTapGestures {
            if (state.isFocused) {
                onClick(focusRequester)
            } else {
                try {
                    focusRequester.requestFocus()
                    onFocus(focusRequester)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }.onFocusChanged { fstate ->
        state.value = fstate
        onFocus(focusRequester)
    }.apply {
        if (state.isFocused) {
            try {
                focusRequester.requestFocus()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}