/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.ui.components.complex.FocusableCard

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
@SuppressLint("ModifierParameter")
fun ItemCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    onFocus: (item: Any?) -> Unit = {},
    onClick: (item: Any?) -> Unit = {},
) {

    FocusableCard(
        modifier = modifier
            .widthIn(max = 320.dp)
            .aspectRatio(16f / 9f),
        item = item,
        onFocus = onFocus,
        onClick = onClick,
    )

}