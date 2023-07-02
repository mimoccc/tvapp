/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.interfaces.ItemWithBackground
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.ui.components.image.ImageAny

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
@SuppressLint("ModifierParameter")
fun CarouselCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardScale.None,
    focusState: MutableState<FocusState?> = rememberFocusState(),
    placeholder: @Composable () -> Unit = {}, // todo
    imageRenderer: @Composable (modifier: Modifier) -> Unit = {
        ImageAny(
            modifier = modifier,
            src = (item as? ItemWithBackground)?.backgroundImageUrl,
            contentDescription = (item as? ItemWithDescription)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    onFocus: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    val isEdit = isEditMode()
    PhotoCard(
        item = item,
        modifier = modifier.conditional(isEdit) {
            defaultMinSize(260.dp)
        },
        aspectRatio = null,
        contentScale = contentScale,
        scale = scale,
        focusState = focusState,
        placeholder = placeholder,
        imageRenderer = imageRenderer,
        onFocus = { onFocus() },
        onClick = { onClick() }
    )

}