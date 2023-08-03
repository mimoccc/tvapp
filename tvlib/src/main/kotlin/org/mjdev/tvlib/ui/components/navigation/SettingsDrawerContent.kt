/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

@SuppressLint("AutoboxingStateValueProperty")
@Preview
@Composable
fun SettingsDrawerContent(
    backgroundColor: Color = Color(0xff202020),
    roundCornerSize: Dp = 16.dp,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(roundCornerSize),
//    onDrawerItemClick: (id: Int) -> Unit = { id -> },
//    onDrawerItemFocus: (id: Int) -> Unit = { id -> },
) {
    Column(
        modifier = Modifier
            .recomposeHighlighter()
            .fillMaxHeight()
            .conditional(isEditMode()) {
                width(200.dp)
            }
            .background(backgroundColor, shape)
            .border(borderSize, borderColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        // todo
    }
}