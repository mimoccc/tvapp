/*
 * Copyright (c) Milan Jurkulák 2023. 
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.ui.components.complex.FocusableBox
import org.mjdev.tvapp.base.ui.components.image.CircleImage

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun UserPic(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    src: Any? = R.drawable.milanj,
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    contentPadding: Dp = 2.dp,
    focusState: MutableState<FocusState?> = rememberFocusState(),
    onClick: () -> Unit = {}
) = FocusableBox(
    modifier = modifier.wrapContentSize(),
    focusState = focusState,
    shape = shape,
    onClick = onClick
) {

    val isEdit = isEditMode()
    CircleImage(
        modifier = modifier
            .conditional(isEdit) {
                size(64.dp)
            },
        backGroundColor = backGroundColor,
        borderSize = borderSize,
        borderColor = borderColor,
        contentPadding = contentPadding + borderSize,
        src = src,
    )

}