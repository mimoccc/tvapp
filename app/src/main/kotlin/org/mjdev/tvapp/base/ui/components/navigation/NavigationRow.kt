/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ModifierExt.isFocused
import org.mjdev.tvapp.base.extensions.ModifierExt.rememberFocusState
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.icon.IconAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

@Preview
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    id: Int = -1,
    text: Any? = "menu item 1",
    icon: Any? = Icons.Default.AccountCircle,
    textColor: Color = Color.White,
    iconColor: Color = Color.White,
    focusedColor: Color = Color.Green,
    unFocusedColor: Color = Color.Transparent,
    expandedWidth: Dp = 150.dp,
    roundCornerSize: Dp = 32.dp,
    shape: Shape = RoundedCornerShape(roundCornerSize),
    strokeWidth: Dp = 2.dp,
    margin: Dp = 2.dp,
    padding: Dp = 4.dp,
//    brush: Brush? = BrushBuilder()
//        .type(BrushBuilder.Vertical)
//        .colors(
//            0.1f to Color.White,
//            0.3f to Color.Green,
//            0.4f to Color.Green,
//            0.5f to Color.Green,
//            0.9f to Color.Green,
//        )
//        .build(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    focusState: MutableState<FocusState?> = rememberFocusState(),
    onFocus: (id: Int) -> Unit = {},
    onClick: (id: Int) -> Unit = {},
) {

    val isEdit = isEditMode()

    FocusableBox(
        modifier = modifier
            .padding(margin)
            .border(
                BorderStroke(
                    strokeWidth,
                    if (isEdit || focusState.isFocused) focusedColor
                    else unFocusedColor
                ),
                shape
            ),
        focusState = focusState,
        shape = shape,
        focusedColor = focusedColor.copy(alpha = 0.5f),
        unFocusedColor = Color.Transparent,
        onFocus = {
            if (focusState.isFocused) {
                onFocus(id)
            }
        },
        onClick = {
            onClick(id)
        },
    ) {
        Row(
            modifier = Modifier
                .padding(padding),
//                .background(brush!!, shape),
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