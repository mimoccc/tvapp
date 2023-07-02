/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import org.mjdev.tvapp.base.extensions.ComposeExt.isFocused
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState

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

    @SuppressLint("ComposableModifierFactory")
    @Composable
    // todo focus problem, requester not registered
    fun Modifier.touchable(
        focusState: MutableState<FocusState?> = rememberFocusState(),
        focusRequester: FocusRequester = rememberFocusRequester(),
        onFocus: FocusRequester.() -> Unit = {},
        onClick: FocusRequester.() -> Unit = {},
    ): Modifier = this then focusRequester(
        focusRequester
    ).onFocusChanged { state ->
            focusState.value = state
            if (focusState.isFocused) {
                onFocus(focusRequester)
            }
        }
        .clickable {
            if (focusState.isFocused) {
                onClick(focusRequester)
            } else {
                focusRequester.requestFocus()
            }
        }

}