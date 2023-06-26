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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    selectedContentColor: Color = Color.White,
    focusedContentColor: Color = Color.White,
    onItemClick: (index: Int) -> Unit = {}
) {

    val selectedTabIndex = remember { mutableIntStateOf(0) }

    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex.value,
        contentColor = activeContentColor,
        separator = {
            Spacer(modifier = Modifier.width(itemsSpacing))
        },
    ) {

        items.forEachIndexed { index, tab ->

            val selected = index == selectedTabIndex.value
            val colors = TabDefaults.pillIndicatorTabColors(
                activeContentColor = activeContentColor,
                contentColor = activeContentColor,
                selectedContentColor = activeContentColor,
                focusedContentColor = focusedContentColor,
                focusedSelectedContentColor = focusedContentColor,
                disabledActiveContentColor = activeContentColor,
                disabledContentColor = activeContentColor,
                disabledSelectedContentColor = selectedContentColor,
            )

            Tab(
                modifier = Modifier,
                colors = colors,
                selected = selected,
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
                        color = if (selected) Color.Black else Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }

    }

}