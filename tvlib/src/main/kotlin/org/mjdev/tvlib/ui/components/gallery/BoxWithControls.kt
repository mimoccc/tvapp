/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gallery

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.mjdev.tvlib.extensions.ComposeExt.onKey
import org.mjdev.tvlib.extensions.GlobalExt.toggle
import org.mjdev.tvlib.extensions.ModifierExt.swipeGestures
import org.mjdev.tvlib.ui.components.image.ImageColoredBackground

@SuppressLint("InlinedApi")
@Composable
fun BoxWithControls(
    modifier: Modifier = Modifier,
    src: Any? = null,
    contentAlignment: Alignment = Alignment.BottomCenter,
    controlsState: MutableState<Boolean> = remember { mutableStateOf(false) },
    onTap: () -> Unit = { controlsState.toggle() },
    controls: @Composable (
        src: Any?,
        bckColor: Color,
        controlsState: MutableState<Boolean>
    ) -> Unit = { _, _, _ -> },
    content: @Composable (
        src: Any?,
        bckColor: Color,
        controlsState: MutableState<Boolean>
    ) -> Unit
) = ImageColoredBackground(
    modifier = modifier
        .swipeGestures(
            onTap = { onTap() }
        )
        .onKey(android.view.KeyEvent.KEYCODE_DPAD_DOWN) {
            controlsState.value = false
        }
        .onKey(android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN) {
            controlsState.value = false
        }
        .onKey(android.view.KeyEvent.KEYCODE_DPAD_UP) {
            controlsState.value = true
        }
        .onKey(android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP) {
            controlsState.value = true
        },
    image = src,
    contentAlignment = contentAlignment,
) { bckColor ->
    content(src, bckColor, controlsState)
    if (controlsState.value) {
        controls(src, bckColor, controlsState)
    }
}