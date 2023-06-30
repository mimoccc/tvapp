/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.icon.IconAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Preview
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    id: Int = -1,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
    text: Any? = "menu item 1",
    icon: Any? = Icons.Default.AccountCircle,
    textColor: Color = Color.White,
    iconColor: Color = Color.White,
    focusedColor: Color = Color.Black,
    unFocusedColor: Color = Color.Transparent,
    expandedWidth: Dp = 150.dp,
    contentPadding: Dp = 2.dp,
    onFocus: (id: Int) -> Unit = {},
    onClick: (id: Int) -> Unit = {},
) {

    val isEdit = isEditMode()
    val focused = remember { mutableStateOf(false) }

    FocusableBox(
        modifier = modifier
            .padding(contentPadding)
            .background(
                if (isEdit || focused.value) focusedColor else unFocusedColor
            ),
        focusedColor = focusedColor,
        unFocusedColor = unFocusedColor,
        onFocus = {
            onFocus(id)
        },
        onClick = {
            onClick(id)
        },
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.padding(4.dp)
            ) {
                IconAny(
                    src = icon,
                    contentDescription = null,
                    tint = iconColor
                )
            }
            AnimatedVisibility(
                visible = (drawerState.currentValue == DrawerValue.Open)
            ) {
                TextAny(
                    text = text,
                    softWrap = false,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(expandedWidth),
                    color = textColor,
                    textAlign = TextAlign.Left
                )

            }
        }
    }

}