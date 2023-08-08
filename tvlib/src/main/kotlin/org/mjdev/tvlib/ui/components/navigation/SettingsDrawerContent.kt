/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter

@SuppressLint("AutoboxingStateValueProperty")
@Preview
@Composable
fun SettingsDrawerContent(
) {
    Column(
        modifier = Modifier
            .recomposeHighlighter()
            .fillMaxHeight()
            .conditional(isEditMode()) {
                width(200.dp)
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        // todo
    }
}