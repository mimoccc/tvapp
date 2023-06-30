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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.tv.material3.MaterialTheme
import kotlinx.coroutines.delay
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.Screen
import org.mjdev.tvapp.base.ui.components.icon.IconAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

class SplashScreen : Screen() {

    override val showOnce: Boolean = true
    override val backgroundColor: Color = Color.Black
    override val immersive: Boolean = true

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    @Composable
    override fun Compose(
        navController: NavHostControllerEx,
        backStackEntry: NavBackStackEntry?,
        args: Map<String, Any?>
    ) {

        val isEdit = isEditMode()
        val scale = remember { Animatable(if (isEdit) 1f else 0f) }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)
            navController.addOnDestinationChangedListener(this@SplashScreen)
            navController.open<MainScreen>()
        }

        Box(
            modifier = Modifier
                .scale(scale.value)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconAny(
                modifier = Modifier.size(128.dp),
                src = Icons.Sharp.Tv,
                tint = Color.White.copy(alpha = 0.5f)
            )
            TextAny(
                text = R.string.app_name,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(5f, 5f),
                        blurRadius = 10f
                    )
                ),
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
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.5f)
            )
        }

    }

}