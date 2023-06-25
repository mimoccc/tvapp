/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.tv.foundation.lazy.list.TvLazyListScope
import org.mjdev.tvapp.R

@Preview
@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: Any? = R.string.app_name,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    backgroundColor: Color = Color(0xFF202020),
    roundSize: Dp = 8.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.White,
    padding: PaddingValues = PaddingValues(roundSize),
    contentPadding: PaddingValues = PaddingValues(2.dp),
    messagesCount: Int = 0,
    onTitleClick: () -> Unit = {},
    onClockClick: () -> Unit = {},
    onMessageBadgeClick: () -> Unit = {},
    onUserPicClick: () -> Unit = {},
) = ConstraintLayout(
    modifier = modifier
        .fillMaxWidth()
        .background(backgroundColor, RoundedCornerShape(roundSize))
        .apply {
            if (borderSize > 0.dp) {
                border(borderSize, borderColor, RoundedCornerShape(roundSize))
            }
        }
        .padding(padding)
) {

    val (_title, _clock, _badge, _settings) = createRefs()

    Title(
        modifier = Modifier
            .constrainAs(_title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .padding(contentPadding),
        title = title ?: R.string.app_name,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color
    )

    Clock(
        modifier = Modifier
            .constrainAs(_clock) {
                top.linkTo(parent.top)
                end.linkTo(_badge.start)
                bottom.linkTo(parent.bottom)
            }
            .padding(contentPadding),
        timeTextColor = color,
        dateTextColor = color
    )

    Badge(
        modifier = Modifier
            .defaultMinSize(40.dp)
            .constrainAs(_badge) {
                top.linkTo(parent.top)
                end.linkTo(_settings.start)
                bottom.linkTo(parent.bottom)
            }
            .padding(contentPadding)
            .clip(CircleShape),
        count = messagesCount
    )

    UserPic(
        modifier = Modifier
            .size(64.dp)
            .constrainAs(_settings) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(contentPadding)
            .clip(CircleShape),
        onClick = { }
    )

}

fun TvLazyListScope.header(
    modifier: Modifier = Modifier,
    title: Any? = R.string.app_name,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    backgroundColor: Color = Color(0xFF202020),
    roundSize: Dp = 8.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.White,
    padding: PaddingValues = PaddingValues(roundSize),
    contentPadding: PaddingValues = PaddingValues(2.dp),
    messagesCount: Int = 0,
    onTitleClick: () -> Unit = {},
    onClockClick: () -> Unit = {},
    onMessageBadgeClick: () -> Unit = {},
    onUserPicClick: () -> Unit = {},
) = item {
    Header(
        modifier,
        title,
        fontSize,
        fontWeight,
        color,
        backgroundColor,
        roundSize,
        borderSize,
        borderColor,
        padding,
        contentPadding,
        messagesCount,
        onTitleClick,
        onClockClick,
        onMessageBadgeClick,
        onUserPicClick
    )
}