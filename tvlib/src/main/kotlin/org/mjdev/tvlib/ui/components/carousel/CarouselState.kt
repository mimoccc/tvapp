/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import java.lang.Math.floorMod

@Suppress("unused")
@Stable
class CarouselState(
    initialActiveItemIndex: Int = 0,
) {

    var activePauseHandlesCount by mutableIntStateOf(0)
    var activeItemIndex by mutableIntStateOf(initialActiveItemIndex)
    var isMovingBackward = false

    fun pauseAutoScroll(itemIndex: Int): CarouselDefaults.ScrollPauseHandle {
        if (this.activeItemIndex != itemIndex) {
            return CarouselDefaults.NoOpScrollPauseHandle
        }
        return CarouselDefaults.ScrollPauseHandleImpl(this)
    }

    fun isFirstItem() = activeItemIndex == 0

    fun isLastItem(itemCount: Int) = activeItemIndex == itemCount - 1

    fun moveToPreviousItem(itemCount: Int) {
        if (itemCount == 0) return
        isMovingBackward = true
        activeItemIndex = floorMod(activeItemIndex - 1, itemCount)
    }

    fun moveToNextItem(itemCount: Int) {
        if (itemCount == 0) return
        isMovingBackward = false
        activeItemIndex = floorMod(activeItemIndex + 1, itemCount)
    }

}