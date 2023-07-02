/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ComposeExt
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.ui.components.image.PhotoImage

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun PhotoCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    focusState: MutableState<FocusState?> = ComposeExt.rememberFocusState(),
    imageRenderer: @Composable (modifier: Modifier) -> Unit = { m ->
        PhotoImage(
            modifier = m,
            src = (item as? ItemWithImage)?.imageUrl,
            contentDescription = (item as? ItemWithDescription)?.description?.toString(),
        )
    },
    aspectRatio: Float? = 16f / 9f,
    placeholder: @Composable () -> Unit = {},
    scale: CardScale = CardDefaults.scale(),
    onClick: (item: Any?) -> Unit = {},
) {
    ItemCard(
        item = item,
        modifier = modifier,
        contentScale = contentScale,
        aspectRatio = aspectRatio,
        scale = scale,
        imageRenderer = imageRenderer,
        placeholder = placeholder,
        onClick = onClick,
    )
}