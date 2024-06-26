/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.indicators

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun DashedProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Int = 3,
    totalNumberOfBars: Int = 10,
    dashColor: Color = Color.LightGray,
    strokeWidth: Float = 13f
) = Canvas(modifier = modifier) {
    val barArea = size.width / totalNumberOfBars
    val barLength = barArea - 10.dp.toPx()
    var nextBarStartPosition = 0F
    for (i in 0..totalNumberOfBars) {
        val barStartPosition = nextBarStartPosition + 5.dp.toPx()
        val barEndPosition = barStartPosition + barLength
        val start = Offset(x = barStartPosition, y = size.height / 2)
        val end = Offset(x = barEndPosition, y = size.height / 2)
        drawLine(
            cap = StrokeCap.Round,
            color = if (i < progress) dashColor else dashColor.copy(alpha = .5F),
            start = start,
            end = end,
            strokeWidth = strokeWidth
        )
        nextBarStartPosition = barEndPosition + 5.dp.toPx()
    }
}
