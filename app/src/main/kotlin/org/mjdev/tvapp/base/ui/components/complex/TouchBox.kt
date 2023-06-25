/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
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

@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun TouchBox(
        modifier: Modifier = Modifier,
        contentAlignment: Alignment = Alignment.TopStart,
        propagateMinConstraints: Boolean = false,
        state: TvLazyListState? = null,
        content: @Composable BoxScope.() -> Unit
) {
    val scrollDelta = remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures { _, dragAmount ->
                scrollDelta.value = dragAmount.y
            }
        },
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {

        content.invoke(this)

    }

    LaunchedEffect(scrollDelta.value) {
        state?.dispatchRawDelta(-scrollDelta.value)
    }

}