/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.mjdev.tvlib.annotations.TvPreview

// todo new ui
@TvPreview
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun PlayerControlView(
    modifier: Modifier = Modifier,
    state: MediaPlayerState = MediaPlayerState.rememberMediaPlayerState(),
) {
//    val isEdit = isEditMode()
//    val infoVisible = remember { mutableStateOf(isEdit) }
//    AndroidView(
//        modifier = modifier.conditional(isEdit) {
//            fillMaxSize()
//        },
//        factory = { context ->
//            PlayerControlView(context).apply {
//                addVisibilityListener { visibility ->
//                    infoVisible.value = (visibility == View.VISIBLE)
//                }
//                setPlayer(player)
//            }
//        }
//    )
//    ItemInfo(
//        src = item,
//        infoVisible = infoVisible
//    )
}