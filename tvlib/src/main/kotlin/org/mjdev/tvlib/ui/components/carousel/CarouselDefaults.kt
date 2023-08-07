/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import android.view.accessibility.AccessibilityManager
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.first

@Suppress("unused")
object CarouselDefaults {

    const val TimeToDisplayItemMillis: Long = 5000

    val contentTransform: ContentTransform
        @Composable get() = fadeIn(animationSpec = tween(100))
            .togetherWith(fadeOut(animationSpec = tween(100)))

    @Composable
    fun IndicatorRow(
        itemCount: Int,
        activeItemIndex: Int,
        modifier: Modifier = Modifier,
        spacing: Dp = 8.dp,
        indicator: @Composable (isActive: Boolean) -> Unit = { isActive ->
            val activeColor = Color.White
            val inactiveColor = activeColor.copy(alpha = 0.3f)
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (isActive) activeColor else inactiveColor,
                        shape = CircleShape,
                    ),
            )
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            repeat(itemCount) {
                val isActive = it == activeItemIndex
                indicator(isActive)
            }
        }
    }

    @Composable
    fun CarouselStateUpdater(carouselState: CarouselState, itemCount: Int) {
        LaunchedEffect(carouselState, itemCount) {
            if (itemCount != 0) {
                carouselState.activeItemIndex = Math.floorMod(carouselState.activeItemIndex, itemCount)
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Suppress("IllegalExperimentalApiUsage")
    suspend fun AnimatedVisibilityScope.onAnimationCompletion(
        action: suspend () -> Unit
    ) {
        snapshotFlow { transition.currentState == transition.targetState }.first { it }
        action.invoke()
    }

    fun shouldPerformAutoScroll(
        focusState: FocusState?,
        accessibilityManager: AccessibilityManager
    ): Boolean {
        val carouselIsFocused = focusState?.isFocused ?: false
        val carouselHasFocus = focusState?.hasFocus ?: false
        return !accessibilityManager.isEnabled && !(carouselIsFocused || carouselHasFocus)
    }

    interface ScrollPauseHandle {
        fun resumeAutoScroll()
    }

    object NoOpScrollPauseHandle : ScrollPauseHandle {
        override fun resumeAutoScroll() {}
    }

    class ScrollPauseHandleImpl(private val carouselState: CarouselState) : ScrollPauseHandle {
        private var active by mutableStateOf(true)

        init {
            carouselState.activePauseHandlesCount += 1
        }

        override fun resumeAutoScroll() {
            if (active) {
                active = false
                carouselState.activePauseHandlesCount -= 1
            }
        }
    }

    object KeyEventPropagation {
        const val StopPropagation = true
        const val ContinuePropagation = false
    }

}