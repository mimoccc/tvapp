/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.complex.FocusableBox
import org.mjdev.tvlib.ui.components.image.CircleImage
import org.mjdev.tvlib.ui.components.text.TextWithShadow

@Preview
@Composable
fun Title(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    focusShadowColor: Color = Color.Green,
    unfocusedShadowColor: Color = Color.Transparent,
    shadowSize: Dp = 10.dp,
    title: Any? = R.string.app_name,
    icon: Any? = R.drawable.person,
    onClick: () -> Unit = {},
) {
    val isEdit = isEditMode()
    val focusState = rememberFocusState(title)
    val shadowColor = if (isEdit || focusState.isFocused) focusShadowColor else unfocusedShadowColor
    FocusableBox(
        modifier = modifier.recomposeHighlighter(),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        focusedColor = Color.Transparent,
        onFocusChange = { state ->
            focusState.value = state
        }
    ) {
        Row(
            modifier = modifier
                .padding(16.dp, 8.dp, 8.dp, 8.dp)
                .recomposeHighlighter(),
            verticalAlignment = CenterVertically,
        ) {
            CircleImage(
                modifier = Modifier
                    .recomposeHighlighter()
                    .height((fontSize.value).dp)
                    .aspectRatio(1f)
                    .scale(1.3f)
                    .shadow(
                        elevation = shadowSize,
                        shape = CircleShape,
                        ambientColor = shadowColor,
                        spotColor = shadowColor,
                        clip = false,
                    ),
                borderColor = Color.White,
                backGroundColor = Color.White,
                borderSize = 1.3.dp,
                src = icon
            )
            TextWithShadow(
                modifier = modifier.recomposeHighlighter(),
                shadowColor = shadowColor,
                shadowSize = shadowSize,
                text = title ?: R.string.app_name,
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = color
            )
        }
    }
}