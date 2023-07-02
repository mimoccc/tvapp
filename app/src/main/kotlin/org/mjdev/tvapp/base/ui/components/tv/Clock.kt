/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ContextExt.dateAsString
import org.mjdev.tvapp.base.extensions.ContextExt.timeAsString
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Preview
@Composable
fun Clock(
    modifier: Modifier = Modifier,
    timeTextSize: TextUnit = 24.sp,
    timeTextWeight: FontWeight = FontWeight.Bold,
    timeTextColor: Color = Color.White,
    dateTextSize: TextUnit = 12.sp,
    dateTextWeight: FontWeight = FontWeight.Bold,
    dateTextColor: Color = Color.White,
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    backgroundColor: Color = Color.Transparent,
    roundSize: Dp = 8.dp,
    contentPadding: Dp = 2.dp,
    showTime: Boolean = true,
    showDate: Boolean = true,
    onClick: () -> Unit = {},
) {
    val isEdit = isEditMode()
    val context = LocalContext.current

    val timeFlow = if (showTime) channelFlow {
        launch {
            var time = "00:00:00"
            do {
                context.timeAsString.also { t ->
                    if (time != t) {
                        time = t
                        send(time)
                    }
                }
                delay(200L)
            } while (true)
        }
    }.collectAsState(initial = "00:00:00") else null

    val dateFlow = if (showDate) channelFlow {
        launch {
            var date = "1.1.1970"
            do {
                context.dateAsString.also { t ->
                    if (date != t) {
                        date = t
                        send(date)
                    }
                }
                delay(5000L)
            } while (true)
        }
    }.collectAsState(initial = "1.1.1970") else null

    FocusableBox(
        modifier = modifier
            .padding(contentPadding)
            .conditional(isEdit) {
                background(Color.DarkGray, RectangleShape)
            },
        shape = RoundedCornerShape(roundSize),
        onClick = onClick
    ) {

        Box(
            Modifier
                .background(backgroundColor, RoundedCornerShape(roundSize))
                .wrapContentSize()
                .clip(RoundedCornerShape(roundSize))
        ) {

            Column(
                modifier = Modifier.padding(8.dp, 2.dp, 8.dp, 2.dp),
                horizontalAlignment = horizontalAlignment,
            ) {

                if (showTime) TextAny(
                    modifier = modifier.wrapContentSize(),
                    text = timeFlow?.value,
                    fontWeight = timeTextWeight,
                    fontSize = timeTextSize,
                    color = timeTextColor
                )

                if (showDate) TextAny(
                    modifier = modifier.wrapContentSize(),
                    text = dateFlow?.value,
                    textAlign = TextAlign.End,
                    fontWeight = dateTextWeight,
                    fontSize = dateTextSize,
                    color = dateTextColor
                )

            }

        }

    }

}