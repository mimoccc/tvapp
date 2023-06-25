/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyListScope
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
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
    activeContentColor: Color = Color.Black,
    selectedContentColor: Color = activeContentColor,
    focusedContentColor: Color = activeContentColor,
    onItemClick: (index: Int) -> Unit = {}
) {

    val selectedTabIndex = remember { mutableIntStateOf(0) }

    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex.value,

        separator = {
            Spacer(modifier = Modifier.width(itemsSpacing))
        },
    ) {

        items.forEachIndexed { index, tab ->

            Tab(
                modifier = Modifier,
                colors = TabDefaults.pillIndicatorTabColors(
                    activeContentColor,
                    activeContentColor.copy(alpha = 0.4f),
                    selectedContentColor,
                    focusedContentColor,
                    focusedContentColor,
                    activeContentColor,
                    activeContentColor.copy(alpha = 0.4f),
                    selectedContentColor,
                ),
                selected = index == selectedTabIndex.value,
                onFocus = {
                    selectedTabIndex.value = index
                },
            ) {

                FocusableBox(
                    focusedColor = Color.Transparent,
                    onFocus = {
                        selectedTabIndex.value = index
                    }
                ) {

                    TextAny(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        text = if (tab is ItemWithTitle) tab.title else tab.toString(),
                        fontSize = fontSize,
                        color = Color.Black
                    )

                }

            }

        }

    }

}

fun <T> TvLazyListScope.tabs(
    modifier: Modifier = Modifier,
    items: List<T> = emptyList(),
    fontSize: TextUnit = 12.sp,
    itemsSpacing: Dp = 8.dp,
    activeContentColor: Color = Color.Black,
    selectedContentColor: Color = activeContentColor,
    focusedContentColor: Color = activeContentColor,
    onItemClick: (index: Int) -> Unit = {}
) = item {
    Tabs(
        modifier,
        items,
        fontSize,
        itemsSpacing,
        activeContentColor,
        selectedContentColor,
        focusedContentColor,
        onItemClick
    )
}