/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.immersivelist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.bringIntoViewIfChildrenAreFocused

@Previews
@SuppressLint("ModifierParameter")
@Suppress("IllegalExperimentalApiUsage")
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalTvMaterial3Api
@Composable
fun ImmersiveList(
    background: @Composable ImmersiveListBackgroundScope.(
        index: Int,
        listHasFocus: Boolean
    ) -> Unit = { _, _ -> },
     modifier: Modifier = Modifier,
    listAlignment: Alignment = Alignment.BottomEnd,
    currentItemIndex: MutableIntState = remember { mutableIntStateOf(0) },
    listHasFocus: MutableState<Boolean> = remember { mutableStateOf(false) },
    list: @Composable ImmersiveListScope.() -> Unit = {},
) {
    Box(
        modifier.bringIntoViewIfChildrenAreFocused()
    ) {
        ImmersiveListBackgroundScope(
            this
        ).background(
            currentItemIndex.intValue,
            listHasFocus.value
        )
        val focusManager = LocalFocusManager.current
        Box(
            Modifier
                .align(listAlignment)
                .onFocusChanged { focusState ->
                    listHasFocus.value = focusState.hasFocus
                }
        ) {
            ImmersiveListScope { idx ->
                currentItemIndex.intValue = idx
                focusManager.moveFocus(FocusDirection.Enter)
            }.list()
        }
    }
}