/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Suppress("LocalVariableName")
@TvPreview
@Composable
fun Title(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    focusShadowColor: Color = Color.Black,
    unfocusedShadowColor: Color = Color.Transparent,
    shadowSize: Dp = 4.dp,
    title: Any? = R.string.app_name,
    onFocus: () -> Unit = {},
    onClick: () -> Unit = {},
) {

    val isFocused: MutableState<Boolean> = remember { mutableStateOf(false) }
    val onTouched: (focused: Boolean) -> Unit = { focused ->
        if (focused) onClick() else onFocus()
    }

    FocusableBox(
        isFocused = isFocused,
        onTouched = onTouched,
        focusedColor = Color.Transparent,
    ) {

        isFocused.value.also { focused ->

            TextAny(
                modifier = modifier.padding(shadowSize),
                style = MaterialTheme.typography.bodyMedium.copy(
                    shadow = Shadow(
                        color = if (focused) focusShadowColor else unfocusedShadowColor,
                        offset = Offset(shadowSize.value, shadowSize.value),
                        blurRadius = shadowSize.value
                    )
                ),
                text = title ?: R.string.app_name,
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = color
            )

        }

    }

}