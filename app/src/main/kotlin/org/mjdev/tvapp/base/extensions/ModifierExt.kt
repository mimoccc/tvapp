/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import timber.log.Timber

object ModifierExt {

    fun Modifier.touchable(
        onTouch: FocusRequester.() -> Unit = {}
    ): Modifier = composed {
        val focusRequester = remember { FocusRequester() }
        this
            .focusRequester(focusRequester)
            .pointerInput(Unit) {
                detectTapGestures {
                    try {
                        onTouch(focusRequester)
                        focusRequester.requestFocus()
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
    }

}