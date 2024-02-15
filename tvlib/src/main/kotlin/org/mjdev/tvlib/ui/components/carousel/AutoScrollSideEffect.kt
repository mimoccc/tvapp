/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.yield

@Composable
fun AutoScrollSideEffect(
    autoScrollDurationMillis: Long,
    itemCount: Int,
    carouselState: CarouselState,
    doAutoScroll: Boolean,
    onAutoScrollChange: ((isAutoScrollActive: Boolean) -> Unit)? = null,
) {
    if (autoScrollDurationMillis == Long.MAX_VALUE || autoScrollDurationMillis < 0) {
        return
    }
    val updatedItemCount by rememberUpdatedState(newValue = itemCount)
    if (doAutoScroll) {
        LaunchedEffect(carouselState) {
            while (true) {
                yield()
                delay(autoScrollDurationMillis)
                if (carouselState.activePauseHandlesCount > 0) {
                    snapshotFlow {
                        carouselState.activePauseHandlesCount
                    }.first { pauseHandleCount ->
                        pauseHandleCount == 0
                    }
                }
                carouselState.moveToNextItem(updatedItemCount)
            }
        }
    }
    onAutoScrollChange?.invoke(doAutoScroll)
}