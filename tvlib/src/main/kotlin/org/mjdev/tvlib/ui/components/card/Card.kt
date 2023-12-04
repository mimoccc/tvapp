/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("PreviewShouldNotBeCalledRecursively")

package org.mjdev.tvlib.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberMutableInteractionSource
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.ModifierExt.tvAspectRatio

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun Card(
    modifier: Modifier = Modifier,
    scale: CardScale = CardScale.None,
    onClick: () -> Unit = {},
    onLongClick: (() -> Unit)? = null,
    shape: CardShape = CardDefaults.shape(),
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.NO_BORDER,
    focusGlowColor: Color = Color.Green,
    unFocusGlowColor: Color = Color.Transparent,
    glow: CardGlow = CardDefaults.colorFocusGlow(
        focusGlowColor,
        unFocusGlowColor
    ),
    aspectRatio: Float? = 16f / 9f,
    interactionSource: MutableInteractionSource = rememberMutableInteractionSource(),
    content: @Composable ColumnScope.() -> Unit = {}
) {
    androidx.tv.material3.Card(
        onClick = onClick,
        modifier = modifier
            .recomposeHighlighter()
            .widthIn(max = 320.dp)
            .conditional(aspectRatio != null) {
                tvAspectRatio(aspectRatio)
            }
            .clickable {
                onClick()
            },
        onLongClick = onLongClick,
        shape = shape,
        colors = colors,
        scale = scale,
        border = border,
        glow = glow,
        interactionSource = interactionSource,
        content = content
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
val CardDefaults.NO_BORDER: CardBorder
    @Composable
    get() = border(
        border = Border.None,
        focusedBorder = Border.None,
        pressedBorder = Border.None
    )

@Suppress("unused")
@OptIn(ExperimentalTvMaterial3Api::class)
val CardDefaults.NO_GLOW: CardGlow
    @Composable
    get() = glow(
        glow = Glow.None,
        focusedGlow = Glow.None,
        pressedGlow = Glow.None
    )

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CardDefaults.colorFocusGlow(
    focusColor: Color = Color.Green,
    onUnFocusColor: Color = Color.Transparent,
    elevation: Dp = 10.dp
): CardGlow = glow(
    glow = Glow(
        elevationColor = onUnFocusColor,
        elevation = elevation
    ),
    focusedGlow = Glow(
        elevationColor = focusColor,
        elevation = elevation
    ),
    pressedGlow = Glow(
        elevationColor = onUnFocusColor,
        elevation = elevation
    )
)

@Suppress("UNUSED_PARAMETER")
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CardDefaults.colorFocusBorder(
    colorFocused: Color = Color.Green,
    colorUnFocused: Color = Color.Black,
    colorPressed: Color = Color.Yellow,
    width: Dp = 1.dp,
    roundCornerSize: Dp = 8.dp
) = border(
//    border = Border(
//        border = BorderStroke(
//            width = width,
//            color = colorUnFocused
//        ),
//        shape = RoundedCornerShape(roundCornerSize)
//    ),
    focusedBorder = Border(
        border = BorderStroke(
            width = width,
            color = colorFocused
        ),
        shape = RoundedCornerShape(roundCornerSize)
    ),
//    pressedBorder = Border(
//        border = BorderStroke(
//            width = width,
//            color = colorPressed
//        ),
//        shape = RoundedCornerShape(roundCornerSize)
//    )
)

@Suppress("unused")
@Composable
fun rainbowColorsBrush(
    key:Any?=null
) = remember(key) {
    Brush.sweepGradient(
        listOf(
            Color(0xFF9575CD),
            Color(0xFFBA68C8),
            Color(0xFFE57373),
            Color(0xFFFFB74D),
            Color(0xFFFFF176),
            Color(0xFFAED581),
            Color(0xFF4DD0E1),
            Color(0xFF9575CD)
        )
    )
}

@Suppress("unused")
fun Modifier.rainbowBorder(
    borderSize: Dp = 4.dp,
    borderRound: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(borderRound)
) = composed {
    border(
        BorderStroke(
            borderSize,
            rainbowColorsBrush()
        ),
        shape
    )
}