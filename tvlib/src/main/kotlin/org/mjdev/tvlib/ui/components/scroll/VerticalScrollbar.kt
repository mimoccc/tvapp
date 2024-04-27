/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.scroll

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.ui.icons.ScrollDown
import org.mjdev.tvlib.ui.icons.ScrollDownDisabled
import org.mjdev.tvlib.ui.icons.ScrollUp
import org.mjdev.tvlib.ui.icons.ScrollUpDisabled

@Composable
inline fun <reified T> WithScope(
    scope: T,
    noinline config: @Composable T.() -> Unit,
    block: @Composable T.() -> Unit
) {
    config.invoke(scope)
    block.invoke(scope)
}

@Preview
@Composable
fun ScrollBarVertical(
    modifier: Modifier = Modifier,
    config: @Composable ScrollBarVerticalConfigScope.() -> Unit = {},
    visible: Boolean = true,
    scrollState: ScrollState = rememberScrollState(),
) = WithScope(ScrollBarVerticalConfigScope(), config) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .conditional(
                condition = visible,
                ifTrue = {
                    wrapContentWidth()
                },
                ifFalse = {
                    width(0.dp)
                }
            )
    ) {
        val maxHeight = maxHeight.value
        val steps by remember {
            derivedStateOf {
                scrollState.maxValue.toFloat()
            }
        }
        val currentValue by remember {
            derivedStateOf {
                scrollState.value.toFloat()
            }
        }
        if (visible) {
            val canScrollDown by remember {
                derivedStateOf {
                    steps > currentValue
                }
            }
            val canScrollTop by remember {
                derivedStateOf {
                    currentValue > 0
                }
            }
            val topIcon by remember {
                derivedStateOf {
                    if (canScrollTop) Icons.Filled.ScrollUp else Icons.Filled.ScrollUpDisabled
                }
            }
            val bottomIcon by remember {
                derivedStateOf {
                    if (canScrollDown) Icons.Filled.ScrollDown else Icons.Filled.ScrollDownDisabled
                }
            }
            val handleHeight by remember {
                derivedStateOf {
                    maxHeight / (steps - 1)
                }
            }
            val handleContainerSize by remember {
                derivedStateOf {
                    maxHeight - handleHeight
                }
            }
            val stepSize by remember {
                derivedStateOf {
                    handleContainerSize / (steps + 1)
                }
            }
            val handlePosition by remember {
                derivedStateOf {
                    stepSize * currentValue
                }
            }
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = topIcon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(iconPadding)
                        .size(iconSize)
                )
                Box(
                    modifier = Modifier
                        .width(iconSize + iconPadding + iconPadding)
                        .fillMaxHeight()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .height(maxHeight.dp)
                            .width(handleWidth)
                            .border(
                                handleBorderWidth,
                                color = handleBorderColor,
                                shape = handleShape
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = handlePosition.dp)
                                .height(handleHeight.dp)
                                .width(handleWidth)
                                .background(
                                    color = handleColor,
                                    shape = MaterialTheme.shapes.medium
                                )
                        ) {}
                    }
                }
                Icon(
                    imageVector = bottomIcon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(iconPadding)
                        .size(iconSize)
                )
            }
        }
    }
}

data class ScrollBarVerticalConfigScope(
    val iconSize: Dp = 24.dp,
    val iconPadding: Dp = 16.dp,

    val handleColor: Color = Color.Black,
    val handleBorderColor: Color = Color.Black,
    val handleShape: Shape = RoundedCornerShape(12.0.dp),
    val handleWidth: Dp = 8.dp,
    val handleBorderWidth: Dp = 1.dp
)
