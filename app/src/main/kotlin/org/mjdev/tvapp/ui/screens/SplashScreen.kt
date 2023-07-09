/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.NavControllerExt.openAsTop
import org.mjdev.tvapp.base.screen.Screen
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.base.ui.components.text.TextWithShadow

class SplashScreen : Screen() {

    override val showOnce: Boolean = true
    override val backgroundColor: Color = Color.Black
    override val immersive: Boolean = true

    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val isEdit = isEditMode()
        val scale = remember { Animatable(if (isEdit) 1f else 0f) }
        val brush = Brush.radialGradient(
            colors = listOf(Color.Black, Color.Black, Color.DarkGray, Color.Black),
        )

        Box(
            modifier = Modifier
                .scale(scale.value)
                .background(brush, RectangleShape)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextWithShadow(
                text = R.string.app_name,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(10.dp),
                shadowColor = Color.DarkGray,
                shadowSize = 18.dp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    0.dp,
                    0.dp,
                    0.dp,
                    4.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextAny(
                text = R.string.author,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.5f)
            )
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)
            navController?.openAsTop<MainScreen>()
        }

    }

}