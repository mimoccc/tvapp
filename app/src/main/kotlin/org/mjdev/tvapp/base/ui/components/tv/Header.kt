/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.ui.components.badge.Badge

@TvPreview
@Composable
fun Header(
    title: Any? = R.string.app_name,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    backgroundColor: Color = Color.DarkGray,
    roundSize: Dp = 0.dp,
    padding: Dp = 0.dp,
    contentPadding: Dp = 2.dp,
    messagesCount: Int = 0,
    onTitleClick: () -> Unit = {},
    onClockClick: () -> Unit = {},
    onMessageBadgeClick: () -> Unit = {},
    onUserPicClick: () -> Unit = {},
) {
    val isEdit = isEditMode()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(roundSize))
            .padding(padding + roundSize)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Clock(
                modifier = Modifier.wrapContentSize(),
                backgroundColor = Color.Black.copy(alpha = 0.2f),
                contentPadding = contentPadding,
                timeTextColor = color,
                dateTextColor = color,
                onClick = onClockClick,
            )
            if (isEdit || (messagesCount > 0)) {
                Badge(
                    modifier = Modifier
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
                    .size(56.dp)
                    .clip(CircleShape),
                borderColor = Color.White,
                contentPadding = contentPadding,
                borderSize = 1.dp,
                onClick = onUserPicClick,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Title(
                modifier = Modifier.wrapContentSize(),
                title = title ?: R.string.app_name,
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = color,
                onClick = onTitleClick,
            )
        }
    }
}