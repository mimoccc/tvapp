/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.button

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ButtonGlow
import androidx.tv.material3.ButtonScale
import androidx.tv.material3.Glow
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("UNUSED_PARAMETER", "PreviewShouldNotBeCalledRecursively")
@SuppressLint("ModifierParameter")
@Previews
@Composable
fun Button(
    text: Any? = "test",
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    containerColor: Color = Color.LightGray,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    roundRectSize: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(roundRectSize),
    borderSize: Dp = 2.dp,
    borderColor: Color = Color.White,
    glow: ButtonGlow = ButtonDefaults.NO_GLOW,
    scale: ButtonScale = ButtonDefaults.NO_SCALE,
    focusState: MutableState<FocusState> = rememberFocusState(),
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit = {
        TextAny(
            text = text,
            color = color,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
) = Button(
    modifier = modifier
        .border(BorderStroke(borderSize, borderColor), shape)
        .clickable { onClick?.invoke() },
    glow = glow,
    scale = scale,
    onClick = { onClick?.invoke() },
    colors = ButtonDefaults.colors(containerColor = containerColor),
    shape = ButtonDefaults.shape(shape),
    contentPadding = contentPadding,
    content = content
)

val ButtonDefaults.NO_GLOW
    get() = glow(
        glow = Glow.None,
        focusedGlow = Glow.None,
        pressedGlow = Glow.None
    )

val ButtonDefaults.NO_SCALE
    get() = scale(
        scale = 1f,
        focusedScale = 1f,
        pressedScale = 1f,
        disabledScale = 1f,
        focusedDisabledScale = 1f
    )