package org.mjdev.tvlib.ui.toolkit.blur

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.FloatRange
import androidx.compose.ui.Modifier

@Suppress("UNUSED_PARAMETER", "unused")
@TargetApi(Build.VERSION_CODES.S_V2)
internal fun Modifier.legacyContentBlur(
    @FloatRange(0.0, 25.0) radius: Float = 25f,
    @FloatRange(0.0, 1.0, fromInclusive = false) downSample: Float = 1.0f
): Modifier {
//    this then TODO()
    return this
}