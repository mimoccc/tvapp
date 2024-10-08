/*
 *  Copyright (c) Milan Jurkulák 2024.
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.ui.components.button.Button
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.ui.components.text.TextAny

@Previews
@Composable
fun ErrorMessage(
    title: Any? = null,
    error: Throwable? = Exception("An unknown error happen, please contact developer, if issue does not disappear."),
    dismissText: Any? = R.string.bt_dismiss,
    color: Color = Color.White,
    backgroundColor: Color = Color.Red,
    fontSizeTitle: TextUnit = 14.sp,
    fontSizeMessage: TextUnit = 11.sp,
    fontSizeButton: TextUnit = 11.sp,
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
    dismissOnClick: (() -> Unit)? = null,
) = AnimatedVisibility(
    visible = visibleState.value,
    enter = enter,
    exit = exit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingContent)
                .border(borderSize, borderColor, backgroundShape)
                .background(backgroundColor, backgroundShape),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextAny(
                modifier = Modifier.padding(paddingTitle),
                text = title ?: stringResource(id = R.string.title_error),
                fontWeight = fontWeight,
                fontSize = fontSizeTitle,
                textAlign = TextAlign.Left,
                maxLines = 1,
                softWrap = false,
                color = color
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(Color.White),
            )
            TextAny(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                color = color,
                fontSize = fontSizeMessage,
                fontWeight = fontWeight,
                textAlign = TextAlign.Left,
                maxLines = 4,
                softWrap = true,
                lineHeight = (fontSizeMessage.value + 2).sp,
                overflow = TextOverflow.Ellipsis,
                text = error?.message ?: (stringResource(id = R.string.text_app_description))
            )
            if (dismissible && (dismissText != null)) {
                Button(
                    modifier = Modifier.padding(8.dp, 8.dp),
                    text = dismissText,
                    fontSize = fontSizeButton,
                    contentPadding = PaddingValues(8.dp, 2.dp),
                    containerColor = Color.White.copy(alpha = 0.2f),
                    onClick = {
                        visibleState.value = false
                        dismissOnClick?.invoke()
                    }
                )
            }
        }
    }
}