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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.NavControllerExt.openAsTop
import org.mjdev.tvlib.permission.rememberPermissionManager
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("UNUSED_VARIABLE")
class SplashScreen : Screen() {

    override val showOnce: Boolean = true
    override val backgroundColor: Color = Color.Transparent
    override val immersive: Boolean = true

    @OptIn(ExperimentalPermissionsApi::class)
    @TvPreview
    @Composable
    override fun ComposeScreen() {

        val isEdit = isEditMode()
        val scale = remember { Animatable(if (isEdit) 1f else 0f) }
        val permissionManager = rememberPermissionManager()
        val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val progress = animateLottieCompositionAsState(composition.value)

        Box(
            modifier = Modifier
                .scale(scale.value)
                .background(backgroundColor, RectangleShape)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                modifier = Modifier.padding(120.dp) ,
                composition = composition.value,
                progress = {
                    progress.value
                },
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
                text = R.string.text_author,
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
            delay(1000L)
            navController?.openAsTop<MainScreen>()
        }
    }

}