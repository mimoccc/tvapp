@file:Suppress("unused")

package org.mjdev.tvlib.ui.toolkit

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val DEFAULT_FADING_EDGE_LENGTH = 10.dp

@Suppress("unused")
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.fadeEdge(
    color: Color,
    state: ScrollableState,
    horizontal: Boolean = true,
    length: Dp = DEFAULT_FADING_EDGE_LENGTH,
) = composed(
    debugInspectorInfo {
        name = "length"
        value = length
    },
) {
    Modifier.drawWithContent {
        // The length in pixels
        val lengthPx = length.toPx()

        // The alpha of color

        // it constitutes of two reacts top/bottom or left right.
        // animate until scroll is less than lengthPx to 1
        // if offset of scroll(in Px) is > 0 show first.
        val alpha1: Float

        // animate until scroll is greater than max - lengthPx to 0
        // if offset of scroll(in Px) is less than maxHeight - lengthInPx of list.
        val alpha2: Float

        fun alpha(fraction: Float) =
            lerp(0f, 1f, fraction.coerceAtMost(1f))

        // determine value of alpha 1 and alpha2 from state.
        when (state) {
            is ScrollState -> {
                // calculate fraction value between 0 and 1
                // since value moves from 0 to max [i.e. size of the list in pixels]
                // we just need to animate for first lengthPx pixels for rest it is visible.
                var fraction = state.value / lengthPx
                alpha1 = if (state.value > lengthPx) 1f else alpha(fraction = fraction)

                // invert fraction from last item
                // animate for last lengthPx pixels but inverted.
                fraction = 1 - (state.maxValue - state.value) / lengthPx
                alpha2 = if (state.value < (state.maxValue - lengthPx)) 1f else 1 - alpha(fraction)
            }
            is LazyListState -> {
                val info = state.layoutInfo

                //  we need to animate for oth item till lengthPx Pixels
                val fraction = state.firstVisibleItemScrollOffset / lengthPx
                alpha1 = if (state.firstVisibleItemIndex != 0) 1f else alpha(fraction)

                val isLast =
                    state.firstVisibleItemIndex + info.visibleItemsInfo.size == info.totalItemsCount
                //FixMe Here is a bug which causes blink as we cant determine actual last item
                // animate for
                alpha2 = if (!isLast) 1f else 1 - alpha(fraction)
            }
            else -> error("The $state is not supported.")
        }

        fun drawVertical() {
            // draw top edge
            val color1 = color.copy(alpha1)
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(color1, Color.Transparent),
                    startY = 0f,
                    endY = lengthPx,
                ),
                size = Size(
                    this.size.width,
                    lengthPx
                ),
            )
            //draw bottom edge
            val color2 = color.copy(alpha2)
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, color2),
                    startY = size.height - lengthPx,
                    endY = size.height,
                ),
                topLeft = Offset(x = 0f, y = size.height - lengthPx),
            )
        }

        fun drawHorizontal() {
            // start edge
            val color1 = color.copy(alpha1)
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(color1, Color.Transparent),
                    startX = 0f,
                    endX = lengthPx,
                ),
                size = Size(
                    lengthPx,
                    this.size.height,
                ),
            )

            //end edge.
            val color2 = color.copy(alpha2)
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, color2),
                    startX = size.width - lengthPx,
                    endX = size.width,
                ),
                topLeft = Offset(x = size.width - lengthPx, y = 0f),
            )
        }

        drawContent()

        when (horizontal) {
            true -> drawHorizontal()
            else -> drawVertical()
        }
    }
}

fun Modifier.verticalFadingEdge(
    color: Color,
    state: ScrollableState,
    length: Dp = DEFAULT_FADING_EDGE_LENGTH,
) = fadeEdge(state = state, horizontal = false, color = color, length = length)

fun Modifier.horizontalFadingEdge(
    color: Color,
    state: ScrollableState,
    length: Dp = DEFAULT_FADING_EDGE_LENGTH,
) = fadeEdge(state = state, horizontal = true, color = color, length = length)