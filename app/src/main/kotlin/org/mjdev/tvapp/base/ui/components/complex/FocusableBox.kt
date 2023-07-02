/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun FocusableBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFocus: () -> Unit = {},
    enabled: Boolean = true,
    tonalElevation: Dp = 0.dp,
    focusedColor: Color = Color.Green,
    unFocusedColor: Color = Color.Transparent,
    pressedColor: Color = focusedColor.copy(alpha = 0.5f),
    disabledColor: Color = Color.Gray.copy(alpha = 0.5f),
    roundCornerSize: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    borderColor: Color = Color.Transparent,
    borderSize: Dp = 0.dp,
    focusState: MutableState<FocusState?> = rememberFocusState(),
    content: @Composable BoxScope.() -> Unit = {}
) {
    Surface(
        onClick = onClick,
        modifier = modifier.touchable(
            focusState = focusState,
            onFocus = { onFocus() },
            onClick = { onClick() },
        ),
        onLongClick = null,
        enabled = enabled,
        tonalElevation = tonalElevation,
        shape = ClickableSurfaceDefaults.shape(shape),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = unFocusedColor,
            contentColor = unFocusedColor,
            focusedContainerColor = focusedColor,
            focusedContentColor = focusedColor,
            pressedContainerColor = pressedColor,
            pressedContentColor = pressedColor,
            disabledContainerColor = disabledColor,
            disabledContentColor = disabledColor
        ),
        scale = ClickableSurfaceDefaults.scale(
            1f,
            1f,
            1f,
            1f,
            1f
        ),
        border = Border(
            border = BorderStroke(
                width = borderSize,
                color = borderColor
            ),
            inset = 0.dp,
            shape = shape
        ).let { b -> ClickableSurfaceDefaults.border(b, b, b, b, b) },
        glow = ClickableSurfaceDefaults.glow(),
        content = content
    )

}