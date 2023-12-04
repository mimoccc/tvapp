/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.ui.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.tv.material3.ColorScheme
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme
import org.mjdev.tvlib.extensions.ComposeExt.isTV

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ThemeHelper(
    useDarkTheme: Boolean = isSystemInDarkTheme() || isTV(),
    dynamicColors: Boolean = !isTV(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val view = LocalView.current
    val activity = context as? Activity
    val window = activity?.window
    val isEditMode = view.isInEditMode

    val colorScheme: ColorScheme =
        if (useDarkTheme) {
            if (dynamicColors && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)) {
                dynamicDarkColorScheme(context)
            } else {
                darkColorScheme()
            }
        } else {
            if (dynamicColors && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)) {
                dynamicLightColorScheme(context)
            } else {
                lightColorScheme()
            }
        }

    if (!isEditMode) {
        SideEffect {
            window?.apply {
                navigationBarColor = Color.Transparent.toArgb()
                statusBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = !useDarkTheme
                    isAppearanceLightNavigationBars = !useDarkTheme
                }
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = {
            CompositionLocalProvider(
                LocalIndication provides NoRipple,
                content = content,
            )
        },
    )

}

private object NoRipple : Indication, IndicationInstance {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource) = this
    override fun ContentDrawScope.drawIndication() = drawContent()
}

val LocalCardShape = staticCompositionLocalOf<Shape> {
    RoundedCornerShape(8.dp)
}

val LocalThemeProvider = compositionLocalOf {
    ThemeProvider()
}

// todo
@Suppress("MemberVisibilityCanBePrivate")
class ThemeProvider {

    val detailTextPaddingHorizontal: Dp = 48.dp
    val detailTextPaddingVertical: Dp = 32.dp
    val detailsTextColor: Color = Color.White
    val detailTextBackgroundColor: Color = Color.Black

    @OptIn(ExperimentalTvMaterial3Api::class)
    val detailsTitleTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineSmall

    @OptIn(ExperimentalTvMaterial3Api::class)
    val detailsTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall

    val headerTitleTextSize: TextUnit = 0.sp
    val headerTextSize: TextUnit = 0.sp
    val headerClockTextSize: TextUnit = 0.sp

    val backgroundColorDefault = Color.DarkGray
    val backgroundColorMenu = Color(0xff202020)

    val textColorDefault = Color.White

    val roundCornerSize: Dp = 16.dp

    val backgroundShape: Shape = RoundedCornerShape(roundCornerSize)

}

@Suppress("unused")
object ThemeHelper {

    val cardShape: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalCardShape.current

