package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.ui.components.card.FocusHelper

@Previews
@Composable
fun NeonRectangle(
    modifier: Modifier = Modifier,
    colorRect: Color = Color.Green,
    colorStroke: Color = Color.Green,
    padding: Dp = 40.dp,
    strokeWidth: Dp = 12.dp,
    shadowWidth: Dp = 3.dp,
    roundCorner: Dp = 10.dp
) {
    val paint = remember {
        Paint().apply {
            style = PaintingStyle.Stroke
            this.strokeWidth = strokeWidth.value
        }
    }
    val frameworkPaint = remember {
        paint.asFrameworkPaint().apply {
            color = colorStroke.copy(alpha = 0f).toArgb()
        }
    }
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val baseSize = DpSize(maxWidth, maxHeight)
        val size = DpSize(
            maxWidth - (shadowWidth + padding),
            maxHeight - (shadowWidth + padding)
        )
        val pad = padding / 2
        Canvas(
            modifier = Modifier.size(baseSize)
        ) {
            drawIntoCanvas { canvas ->
                frameworkPaint.setShadowLayer(
                    shadowWidth.toPx(),
                    pad.toPx(),
                    pad.toPx(),
                    colorStroke.copy(alpha = .5f).toArgb()
                )
                canvas.drawRoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width.toPx(),
                    bottom = size.height.toPx(),
                    radiusX = roundCorner.toPx(),
                    radiusY = roundCorner.toPx(),
                    paint = paint
                )
                drawRoundRect(
                    color = colorRect,
                    topLeft = Offset(pad.toPx(), pad.toPx()),
                    size = Size(size.width.toPx(), size.height.toPx()),
                    cornerRadius = CornerRadius(
                        roundCorner.toPx(),
                        roundCorner.toPx()
                    ),
                    colorFilter = null,
                    style = Stroke(width = shadowWidth.toPx())
                )
            }
        }
    }
}

@Suppress("unused")
@Composable
fun NeonRectangle(
    modifier: Modifier = Modifier,
    focusState: MutableState<FocusState> = mutableStateOf(FocusHelper(true))
    // todo
//    glow: CardGlow = CardDefaults.glow()
) {
    if (focusState.isFocused) {
        // todo
        val colorRect: Color = Color.Green
        val colorStroke: Color = Color.Green
        val strokeWidth: Dp = 10.dp
        val shadowWidth: Dp = 3.dp
        val roundCorner: Dp = 12.dp
        NeonRectangle(
            modifier = modifier,
            padding = 0.dp,
            colorRect = colorRect,
            colorStroke = colorStroke,
            strokeWidth = strokeWidth,
            shadowWidth = shadowWidth,
            roundCorner = roundCorner
        )
    }
}


