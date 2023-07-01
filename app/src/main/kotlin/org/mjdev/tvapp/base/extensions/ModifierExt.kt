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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import timber.log.Timber

object ModifierExt {

    @Composable
    fun rememberMutableInteractionSource() = remember { MutableInteractionSource() }

    @Composable
    fun rememberFocusState(
        initial: FocusState? = null
    ) = remember {
        mutableStateOf(initial)
    }

    @Composable
    fun rememberFocusRequester() = remember { FocusRequester() }

    val MutableState<FocusState?>.isFocused
        get() = (value?.isFocused == true) || (value?.hasFocus == true)

    @SuppressLint("ComposableModifierFactory")
    @Composable
    // todo focus problem, requester not registered
    fun Modifier.touchable(
        state: MutableState<FocusState?> = rememberFocusState(),
        focusRequester: FocusRequester = rememberFocusRequester(),
        onFocus: () -> Unit = {},
        onClick: () -> Unit = {},
    ): Modifier = focusRequester(
        focusRequester
    ).pointerInput(Unit) {
        detectTapGestures {
            if (state.isFocused) {
                onClick()
            } else {
                try {
                    focusRequester.requestFocus()
                    onFocus()
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }.onFocusChanged { fstate ->
        state.value = fstate
        onFocus()
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