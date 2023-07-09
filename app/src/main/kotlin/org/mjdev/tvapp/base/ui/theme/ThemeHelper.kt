/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.tv.material3.ColorScheme
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ThemeHelper(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit,
) {
//    val context = LocalContext.current
    val colorScheme: ColorScheme = when {
//        dynamicColor -> {
//            if (useDarkTheme)
//                dynamicDarkColorScheme(context)
//            else
//                dynamicLightColorScheme(context)
//        }

        else -> {
            if (useDarkTheme)
                darkColorScheme()
            else
                lightColorScheme()
        }
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )

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
    val detailsTitleTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineMedium
    val detailsTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineSmall

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