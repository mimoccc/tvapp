/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.LocalTextStyle
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.button.Button
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun ErrorMessage(
    error: Throwable? = null,
    contentPadding: Dp = 4.dp,
    title: Any? = null,
    fontSizeTitle: TextUnit = 24.sp,
    fontSizeMessage: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    paddingTitle: Dp = 4.dp,
    paddingMessage: Dp = paddingTitle,
    backgroundColor: Color = Color.Red,
    roundSize: Dp = 8.dp,
    cancelText: Any? = stringResource(id = R.string.bt_dismiss),
    shape: Shape = RoundedCornerShape(roundSize),
    dismissible: Boolean = true,
    onDismiss: () -> Unit = {}
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(backgroundColor, shape)
        .padding(contentPadding)
        .recomposeHighlighter(),
    verticalAlignment = Alignment.CenterVertically,
) {
    val style = LocalTextStyle.current
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current
        }
    }
    TextAny(
        modifier = Modifier
            .recomposeHighlighter()
            .wrapContentSize()
            .padding(paddingTitle),
        text = title ?: stringResource(id = R.string.title_error),
        fontWeight = fontWeight,
        fontSize = fontSizeTitle,
        color = color
    )
    Spacer(
        modifier = Modifier.weight(1f)
    )
    BasicText(
        modifier = Modifier
            .recomposeHighlighter()
            .wrapContentSize()
            .padding(paddingMessage),
        style = style.merge(
            color = textColor,
            fontSize = fontSizeMessage,
            fontWeight = fontWeight,
            textAlign = TextAlign.Left,
        ),
        minLines = 1,
        maxLines = 4,
        text =
        error?.message ?: (stringResource(id = R.string.error_unknown) +
                "\nType: " +
                (error?.javaClass?.simpleName ?: Exception::class.simpleName))
    )
    if (dismissible && (cancelText != null)) {
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Button(
            modifier = Modifier
                .recomposeHighlighter()
                .padding(4.dp),
            contentPadding = PaddingValues(
                8.dp,
                4.dp
            ),
            containerColor = Color.Red,
            onClick = {
                onDismiss()
            },
            content = {
                TextAny(
                    text = cancelText,
                    color = color
                )
            }
        )
    }
}