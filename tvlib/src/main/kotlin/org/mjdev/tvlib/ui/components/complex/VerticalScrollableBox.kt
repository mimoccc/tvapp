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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode

@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun VerticalScrollableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = isLandscapeMode(),
    state: TvLazyListState? = rememberTvLazyListState(),
    content: @Composable BoxScope.() -> Unit = {}
) = Box(
    modifier = modifier
        .pointerInput(Unit) {
            detectVerticalDragGestures { _, dragAmount ->
                state?.dispatchRawDelta(-dragAmount)
            }
        },
    contentAlignment = contentAlignment,
    propagateMinConstraints = propagateMinConstraints,
) {
    content.invoke(this)
}