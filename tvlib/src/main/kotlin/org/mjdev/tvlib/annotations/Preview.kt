/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.annotations

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

//@Preview(
//    uiMode = UI_MODE_TYPE_TELEVISION,
//    showBackground = true,
//    device = Devices.TV_720p,
//    showSystemUi = false,
//    backgroundColor = 0xFF444444,
//    wallpaper = GREEN_DOMINATED_EXAMPLE
//)
//annotation class TvPreview

@Preview(
    name = "Landscape",
    group = "device",
    device = Devices.AUTOMOTIVE_1024p,
    showBackground = true,
    backgroundColor = 0xFF444444,
    widthDp = 800,
    heightDp = 480
)
annotation class PreviewLandscape

@Preview(
    name = "Portrait",
    group = "device",
    showBackground = true,
    device = Devices.PIXEL_7,
    backgroundColor = 0xFF444444,
    widthDp = 480,
    heightDp = 800
)
annotation class PreviewPortrait

@PreviewPortrait
@PreviewLandscape
annotation class Previews