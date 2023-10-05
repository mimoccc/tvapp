/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.icon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview
@Composable
fun ConversationReceiptIcon(
    backgroundColor: Color = Color.Black,
    borderColor: Color = Color.Black,
    tint: Color = Color.White,
    borderSize: Dp = 2.dp,
    centerIcon: ImageVector = Icons.Filled.Check,
    size: Dp = 32.dp,
    shape: Shape = RoundedCornerShape(size),
    innerPadding: Dp = 4.dp,
    contentDescription: String = "",
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    Box(
        modifier = Modifier
            .size(size)
            .clip(shape)
            .background(backgroundColor, shape)
            .border(
                BorderStroke(
                    borderSize,
                    borderColor
                ),
                shape
            ),
    ) {
        Image(
            modifier = Modifier
                .padding(innerPadding)
                .size(size)
                .background(backgroundColor, shape),
            imageVector = centerIcon,
            contentDescription = contentDescription,
            contentScale = ContentScale.Inside,
            colorFilter = colorFilter
        )
    }
}

@Preview
@Composable
fun TextDashed() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .width(227.dp)
                .background(
                    Color.Black,
                    shape = DottedShape(
                        step = 4.dp,
                        width = 0.75.dp,
                    )
                ),
            textAlign = TextAlign.Center,
            text = "Unread messages"
        )
    }
}

data class DottedShape(
    val step: Dp,
    val width: Dp = 1.dp,
    val gravity: Alignment.Vertical = Alignment.Bottom
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val gravityY = when (gravity) {
            Alignment.Bottom -> size.height - width.value
            Alignment.Top -> 0f
            else -> 0f
        }
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = width.value)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = gravityY),
                    size = dotSize
                )
            )
        }
        close()
    })
}