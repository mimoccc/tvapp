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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ModifierExt.requestFocusOnTouch
import org.mjdev.tvlib.ui.components.complex.VerticalScrollableBox
import org.mjdev.tvlib.ui.components.text.TextAny

open class Page {

    open val title: Any? = null
    open val icon: Any? = null

    private val focusRequester by lazy {
        FocusRequester()
    }

    @SuppressLint("ComposableNaming")
    @Previews
    @Composable
    @CallSuper
    fun content() {
        Column(
            Modifier.fillMaxSize()
        ) {
            VerticalScrollableBox(
                modifier = Modifier
                    .fillMaxSize()
                    .requestFocusOnTouch(
                        focusRequester = focusRequester
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .focusRequester(focusRequester),
                    contentAlignment = Alignment.Center
                ) {
                    Content()
                }
            }
        }
    }

    @Composable
    open fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextAny(
                modifier = Modifier,
                text = title ?: "Empty Page",
                textAlign = TextAlign.Center,
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