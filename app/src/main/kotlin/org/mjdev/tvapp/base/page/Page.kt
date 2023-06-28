/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.state.ScreenState

@Suppress("LeakingThis")
open class Page {

    var navController: NavHostControllerEx? = null
    var screenState: ScreenState? = null

    @OptIn(ExperimentalFoundationApi::class)
    lateinit var pagerState: PagerState

    open val title: Any? = null
    open val icon: Any? = null

    open val backgroundColor: Color = Color.DarkGray

    open val roundRadius: Dp = 0.dp

    open val background: Shape = RoundedCornerShape(roundRadius)

    @SuppressLint("ComposableNaming")
    @TvPreview
    @Composable
    fun content() {

        screenState?.titleState?.value = title

        Column(
            Modifier
                .fillMaxSize()
                .background(backgroundColor, background)
                .touchable {
                    freeFocus()
                }
        ) {

            Content()

        }

    }

    @Composable
    open fun Content() {

        EMPTY_PAGE

    }

    override fun toString(): String = title.toString()

    companion object {

        val EMPTY_PAGE: Page
            get() = object : Page() {

                @Composable
                override fun Content() {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(

                            text = "Empty Screen",
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )

                    }

                }

            }

    }

}