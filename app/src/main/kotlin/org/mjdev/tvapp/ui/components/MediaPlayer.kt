/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.interfaces.ItemWithVideoUri
import org.mjdev.tvapp.base.media.MediaPlayerContainer

@SuppressLint("ModifierParameter")
@TvPreview
@Composable
fun MediaPlayer(
    movie: ItemWithVideoUri? = null,
    modifier: Modifier = Modifier
) {

    MediaPlayerContainer(
        modifier = modifier.fillMaxSize(),
        uri = movie?.videoUri
    )

}