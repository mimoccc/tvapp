/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvlib.ui.toolkit.blur.legacyBackgroundBlur
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.text.TextAny
import org.mjdev.tvlib.ui.toolkit.ImageBrush
import org.mjdev.tvlib.ui.toolkit.shadow.shadow
import org.mjdev.tvlib.ui.toolkit.visualEffect

@Previews
@Composable
fun BlurDialog(
    modifier: Modifier = Modifier,
    title: String = "Title",
    backgroundColor: Color = Color.DarkGray,
    textColor: Color = Color.White,
    roundCorners: Dp = 8.dp,
    borderSize: Dp = 2.dp,
    backgroundAlpha: Float = 0.9f,
    noiseAlpha: Float = 0.5f,
    keyHandler: (Key) -> Boolean = { false },
    visibleState: MutableState<Boolean> = remember { mutableStateOf(true) },
    content: @Composable () -> Unit = {}
) {
    val handleKey: (KeyEvent) -> Boolean = { keyEvent ->
        val key = keyEvent.key
        if (!keyHandler(key)) {
            when (key) {
                Key.Escape, Key.Back, Key.Backspace, Key.DirectionCenter -> {
                    visibleState.value = false
                    true
                }

                else -> false
            }
        } else true
    }
    if (visibleState.value) {
        Column(
            modifier = modifier
                .border(borderSize, backgroundColor, RoundedCornerShape(roundCorners))
                .padding(borderSize)
                .background(backgroundColor.copy(backgroundAlpha))
                .legacyBackgroundBlur(
                    radius = 15f,
                    downSample = 0.6f
                )
                .visualEffect(
                    ImageBrush.NoiseBrush,
                    noiseAlpha,
                    true,
                    BlendMode.Hardlight
                )
                .clip(RoundedCornerShape(roundCorners - 1.dp))
                .shadow(
                    shape = MaterialTheme.shapes.small,
                    lightShadowColor = Color.Gray.copy(0.5f),
                    darkShadowColor = Color.Black.copy(0.3f),
                    elevation = 4.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures {
                        visibleState.value = false
                    }
                }
                .onKeyEvent {
                    handleKey(it)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor.copy(0.98f))
                    .padding(8.dp)
            ) {
                TextAny(
                    text = title,
                    color = textColor
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }
        }
    }
}