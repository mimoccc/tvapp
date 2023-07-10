/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.ui.components.image.PhotoImage

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun PhotoCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    focusState: MutableState<FocusState?> = rememberFocusState(item),
    imageRenderer: @Composable () -> Unit = {
        PhotoImage(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage<*>)?.image,
            contentScale = contentScale,
            contentDescription = (item as? ItemWithDescription<*>)?.description?.toString(),
        )
    },
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    placeholder: @Composable () -> Unit = {},
    scale: CardScale = CardDefaults.scale(),
    onClick: (item: Any?) -> Unit = {},
) {
    ItemCard(
        item = item,
        modifier = modifier.recomposeHighlighter(),
        contentScale = contentScale,
        focusState = focusState,
        aspectRatio = aspectRatio,
        cardWidth = cardWidth,
        scale = scale,
        imageRenderer = imageRenderer,
        placeholder = placeholder,
        onClick = onClick,
    )
}