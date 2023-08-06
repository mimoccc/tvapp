/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.LocalTextStyle
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.button.Button
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("LocalVariableName")
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
    dismissible: Boolean = true,
    onDismiss: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .recomposeHighlighter()
    ) {

        ConstraintLayout(
            modifier = Modifier
                .recomposeHighlighter()
                .fillMaxWidth()
                .padding(contentPadding)
                .background(backgroundColor, RoundedCornerShape(roundSize))
        ) {

            val style = LocalTextStyle.current

            val (_title, _message, _button) = createRefs()

            val textColor = color.takeOrElse {
                style.color.takeOrElse {
                    LocalContentColor.current
                }
            }

            TextAny(
                modifier = Modifier
                    .recomposeHighlighter()
                    .wrapContentSize()
                    .constrainAs(_title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = roundSize)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(paddingTitle),
                text = title ?: stringResource(id = R.string.title_error),
                fontWeight = fontWeight,
                fontSize = fontSizeTitle,
                color = color
            )

            BasicText(
                modifier = Modifier
                    .recomposeHighlighter()
                    .wrapContentSize()
                    .constrainAs(_message) {
                        top.linkTo(parent.top)
                        start.linkTo(_title.end)
                        end.linkTo(_button.start)
                        bottom.linkTo(parent.bottom)
                    }
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

                Button(
                    modifier = Modifier
                        .recomposeHighlighter()
                        .padding(4.dp)
                        .constrainAs(_button) {
                            top.linkTo(parent.top, margin = 4.dp)
                            end.linkTo(parent.end, margin = roundSize)
                            bottom.linkTo(parent.bottom, margin = 4.dp)
                        },
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

    }

}