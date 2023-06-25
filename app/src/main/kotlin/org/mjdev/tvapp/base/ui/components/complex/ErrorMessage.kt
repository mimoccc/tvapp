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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.tv.foundation.lazy.list.TvLazyListScope
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.LocalTextStyle
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.ui.components.button.Button
import org.mjdev.tvapp.base.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun ErrorMessage(
    error: Throwable? = null,
    title: Any? = null,
    fontSizeTitle: TextUnit = 24.sp,
    fontSizeMessage: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    paddingTitle: PaddingValues = PaddingValues(4.dp),
    paddingMessage: PaddingValues = PaddingValues(4.dp),
    backgroundColor: Color = Color.Red,
    roundSize: Dp = 8.dp,
    borderSize: Dp = 1.dp,
    borderColor: Color = Color.White,
    cancelText: Any? = stringResource(id = R.string.dismiss),
    onDismiss: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .apply {
                if (borderSize > 0.dp) {
                    border(borderSize, borderColor, RoundedCornerShape(roundSize))
                }
            }
            .fillMaxWidth()
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
                .wrapContentSize()
                .constrainAs(_title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = roundSize)
                    bottom.linkTo(parent.bottom)
                }
                .padding(paddingTitle),
            text = title ?: stringResource(id = R.string.error),
            fontWeight = fontWeight,
            fontSize = fontSizeTitle,
            color = color
        )

        BasicText(
            modifier = Modifier
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
// todo touchable
        if (cancelText != null) {
            Button(
                modifier = Modifier
                    .apply {
                        if (borderSize > 0.dp) {
                            border(borderSize, borderColor, RoundedCornerShape(roundSize))
                        }
                    }
                    .background(
                        Color.White.copy(
                            alpha = 0.5f
                        ),
                        RoundedCornerShape(roundSize)
                    )
                    .constrainAs(_button) {
                        top.linkTo(parent.top, margin = 4.dp)
                        end.linkTo(parent.end, margin = roundSize)
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                    },
                onClick = {
                    onDismiss()
                }
            ) {

                TextAny(
                    text = cancelText,
                    color = color
                )

            }

        }

    }

}

@Suppress("unused")
fun TvLazyListScope.errorMessage(error: Throwable?) = item { ErrorMessage(error) }