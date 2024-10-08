/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ComposeExt.rememberMutableInteractionSource
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.ui.components.complex.FocusableBox
import org.mjdev.tvlib.ui.components.text.TextAny

// todo from user
@SuppressLint("AutoboxingStateValueProperty")
@Previews
@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    items: List<Any?> = listOf(Unit, Unit, Unit),
    fontSize: TextUnit = 12.sp,
    itemsSpacing: Dp = 8.dp,
    activeContentColor: Color = Color.Green,
    selectedContentColor: Color = Color.White,
    focusedContentColor: Color = Color.White,
    interactionSource: MutableInteractionSource = rememberMutableInteractionSource(items),
    onItemClick: ((item: Any?) -> Unit)? = null
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val colors = TabDefaults.pillIndicatorTabColors(
        contentColor = activeContentColor,
        inactiveContentColor = activeContentColor.copy(alpha = 0.4f),
        selectedContentColor = activeContentColor,
        focusedContentColor = focusedContentColor,
        focusedSelectedContentColor = focusedContentColor,
        disabledContentColor = activeContentColor.copy(alpha = 0.4f),
        disabledInactiveContentColor = activeContentColor.copy(alpha = 0.4f),
        disabledSelectedContentColor = selectedContentColor,
    )
    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        contentColor = activeContentColor,
        separator = {
            Spacer(modifier = Modifier.width(itemsSpacing))
        },
        indicator = { tabPositions, doesTabRowHaveFocus ->
            tabPositions.getOrNull(selectedTabIndex)?.let { tab ->
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tab,
                    doesTabRowHaveFocus = doesTabRowHaveFocus,
                    activeColor = activeContentColor,
                    inactiveColor = activeContentColor.copy(alpha = 0.4f),
                )
            }
        },
    ) {
        items.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier,
                colors = colors,
                selected = index == selectedTabIndex,
                onFocus = {
                    selectedTabIndex = index
                },
                onClick = {
                    onItemClick?.invoke(selectedTabIndex)
                },
                interactionSource = interactionSource
            ) {
                val focusState = rememberFocusState(tab)
                FocusableBox(
                    focusedColor = Color.Transparent,
                    focusState = focusState,
                    onFocusChange = { state, _ ->
                        if (state.isFocused) {
                            selectedTabIndex = index
                        }
                    },
                    onClick = { onItemClick?.invoke(items[selectedTabIndex]) }
                ) {
                    TextAny(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = if (tab is ItemWithTitle<*>) tab.title else tab.toString(),
                        fontSize = fontSize,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}