    val themeProvider
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeProvider.current

}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalTvMaterial3Api::class)
fun dynamicDarkColorScheme(context: Context): ColorScheme {
    val tonalPalette = dynamicTonalPalette(context)
    return darkColorScheme(
        primary = tonalPalette.primary80,
        onPrimary = tonalPalette.primary20,
        primaryContainer = tonalPalette.primary30,
        onPrimaryContainer = tonalPalette.primary90,
        inversePrimary = tonalPalette.primary40,
        secondary = tonalPalette.secondary80,
        onSecondary = tonalPalette.secondary20,
        secondaryContainer = tonalPalette.secondary30,
        onSecondaryContainer = tonalPalette.secondary90,
        tertiary = tonalPalette.tertiary80,
        onTertiary = tonalPalette.tertiary20,
        tertiaryContainer = tonalPalette.tertiary30,
        onTertiaryContainer = tonalPalette.tertiary90,
        background = tonalPalette.neutral10,
        onBackground = tonalPalette.neutral90,
        surface = tonalPalette.neutral10,
        onSurface = tonalPalette.neutral90,
        surfaceVariant = tonalPalette.neutralVariant30,
        onSurfaceVariant = tonalPalette.neutralVariant80,
        inverseSurface = tonalPalette.neutral90,
        inverseOnSurface = tonalPalette.neutral20,
//        outline = tonalPalette.neutralVariant60,
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalTvMaterial3Api::class)
fun dynamicLightColorScheme(context: Context): ColorScheme {
    val tonalPalette = dynamicTonalPalette(context)
    return lightColorScheme(
        primary = tonalPalette.primary40,
        onPrimary = tonalPalette.primary100,
        primaryContainer = tonalPalette.primary90,
        onPrimaryContainer = tonalPalette.primary10,
        inversePrimary = tonalPalette.primary80,
        secondary = tonalPalette.secondary40,
        onSecondary = tonalPalette.secondary100,
        secondaryContainer = tonalPalette.secondary90,
        onSecondaryContainer = tonalPalette.secondary10,
        tertiary = tonalPalette.tertiary40,
        onTertiary = tonalPalette.tertiary100,
        tertiaryContainer = tonalPalette.tertiary90,
        onTertiaryContainer = tonalPalette.tertiary10,
        background = tonalPalette.neutral99,
        onBackground = tonalPalette.neutral10,
        surface = tonalPalette.neutral99,
        onSurface = tonalPalette.neutral10,
        surfaceVariant = tonalPalette.neutralVariant90,
        onSurfaceVariant = tonalPalette.neutralVariant30,
        inverseSurface = tonalPalette.neutral20,
        inverseOnSurface = tonalPalette.neutral95,
//        outline = tonalPalette.neutralVariant50,
    )
}

object ColorResourceHelper {
    @RequiresApi(Build.VERSION_CODES.S)
    fun getColor(context: Context, @ColorRes id: Int): Color {
        return Color(context.resources.getColor(id, context.theme))
    }
}

class TonalPalette(
    val neutral100: Color,
    val neutral99: Color,
    val neutral95: Color,
    val neutral90: Color,
    val neutral80: Color,
    val neutral70: Color,
    val neutral60: Color,
    val neutral50: Color,
    val neutral40: Color,
    val neutral30: Color,
    val neutral20: Color,
    val neutral10: Color,
    val neutral0: Color,
    val neutralVariant100: Color,
    val neutralVariant99: Color,
    val neutralVariant95: Color,
    val neutralVariant90: Color,
    val neutralVariant80: Color,
    val neutralVariant70: Color,
    val neutralVariant60: Color,
    val neutralVariant50: Color,
    val neutralVariant40: Color,
    val neutralVariant30: Color,
    val neutralVariant20: Color,
    val neutralVariant10: Color,
    val neutralVariant0: Color,
    val primary100: Color,
    val primary99: Color,
    val primary95: Color,
    val primary90: Color,
    val primary80: Color,
    val primary70: Color,
    val primary60: Color,
    val primary50: Color,
    val primary40: Color,
    val primary30: Color,
    val primary20: Color,
    val primary10: Color,
    val primary0: Color,
    val secondary100: Color,
    val secondary99: Color,
    val secondary95: Color,
    val secondary90: Color,
    val secondary80: Color,
    val secondary70: Color,
    val secondary60: Color,
    val secondary50: Color,
    val secondary40: Color,
    val secondary30: Color,
    val secondary20: Color,
    val secondary10: Color,
    val secondary0: Color,
    val tertiary100: Color,
    val tertiary99: Color,
    val tertiary95: Color,
    val tertiary90: Color,
    val tertiary80: Color,
    val tertiary70: Color,
    val tertiary60: Color,
    val tertiary50: Color,
    val tertiary40: Color,
    val tertiary30: Color,
    val tertiary20: Color,
    val tertiary10: Color,
    val tertiary0: Color,
)

@RequiresApi(Build.VERSION_CODES.S)
fun dynamicTonalPalette(context: Context): TonalPalette = TonalPalette(
    neutral100 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_0),
    neutral99 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_10),
    neutral95 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_50),
    neutral90 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_100),
    neutral80 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_200),
    neutral70 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_300),
    neutral60 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_400),
    neutral50 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_500),
    neutral40 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_600),
    neutral30 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_700),
    neutral20 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_800),
    neutral10 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_900),
    neutral0 = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_1000),
    neutralVariant100 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_0),
    neutralVariant99 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_10),
    neutralVariant95 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_50),
    neutralVariant90 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_100),
    neutralVariant80 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_200),
    neutralVariant70 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_300),
    neutralVariant60 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_400),
    neutralVariant50 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_500),
    neutralVariant40 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_600),
    neutralVariant30 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_700),
    neutralVariant20 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_800),
    neutralVariant10 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_900),
    neutralVariant0 = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_1000),
    primary100 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_0),
    primary99 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_10),
    primary95 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_50),
    primary90 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_100),
    primary80 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_200),
    primary70 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_300),
    primary60 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_400),
    primary50 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_500),
    primary40 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_600),
    primary30 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_700),
    primary20 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_800),
    primary10 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_900),
    primary0 = ColorResourceHelper.getColor(context, android.R.color.system_accent1_1000),
    secondary100 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_0),
    secondary99 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_10),
    secondary95 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_50),
    secondary90 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_100),
    secondary80 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_200),
    secondary70 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_300),
    secondary60 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_400),
    secondary50 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_500),
    secondary40 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_600),
    secondary30 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_700),
    secondary20 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_800),
    secondary10 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_900),
    secondary0 = ColorResourceHelper.getColor(context, android.R.color.system_accent2_1000),
    tertiary100 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_0),
    tertiary99 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_10),
    tertiary95 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_50),
    tertiary90 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_100),
    tertiary80 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_200),
    tertiary70 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_300),
    tertiary60 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_400),
    tertiary50 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_500),
    tertiary40 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_600),
    tertiary30 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_700),
    tertiary20 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_800),
    tertiary10 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_900),
    tertiary0 = ColorResourceHelper.getColor(context, android.R.color.system_accent3_1000),
)