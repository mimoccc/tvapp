/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusDirection.Companion.Left
import androidx.compose.ui.focus.FocusDirection.Companion.Right
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType.Companion.KeyUp
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.ScrollAxisRange
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.horizontalScrollAxisRange
import androidx.compose.ui.semantics.scrollBy
import androidx.compose.ui.semantics.semantics

object ModifierExt {

    @Composable
    fun Modifier.carouselSemantics(
        itemCount: Int,
        state: CarouselState
    ): Modifier {
        return this.then(
            remember(
                state,
                itemCount
            ) {
                val accessibilityScrollState = ScrollAxisRange(
                    value = {
                        state.activeItemIndex.toFloat()
                    },
                    maxValue = {
                        (itemCount - 1).toFloat()
                    },
                    reverseScrolling = false
                )
                val scrollByAction: ((x: Float, y: Float) -> Boolean) =
                    { x, _ ->
                        when {
                            x > 0f -> state.moveToNextItem(itemCount)
                            x < 0f -> state.moveToPreviousItem(itemCount)
                        }
                        x != 0f
                    }
                Modifier.semantics {
                    horizontalScrollAxisRange = accessibilityScrollState
                    scrollBy(action = scrollByAction)
                    collectionInfo = CollectionInfo(rowCount = 1, columnCount = itemCount)
                }
            }
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Suppress("IllegalExperimentalApiUsage")
    fun Modifier.handleKeyEvents(
        carouselState: CarouselState,
        outerBoxFocusRequester: FocusRequester,
        focusManager: FocusManager,
        itemCount: Int,
        isLtr: Boolean,
        currentCarouselBoxFocusState: () -> FocusState?
    ): Modifier = onKeyEvent {

        fun showPreviousItem() {
            carouselState.moveToPreviousItem(itemCount)
            outerBoxFocusRequester.requestFocus()
        }

        fun showNextItem() {
            carouselState.moveToNextItem(itemCount)
            outerBoxFocusRequester.requestFocus()
        }

        fun updateItemBasedOnLayout(direction: FocusDirection, isLtr: Boolean) {
            when (direction) {
                Left -> if (isLtr) showPreviousItem() else showNextItem()
                Right -> if (isLtr) showNextItem() else showPreviousItem()
            }
        }

        fun handledHorizontalFocusMove(direction: FocusDirection): Boolean = when {
            it.nativeKeyEvent.repeatCount > 0 -> CarouselDefaults.KeyEventPropagation.StopPropagation
            currentCarouselBoxFocusState()?.isFocused == true ->
                if (shouldFocusExitCarousel(direction, carouselState, itemCount, isLtr)) {
                    CarouselDefaults.KeyEventPropagation.ContinuePropagation
                } else {
                    updateItemBasedOnLayout(direction, isLtr)
                    CarouselDefaults.KeyEventPropagation.StopPropagation
                }

            !focusManager.moveFocus(direction) -> {
                updateItemBasedOnLayout(direction, isLtr)
                CarouselDefaults.KeyEventPropagation.StopPropagation
            }

            else -> CarouselDefaults.KeyEventPropagation.StopPropagation
        }
        when {
            it.type == KeyUp -> CarouselDefaults.KeyEventPropagation.ContinuePropagation
            it.key == Key.Back -> {
                focusManager.moveFocus(FocusDirection.Exit)
                CarouselDefaults.KeyEventPropagation.ContinuePropagation
            }

            it.key == Key.DirectionLeft -> handledHorizontalFocusMove(Left)
            it.key == Key.DirectionRight -> handledHorizontalFocusMove(Right)
            else -> CarouselDefaults.KeyEventPropagation.ContinuePropagation
        }
    }.focusProperties {
        exit = {
            when {
                shouldFocusExitCarousel(
                    it,
                    carouselState,
                    itemCount,
                    isLtr
                ) -> FocusRequester.Default

                else -> FocusRequester.Cancel
            }
        }
    }

    private fun shouldFocusExitCarousel(
        focusDirection: FocusDirection,
        carouselState: CarouselState,
        itemCount: Int,
        isLtr: Boolean
    ): Boolean = when {
        focusDirection == Left && isLtr && !carouselState.isFirstItem() -> false
        focusDirection == Left && !isLtr && !carouselState.isLastItem(itemCount) -> false
        focusDirection == Right && isLtr && !carouselState.isLastItem(itemCount) -> false
        focusDirection == Right && !isLtr && !carouselState.isFirstItem() -> false
        else -> true
    }

}