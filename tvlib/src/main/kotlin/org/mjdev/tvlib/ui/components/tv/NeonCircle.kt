package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import org.mjdev.tvlib.annotations.Previews

@Previews
@Composable
fun NeonCircle(
    modifier: Modifier = Modifier,
    colorCircle: Color = Color.Green,
    colorStroke: Color = Color.Green,
    padding: Dp = 40.dp,
    strokeWidth: Dp = 12.dp,
    circleWidth: Dp = 3.dp
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
        val baseSize = min(maxWidth, maxHeight)
        val size = min(maxWidth, maxHeight) - (circleWidth + padding)
        val pad = padding / 2
        Canvas(
            modifier = Modifier.size(baseSize, baseSize)
        ) {
            drawIntoCanvas { canvas ->
                frameworkPaint.setShadowLayer(
                    circleWidth.toPx(),
                    pad.toPx(),
                    pad.toPx(),
                    colorStroke.copy(alpha = .5f).toArgb()
                )
                canvas.drawOval(
                    left = 0f,
                    top = 0f,
                    right = size.toPx(),
                    bottom = size.toPx(),
                    paint = paint
                )
                drawOval(
                    colorCircle,
                    topLeft = Offset(pad.toPx(), pad.toPx()),
                    size = Size(size.toPx(), size.toPx()),
                    alpha = 1f,
                    colorFilter = null,
                    style = Stroke(width = circleWidth.toPx())
                )
            }
        }
    }
}
