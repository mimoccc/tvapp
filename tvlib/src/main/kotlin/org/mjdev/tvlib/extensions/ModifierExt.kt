/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.relocation.BringIntoViewResponder
import androidx.compose.foundation.relocation.bringIntoViewResponder
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Float.min

@Suppress("MemberVisibilityCanBePrivate")
object ModifierExt {

    // used to debug library
    const val isDebug = false

    fun Modifier.conditional(
        condition: Boolean,
        other: Modifier.() -> Modifier
    ): Modifier {
        return when (condition) {
            true -> this.then(other.invoke(this))
            else -> this
        }
    }

    fun Modifier.tvAspectRatio(
        ratio: Float?,
        matchHeightConstraintsFirst: Boolean = false
    ): Modifier = conditional(ratio != null) {
        aspectRatio(
            ratio!!,
            matchHeightConstraintsFirst
        )
    }

    fun Modifier.focusState(
        focusState: MutableState<FocusState?>
    ): Modifier = onFocusChanged { state ->
        focusState.value = state
    }

    fun Modifier.requestFocusOnTouch(
        focusRequester: FocusRequester,
        onTouch: () -> Unit = {}
    ): Modifier = this then focusRequester(
        focusRequester
    ).pointerInput(this) {
        detectTapGestures {
            try {
                onTouch()
                focusRequester.requestFocus()
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }

    @Stable
    fun Modifier.recomposeHighlighter(): Modifier = conditional(
        isDebug
    ) { this.then(recomposeModifier) }

    @SuppressLint("AutoboxingStateValueProperty")
    private val recomposeModifier = Modifier.composed(
        inspectorInfo = debugInspectorInfo {
            name = "recomposeHighlighter"
        }) {
        val totalCompositions = remember { arrayOf(0L) }
        totalCompositions[0]++
        val totalCompositionsAtLastTimeout = remember { mutableLongStateOf(0L) }
        LaunchedEffect(totalCompositions[0]) {
            delay(3000)
            totalCompositionsAtLastTimeout.value = totalCompositions[0]
        }
        Modifier.drawWithCache {
            onDrawWithContent {
                drawContent()
                val numCompositionsSinceTimeout =
                    totalCompositions[0] - totalCompositionsAtLastTimeout.value
                val hasValidBorderParams = size.minDimension > 0f
                if (!hasValidBorderParams || numCompositionsSinceTimeout <= 0) {
                    return@onDrawWithContent
                }
                val (color, strokeWidthPx) =
                    when (numCompositionsSinceTimeout) {
                        1L -> Color.Blue to 1f
                        2L -> Color.Green to 2.dp.toPx()
                        else -> {
                            lerp(
                                Color.Yellow.copy(alpha = 0.8f),
                                Color.Red.copy(alpha = 0.5f),
                                min(
                                    1f,
                                    (numCompositionsSinceTimeout - 1).toFloat() / 100f
                                )
                            ) to numCompositionsSinceTimeout.toInt().dp.toPx()
                        }
                    }

                val halfStroke = strokeWidthPx / 2
                val topLeft = Offset(halfStroke, halfStroke)
                val borderSize = Size(
                    size.width - strokeWidthPx,
                    size.height - strokeWidthPx
                )
                val fillArea = (strokeWidthPx * 2) > size.minDimension
                val rectTopLeft = if (fillArea) Offset.Zero else topLeft
                val size = if (fillArea) size else borderSize
                val style = if (fillArea) Fill else Stroke(strokeWidthPx)
                drawRect(
                    brush = SolidColor(color),
                    topLeft = rectTopLeft,
                    size = size,
                    style = style
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun Modifier.bringIntoViewIfChildrenAreFocused(): Modifier = composed(
        inspectorInfo = debugInspectorInfo { name = "bringIntoViewIfChildrenAreFocused" },
        factory = {
            var myRect: Rect = Rect.Zero
            this
                .onSizeChanged {
                    myRect = Rect(Offset.Zero, Offset(it.width.toFloat(), it.height.toFloat()))
                }
                .bringIntoViewResponder(
                    remember {
                        object : BringIntoViewResponder {
                            @ExperimentalFoundationApi
                            override fun calculateRectForParent(localRect: Rect): Rect = myRect

                            @ExperimentalFoundationApi
                            override suspend fun bringChildIntoView(localRect: () -> Rect?) {
                            }
                        }
                    }
                )
        }
    )

}