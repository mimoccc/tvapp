/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.rememberMutableInteractionSource
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.text.TextAny

@SuppressLint("AutoboxingStateValueProperty")
@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun <T> Tabs(
    modifier: Modifier = Modifier,
    items: List<T> = emptyList(),
    fontSize: TextUnit = 12.sp,
    itemsSpacing: Dp = 8.dp,
    activeContentColor: Color = Color.Green,
    selectedContentColor: Color = Color.White,
    focusedContentColor: Color = Color.White,
    interactionSource: MutableInteractionSource = rememberMutableInteractionSource(),
    onItemClick: (index: Int) -> Unit = {}
) {

    val selectedTabIndex = remember { mutableIntStateOf(0) }

    val colors = TabDefaults.pillIndicatorTabColors(
        activeContentColor = activeContentColor,
        contentColor = activeContentColor,
        selectedContentColor = activeContentColor,
        focusedContentColor = focusedContentColor,
        focusedSelectedContentColor = focusedContentColor,
        disabledActiveContentColor = activeContentColor.copy(alpha = 0.4f),
        disabledContentColor = activeContentColor.copy(alpha = 0.4f),
        disabledSelectedContentColor = selectedContentColor,
    )

    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex.value,
        contentColor = activeContentColor,
        separator = {
            Spacer(modifier = Modifier.width(itemsSpacing))
        },
        indicator = { tabPositions ->
            tabPositions.getOrNull(selectedTabIndex.value)?.let { tab ->
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tab,
                    activeColor = activeContentColor,
                    inactiveColor = activeContentColor.copy(alpha = 0.4f)
                )
            }
        },
    ) {

        items.forEachIndexed { index, tab ->

            Tab(
                modifier = Modifier,
                colors = colors,
                selected = index == selectedTabIndex.value,
                onFocus = {
                    selectedTabIndex.value = index
                },
                onClick = {
                    onItemClick(selectedTabIndex.value)
                },
                interactionSource = interactionSource
            ) {

                FocusableBox(
                    focusedColor = Color.Transparent,
                    onFocus = {
                        selectedTabIndex.value = index
                    }
                ) {

                    TextAny(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = if (tab is ItemWithTitle) tab.title else tab.toString(),
                        fontSize = fontSize,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }

    }

}