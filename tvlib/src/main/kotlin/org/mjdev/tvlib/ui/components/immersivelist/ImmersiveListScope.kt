/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.immersivelist

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.tv.material3.ExperimentalTvMaterial3Api

@Immutable
@ExperimentalTvMaterial3Api
class ImmersiveListScope internal constructor(
    private val onFocused: (Int) -> Unit
) {

    fun Modifier.immersiveListItem(index: Int): Modifier {
        return this then onFocusChanged {
            if (it.isFocused) {
                onFocused(index)
            }
        }
    }

}