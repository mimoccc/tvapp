/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import android.annotation.SuppressLint
import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.extensions.ModifierExt.bringIntoViewIfChildrenAreFocused
import org.mjdev.tvlib.ui.components.carousel.CarouselDefaults.CarouselStateUpdater
import org.mjdev.tvlib.ui.components.carousel.CarouselDefaults.onAnimationCompletion
import org.mjdev.tvlib.ui.components.carousel.CarouselDefaults.shouldPerformAutoScroll
import org.mjdev.tvlib.ui.components.carousel.ModifierExt.carouselSemantics
import org.mjdev.tvlib.ui.components.carousel.ModifierExt.handleKeyEvents

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("ServiceCast")
@Suppress("IllegalExperimentalApiUsage")
@Composable
fun Carousel(
    itemCount: Int,
    modifier: Modifier = Modifier,
    carouselState: CarouselState = remember { CarouselState() },
    autoScrollDurationMillis: Long = CarouselDefaults.TimeToDisplayItemMillis,
    contentTransformStartToEnd: ContentTransform = CarouselDefaults.contentTransform,
    contentTransformEndToStart: ContentTransform = CarouselDefaults.contentTransform,
    carouselIndicator: @Composable BoxScope.() -> Unit = {
        CarouselDefaults.IndicatorRow(
            itemCount = itemCount,
            activeItemIndex = carouselState.activeItemIndex,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        )
    },
    content: @Composable AnimatedContentScope.(isFocused:Boolean, index: Int) -> Unit
) {

    CarouselStateUpdater(carouselState, itemCount)

    var focusState: FocusState? by remember { mutableStateOf(null) }
    val focusManager = LocalFocusManager.current
    val isLtr = LocalLayoutDirection.current == LayoutDirection.Ltr
    val carouselOuterBoxFocusRequester = remember { FocusRequester() }
    var isAutoScrollActive by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val accessibilityManager = remember {
        context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    }

    AutoScrollSideEffect(
        autoScrollDurationMillis = autoScrollDurationMillis,
        itemCount = itemCount,
        carouselState = carouselState,
        doAutoScroll = shouldPerformAutoScroll(focusState, accessibilityManager),
        onAutoScrollChange = {
            isAutoScrollActive = it
        }
    )

    Box(modifier = modifier
        .carouselSemantics(itemCount = itemCount, state = carouselState)
        .bringIntoViewIfChildrenAreFocused()
        .focusRequester(carouselOuterBoxFocusRequester)
        .onFocusChanged {
            focusState = it
            if (it.isFocused && isAutoScrollActive) {
                focusManager.moveFocus(FocusDirection.Enter)
            }
        }
        .handleKeyEvents(
            carouselState = carouselState,
            outerBoxFocusRequester = carouselOuterBoxFocusRequester,
            focusManager = focusManager,
            itemCount = itemCount,
            isLtr = isLtr
        ) {
            focusState
        }
        .focusable()
    ) {
        AnimatedContent(
            targetState = carouselState.activeItemIndex,
            transitionSpec = {
                if (carouselState.isMovingBackward) {
                    contentTransformEndToStart
                } else {
                    contentTransformStartToEnd
                }
            },
            label = "CarouselAnimation"
        ) { activeItemIndex ->
            LaunchedEffect(Unit) {
                if (accessibilityManager.isEnabled) {
                    carouselOuterBoxFocusRequester.requestFocus()
                }
                this@AnimatedContent.onAnimationCompletion {
                    if (!isAutoScrollActive && focusState?.isFocused == true) {
                        carouselOuterBoxFocusRequester.requestFocus()
                        focusManager.moveFocus(FocusDirection.Enter)
                    }
                }
            }
            if (itemCount > 0) {
                val isFocused = focusState?.let { f -> f.isFocused || f.hasFocus } ?: false
                content(
                    isFocused,
                    if (activeItemIndex < itemCount) activeItemIndex else 0
                )
            }
        }
        carouselIndicator()
    }
}