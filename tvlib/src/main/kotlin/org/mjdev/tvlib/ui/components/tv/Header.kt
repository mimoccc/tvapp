/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.ui.components.badge.Badge
import org.mjdev.tvlib.ui.components.complex.FocusableBox

@Previews
@Composable
fun Header(
    title: Any? = R.string.app_name,
    appIcon: Any? = R.drawable.person,
    userIcon: Any? = R.drawable.person,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundSize: Dp = 8.dp,
    padding: Dp = 0.dp,
    contentPadding: Dp = 2.dp,
    messagesCount: Int = 0,
    onTitleClick: () -> Unit = {},
    onClockClick: () -> Unit = {},
    onMessageBadgeClick: () -> Unit = {},
    onUserPicClick: () -> Unit = {},
) {
    val isEdit = isEditMode()
    val isPortrait = isPortraitMode()
    Box(
        modifier = Modifier
            .recomposeHighlighter()
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(roundSize))
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .recomposeHighlighter(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!isPortrait) {
                Clock(
                    modifier = Modifier
                        .wrapContentSize()
                        .recomposeHighlighter(),
                    backgroundColor = Color.Black.copy(alpha = 0.2f),
                    contentPadding = contentPadding,
                    timeTextColor = color,
                    dateTextColor = color,
                    onClick = onClockClick,
                )
            }
            if (isEdit || (messagesCount > 0)) {
                Badge(
                    modifier = Modifier
                        .recomposeHighlighter()
                        .wrapContentSize()
                        .clip(CircleShape),
                    contentPadding = contentPadding,
                    count = messagesCount,
                    borderColor = Color.White,
                    borderSize = 1.dp,
                    textSize = 20.sp,
                    textColor = Color.White,
                    onClick = onMessageBadgeClick,
                )
            }
            UserPic(
                modifier = Modifier
                    .recomposeHighlighter()
                    .size(56.dp)
                    .clip(CircleShape),
                src = userIcon,
                borderColor = Color.White,
                contentPadding = contentPadding,
                borderSize = 1.dp,
                onClick = onUserPicClick
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .recomposeHighlighter(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FocusableBox(
                modifier = Modifier
                    .recomposeHighlighter()
                    .wrapContentHeight()
                    .width(0.01.dp),
                onFocusChange = { state ->
                    if ((!isPortrait) && state.isFocused) {
                        onTitleClick()
                    }
                }
            )
            Title(
                modifier = Modifier
                    .wrapContentSize()
                    .recomposeHighlighter(),
                title = title ?: R.string.app_name,
                icon = appIcon,
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = color,
                onClick = onTitleClick,
            )
        }
    }
}