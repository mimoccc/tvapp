/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.extensions.ComposeExt.isLandscapeMode
import org.mjdev.tvlib.extensions.ComposeExt.isOpen
import org.mjdev.tvlib.extensions.ComposeExt.isPortraitMode
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.focusState
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.ui.components.card.FocusHelper
import org.mjdev.tvlib.ui.components.complex.FocusableBox
import org.mjdev.tvlib.ui.components.icon.IconAny
import org.mjdev.tvlib.ui.components.text.TextAny
import timber.log.Timber

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
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
    strokeWidth: Dp = 1.dp,
    margin: Dp = 0.dp,
    padding: Dp = 4.dp,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    focused: Boolean = isEditMode(),
    focusState: MutableState<FocusState?> = rememberFocusState(
        text,
        FocusHelper(focused)
    ),
    focusRequester: FocusRequester = rememberFocusRequester(),
    onFocus: (id: Int) -> Unit = {},
    onClick: (id: Int) -> Unit = {},
) {
    val isEdit = isEditMode()
    val isShown = isLandscapeMode() || drawerState.isOpen
    val isPortrait = isPortraitMode()
    val enter = if (isPortrait) expandVertically()
    else (fadeIn() + expandVertically())
    val exit = if (isPortrait) shrinkVertically()
    else (fadeOut() + shrinkVertically())
    if (isShown) {
        FocusableBox(
            modifier = modifier
                .recomposeHighlighter()
                .padding(margin)
                .border(
                    BorderStroke(
                        strokeWidth,
                        if (isEdit || focusState.isFocused) focusedColor
                        else unFocusedColor
                    ),
                    shape
                )
                .focusState(focusState),
            shape = shape,
            focusedColor = focusedColor.copy(alpha = 0.5f),
            focusRequester = focusRequester,
            unFocusedColor = unFocusedColor,
            onFocusChange = { state ->
                if (state.isFocused || state.hasFocus) {
                    onFocus(id)
                }
            },
            onClick = {
                onClick(id)
            },
        ) {
            Row(
                modifier = Modifier
                    .recomposeHighlighter()
                    .padding(padding),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .recomposeHighlighter()
                ) {
                    IconAny(
                        src = icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
                AnimatedVisibility(
                    enter = enter,
                    exit = exit,
                    visible = (isEdit || (drawerState.currentValue == DrawerValue.Open))
                ) {
                    TextAny(
                        text = text,
                        softWrap = false,
                        modifier = Modifier
                            .recomposeHighlighter()
                            .padding(8.dp)
                            .width(expandedWidth),
                        color = textColor,
                        textAlign = TextAlign.Left
                    )
                }
            }
        }
        LaunchedEffect(focused) {
            try {
                if (focused) {
                    focusRequester.requestFocus()
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}