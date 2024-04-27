/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.image.ImageAny

@Previews
@Composable
fun NumberDigit(
    modifier: Modifier = Modifier,
    value: Int = 0
) {
    val optimised = value % 10
    ImageAny(
        modifier = modifier,
        src = when (optimised) {
            0 -> R.drawable.clock_0
            1 -> R.drawable.clock_1
            2 -> R.drawable.clock_2
            3 -> R.drawable.clock_3
            4 -> R.drawable.clock_4
            5 -> R.drawable.clock_5
            6 -> R.drawable.clock_6
            7 -> R.drawable.clock_7
            8 -> R.drawable.clock_8
            9 -> R.drawable.clock_9
            else -> R.drawable.clock_0
        }
    )
}