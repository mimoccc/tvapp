/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.ModifierExt.tvAspectRatio

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
    val isEdit = isEditMode()
    val scrollDelta = remember { mutableFloatStateOf(0f) }
    Box(
        modifier = modifier
            .conditional(isEdit) {
                defaultMinSize(80.dp)
                    .tvAspectRatio(16f / 9f).defaultMinSize(80.dp)
            }
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    scrollDelta.value = dragAmount.y
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