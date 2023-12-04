/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import org.mjdev.tvlib.extensions.ComposeExt.isTV

@Composable
fun TVAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme() || isTV(),
    dynamicColors: Boolean = !isTV(),
    content: @Composable () -> Unit,
) = ThemeHelper (useDarkTheme, dynamicColors, content)