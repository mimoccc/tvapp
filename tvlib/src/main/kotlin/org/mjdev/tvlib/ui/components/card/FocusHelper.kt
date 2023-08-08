/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.card

import androidx.compose.ui.focus.FocusState

class FocusHelper(
    private var focused: Boolean
) : FocusState {
    override val hasFocus: Boolean get() = focused
    override val isCaptured: Boolean get() = focused
    override val isFocused: Boolean get() = focused
}