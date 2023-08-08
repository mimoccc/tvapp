/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester

@Suppress("IllegalExperimentalApiUsage")
@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@TvPreview
@Composable
fun DrawerSheet(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = remember { DrawerState() },
    sizeAnimationFinishedListener: ((initialValue: IntSize, targetValue: IntSize) -> Unit)? = null,
    content: @Composable (DrawerValue) -> Unit = {
        Box(
            modifier = Modifier
                .width(200.dp)
                .background(Color.LightGray, RectangleShape)
        )
    }
) {
    val initializationComplete = remember { mutableStateOf(false) }
    val focusState = remember { mutableStateOf<FocusState?>(null) }
    val focusRequester = rememberFocusRequester()
    LaunchedEffect(
        drawerState.currentValue
    ) {
        if (drawerState.currentValue == DrawerValue.Open && focusState.value?.hasFocus == false) {
            focusRequester.requestFocus()
        }
        initializationComplete.value = true
    }
    Box(
        modifier = Modifier
            .focusRequester(focusRequester)
            .animateContentSize(
                finishedListener = sizeAnimationFinishedListener
            )
            .fillMaxHeight()
            .then(modifier)
            .onFocusChanged { state ->
                focusState.value = state
                if (initializationComplete.value) {
                    drawerState.setValue(
                        if (state.hasFocus) DrawerValue.Open
                        else DrawerValue.Closed
                    )
                }
            }
            .focusGroup()
    ) {
        content.invoke(drawerState.currentValue)
    }
}