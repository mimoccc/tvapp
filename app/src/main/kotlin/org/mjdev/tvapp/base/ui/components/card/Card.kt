/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun Card(
    modifier: Modifier = Modifier,
    scale: CardScale = CardScale.None,
    onClick: () -> Unit = {},
    onLongClick: (() -> Unit)? = null,
    shape: CardShape = CardDefaults.shape(),
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.NO_BORDER,
    glow: CardGlow = CardDefaults.NO_GLOW,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit = {}
) = androidx.tv.material3.Card(
    onClick = onClick,
    modifier = modifier,
    onLongClick = onLongClick,
    shape = shape,
    colors = colors,
    scale = scale,
    border = border,
    glow = glow,
    interactionSource = interactionSource,
    content = content
)

@OptIn(ExperimentalTvMaterial3Api::class)
val CardDefaults.NO_BORDER: CardBorder
    @Composable
    get() = border(
        border = Border.None,
        focusedBorder = Border.None,
        pressedBorder = Border.None
    )

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
    color: Color = Color.Gray,
    elevation: Dp = 10.dp
): CardGlow = glow(
    glow = Glow.None,
    focusedGlow = Glow(
        elevationColor = color,
        elevation = elevation
    ),
    pressedGlow = Glow.None
)