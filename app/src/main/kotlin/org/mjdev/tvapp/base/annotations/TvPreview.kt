/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.annotations

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    device= Devices.TV_720p
)
annotation class TvPreview