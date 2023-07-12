/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.annotations

import android.content.res.Configuration.UI_MODE_TYPE_TELEVISION
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE

@Preview(
    uiMode = UI_MODE_TYPE_TELEVISION,
    showBackground = true,
    device = Devices.TV_720p,
    showSystemUi = false,
    backgroundColor = 0xFF444444,
    wallpaper = GREEN_DOMINATED_EXAMPLE
)
annotation class TvPreview