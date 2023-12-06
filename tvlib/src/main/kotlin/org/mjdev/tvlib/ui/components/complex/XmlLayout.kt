/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.complex

import android.annotation.SuppressLint
import android.view.View
import android.view.View.inflate
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.allViews
import org.mjdev.tvlib.annotations.Previews

@SuppressLint("PrivateResource")
@Previews
@Composable
fun XmlLayout(
    modifier: Modifier = Modifier,
    layoutId: Int = androidx.media3.ui.R.layout.exo_player_control_view,
    views: MutableMap<Int, View> = remember(layoutId) {
        mutableMapOf()
    }
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            FrameLayout(context).apply {
                inflate(
                    context,
                    layoutId,
                    this
                )
                allViews.forEach { v ->
                    if (v.id != -1) {
                        views[v.id] = v
                    }
                }
            }
        }
    )
}