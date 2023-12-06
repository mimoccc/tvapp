/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.button.Button
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun ErrorMessage(
    title: Any? = null,
    error: Throwable? = Exception("An unknown error happen, please contact developer, if issue does not disappear."),
    dismissText: Any? = R.string.bt_dismiss,
    color: Color = Color.White,
    backgroundColor: Color = Color.Red,
    fontSizeTitle: TextUnit = 18.sp,
    fontSizeMessage: TextUnit = 12.sp,
    fontSizeButton: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    paddingTitle: Dp = 8.dp,
    paddingContent: Dp = 4.dp,
    roundSize: Dp = 8.dp,
    borderSize: Dp = 1.dp,
    borderColor: Color = Color.DarkGray,
    backgroundShape: Shape = RoundedCornerShape(roundSize),
    dismissible: Boolean = true,
    visible: Boolean = isEditMode(),
    visibleState: MutableState<Boolean> = mutableStateOf(visible),
    enter: EnterTransition = EnterTransition.None,
    exit: ExitTransition = ExitTransition.None,
    dismissOnClick: () -> Unit = {},
) = AnimatedVisibility(
    visible = visibleState.value,
    enter = enter,
    exit = exit
) {
    BoxWithConstraints {
        Row(
            modifier = Modifier
                .width(constraints.maxWidth.dp)
                .padding(paddingContent)
                .border(borderSize, borderColor, backgroundShape)
                .background(backgroundColor, backgroundShape)
                .recomposeHighlighter(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextAny(
                modifier = Modifier
                    .padding(paddingTitle)
                    .recomposeHighlighter(),
                text = title ?: stringResource(id = R.string.title_error),
                fontWeight = fontWeight,
                fontSize = fontSizeTitle,
                color = color
            )
            TextAny(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .recomposeHighlighter(),
                color = color,
                fontSize = fontSizeMessage,
                fontWeight = fontWeight,
                textAlign = TextAlign.Left,
                maxLines = 4,
                softWrap = true,
                text = error?.message ?: (stringResource(id = R.string.error_unknown))
            )
            if (dismissible && (dismissText != null)) {
                Button(
                    modifier = Modifier
                        .padding(4.dp)
                        .recomposeHighlighter(),
                    text = dismissText,
                    fontSize = fontSizeButton,
                    contentPadding = PaddingValues(8.dp, 0.dp),
                    containerColor = Color.Black.copy(alpha = 0.2f),
                    onClick = {
                        visibleState.value = false
                        dismissOnClick.invoke()
                    }
                )
            }
        }
    }
}