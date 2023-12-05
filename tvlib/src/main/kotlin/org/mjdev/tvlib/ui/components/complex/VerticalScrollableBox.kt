/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.tv.foundation.lazy.list.TvLazyListState
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun VerticalScrollableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = isLandscapeMode(),
    state: TvLazyListState? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    scrollDelta.value = dragAmount
                }
            }
            .recomposeHighlighter(),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        content.invoke(this)
    }
    LaunchedEffect(scrollDelta.value) {
        state?.dispatchRawDelta(-scrollDelta.value)
    }
}