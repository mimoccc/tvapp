package org.mjdev.tvlib.ui.toolkit

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode

fun Modifier.noise(
    alpha: Float
) = this then visualEffect(ImageBrush.NoiseBrush, alpha, true, BlendMode.Hardlight)
