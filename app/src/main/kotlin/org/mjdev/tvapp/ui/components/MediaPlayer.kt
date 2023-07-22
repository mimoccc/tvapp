/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.StringExt.parseUri
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithUri
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.media.MediaPlayerContainer
import java.net.URL

@SuppressLint("ModifierParameter")
@TvPreview
@Composable
fun MediaPlayer(
    data: Any? = null, modifier: Modifier = Modifier
) {

    val uriToPlay: String? = when (data) {
        null -> null
        is ItemWithUri<*> -> data.uri.toString()
        is String -> data.toString()
        is URL -> data.toString()
        is Uri -> data.toString()
        else -> data.toString()
    }

    val background = when (data) {
        is ItemWithBackground<*> -> data.background
        is ItemWithImage<*> -> data.image
        is ItemWithUri<*> -> data.uri
        is String -> data.toString()
        is URL -> data.toString()
        is Uri -> data.toString()
        else -> data.toString()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black, RectangleShape),
    ) {
        // todo: does not show
        ImageAny(
            modifier = Modifier.fillMaxSize(),
            src = background,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
        MediaPlayerContainer(
            modifier = modifier.fillMaxSize(),
            uri = uriToPlay?.parseUri(),
        )
    }

}