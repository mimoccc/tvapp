@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER", "unused")

package org.mjdev.tvlib.ui.toolkit

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import kotlin.math.max
import kotlin.math.min

private val CapriBlue = Color(0xFF0D47A1)
private val SkyBlue = Color(0xFF1A73E8)
private val LightBlue = Color(0xFF03A9F4)
private val Orange = Color(0xFFF4491f)
private val Rose = Color(0xFFE92635)
private val OrientRed = Color(0xffae1c27)
private val RedViolet = Color(0xFF991c37)
private val ClaretViolet = Color(0xFF740945)
private val Magenta = Color(0xFFE91E63)
private val SignalBlue = Color(0xFF4A148C)
private val AzureBlue = Color(0xFF006064)
private val MetroGreen = Color(0xFF00A300)
private val MetroGreen2 = Color(0xFF4CAF50)
private val OliveYellow = Color(0xFF8BC34A)
private val Ivory = Color(0xFFCDDC39)
private val TrafficYellow = Color(0xFFffc107)
private val DahliaYellow = Color(0xFFff9800)
private val Amber = Color(0xFFFF6F00)
private val BlackOlive = Color(0xFF383838)
private val SepiaBrown = Color(0xFF38220f)
private val UmbraGrey = Color(0xFF333333)
private val SignalWhite = Color(0xFFF2F2F2)
private val JetBlack = Color(0xFF121114)
private val TrafficBlack = Color(0xFF1D1D1E)

val Color.Companion.UmbraGrey
    get() = org.mjdev.tvlib.ui.toolkit.UmbraGrey
val Color.Companion.SignalWhite
    get() = org.mjdev.tvlib.ui.toolkit.SignalWhite
val Color.Companion.Amber
    get() = org.mjdev.tvlib.ui.toolkit.Amber
val Color.Companion.Orange
    get() = org.mjdev.tvlib.ui.toolkit.Orange
val Color.Companion.Rose
    get() = org.mjdev.tvlib.ui.toolkit.Rose
val Color.Companion.RedViolet
    get() = org.mjdev.tvlib.ui.toolkit.RedViolet
val Color.Companion.ClaretViolet
    get() = org.mjdev.tvlib.ui.toolkit.ClaretViolet
val Color.Companion.MetroGreen2
    get() = org.mjdev.tvlib.ui.toolkit.MetroGreen2
val Color.Companion.MetroGreen
    get() = org.mjdev.tvlib.ui.toolkit.MetroGreen
val Color.Companion.LightBlue
    get() = org.mjdev.tvlib.ui.toolkit.LightBlue
val Color.Companion.SkyBlue
    get() = org.mjdev.tvlib.ui.toolkit.SkyBlue
val Color.Companion.BlueLilac
    get() = SignalBlue
val Color.Companion.CapriBlue
    get() = org.mjdev.tvlib.ui.toolkit.CapriBlue
val Color.Companion.AzureBlue
    get() = org.mjdev.tvlib.ui.toolkit.AzureBlue
val Color.Companion.TrafficYellow
    get() = org.mjdev.tvlib.ui.toolkit.TrafficYellow
val Color.Companion.DahliaYellow
    get() = org.mjdev.tvlib.ui.toolkit.DahliaYellow
val Color.Companion.BlackOlive
    get() = org.mjdev.tvlib.ui.toolkit.BlackOlive
val Color.Companion.SepiaBrown
    get() = org.mjdev.tvlib.ui.toolkit.SepiaBrown
val Color.Companion.OrientRed
    get() = org.mjdev.tvlib.ui.toolkit.OrientRed
val Color.Companion.Ivory
    get() = org.mjdev.tvlib.ui.toolkit.Ivory
val Color.Companion.OliveYellow
    get() = org.mjdev.tvlib.ui.toolkit.OliveYellow
val Color.Companion.Magenta
    get() = org.mjdev.tvlib.ui.toolkit.Magenta
val Color.Companion.JetBlack
    get() = org.mjdev.tvlib.ui.toolkit.JetBlack
val Color.Companion.TrafficBlack
    get() = org.mjdev.tvlib.ui.toolkit.TrafficBlack

/**
 * Suggest Color
 * Generates a color that can be represented on provided background [Color] based on the luminance
 * @param backgroundColor - provided background color
 * @return [Color.White] if luminance < 0.3 otherwise [Color.Black]
 */

fun suggestContentColorFor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() < 0.3f) Color.White else Color.Black
}


fun Color.contrastAgainst(background: Color): Float {
    val fg = if (alpha < 1f) compositeOver(background) else this
    val fgLuminance = fg.luminance() + 0.05f
    val bgLuminance = background.luminance() + 0.05f

    return max(fgLuminance, bgLuminance) / min(fgLuminance, bgLuminance)
}


/**
 * Blend between two [Color]s using the given ratio.
 * A blend ratio of 0.0 will result in color1, 0.5 will give an even blend, 1.0 will result in color2.
 * @params: color1 – the first ARGB color
 * @param color – the second ARGB color
 * @param ratio – the blend ratio of color1 to color2
 */
fun Color.blend(color: Color, @FloatRange(from = 0.0, to = 1.0) ratio: Float): Color {
    val inverseRatio = 1 - ratio
    val a = alpha * inverseRatio + color.alpha * ratio
    val r = red * inverseRatio + color.red * ratio
    val g = green * inverseRatio + color.green * ratio
    val b = blue * inverseRatio + color.blue * ratio
    return Color(r, g, b, a, colorSpace = ColorSpaces.Srgb)
}

/**
 * Return a copy of [Color] from [hue], [saturation], and [lightness] (HSL representation).
 *
 * @param hue The color value in the range (0..360), where 0 is red, 120 is green, and
 * 240 is blue; default value is null; which makes is unaltered.
 * @param saturation The amount of [hue] represented in the color in the range (0..1),
 * where 0 has no color and 1 is fully saturated; default value is null; which makes is unaltered.
 * @param lightness A range of (0..1) where 0 is black, 0.5 is fully colored, and 1 is
 * white; default value is null; which makes is unaltered.
 */
fun Color.hsl(
    hue: Float? = null,
    saturation: Float? = null,
    lightness: Float? = null,
    alpha: Float? = null
): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(toArgb(), hsl)

    // use value or default.
    return Color.hsl(
        hue = hue ?: hsl[0],
        saturation = saturation ?: hsl[1],
        lightness = lightness ?: hsl[2],
        alpha = alpha ?: this.alpha,
    )
}