/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.page

import android.annotation.SuppressLint
import androidx.annotation.CallSuper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.ModifierExt.requestFocusOnTouch
import org.mjdev.tvlib.ui.components.complex.VerticalScrollableBox
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.text.TextAny

@Suppress("LeakingThis")
open class Page {

    var navController: NavHostControllerEx? = null

    open val title: Any? = null
    open val icon: Any? = null
    open val menuGravity = MenuItem.Gravity.Center
    open val backgroundColor: Color = Color.DarkGray
    open val roundRadius: Dp = 0.dp
    open val background: Shape = RoundedCornerShape(roundRadius)

    @SuppressLint("ComposableNaming")
    @TvPreview
    @Composable
    @CallSuper
    fun content() {
        val focusRequester = rememberFocusRequester(title)
        Column(
            Modifier.fillMaxSize().recomposeHighlighter()
        ) {
            VerticalScrollableBox(
                modifier = Modifier.recomposeHighlighter()
                    .fillMaxSize()
                    .background(backgroundColor, background)
                    .requestFocusOnTouch(focusRequester),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier.recomposeHighlighter()
                        .fillMaxSize()
                        .focusRequester(focusRequester)
                ) {
                    Content()
                }
            }
        }
    }

    @Composable
    open fun Content() {
        Column(
            modifier = Modifier.fillMaxSize().recomposeHighlighter(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextAny(
                text = title ?: "Empty Page",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }

    override fun toString(): String = this::class.simpleName ?: "Page[]"

    companion object {

        val EMPTY_PAGE = Page()

    }

}