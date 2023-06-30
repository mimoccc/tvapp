/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ModifierExt
import org.mjdev.tvapp.base.extensions.ModifierExt.isFocused
import org.mjdev.tvapp.base.extensions.ModifierExt.rememberFocusState
import org.mjdev.tvapp.base.ui.components.image.CircleImage
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Preview
@Composable
fun Title(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    focusShadowColor: Color = Color.Green,
    unfocusedShadowColor: Color = Color.Transparent,
    shadowSize: Dp = 6.dp,
    title: Any? = R.string.app_name,
    focusState: MutableState<FocusState?> = rememberFocusState(),
    interactionSource: MutableInteractionSource = ModifierExt.rememberMutableInteractionSource(),
    onFocus: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val isEdit = isEditMode()

    FocusableBox(
        modifier = modifier,
        focusState = focusState,
        onFocus = onFocus,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        focusedColor = Color.Transparent,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
            verticalAlignment = CenterVertically,
        ) {
            CircleImage(
                modifier = Modifier
                    .height((fontSize.value).dp)
                    .aspectRatio(1f)
                    .scale(1.3f),
                borderColor = Color.White,
                borderSize = 1.dp,
                src = R.mipmap.ic_launcher
            )
            TextAny(
                modifier = modifier.padding(
                    10.dp,
                    shadowSize,
                    shadowSize,
                    shadowSize
                ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    shadow = Shadow(
                        color = if (isEdit || focusState.isFocused) focusShadowColor else unfocusedShadowColor,
                        offset = Offset(0f, 0f),
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