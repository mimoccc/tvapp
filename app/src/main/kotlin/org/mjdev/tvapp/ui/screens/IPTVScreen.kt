/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("UnusedReceiverParameter")

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.navigation.navArgument
import org.mjdev.gradle.annotations.CreateScreenShot
import org.mjdev.tvapp.R
import org.mjdev.tvapp.app.Application
import org.mjdev.tvapp.sync.SyncAdapter.Companion.pauseSyncUntilGone
import org.mjdev.tvapp.viewmodel.IPTVViewModel
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.KodeinExt.rememberInstance
import org.mjdev.tvlib.extensions.ListExt.indexOf
import org.mjdev.tvlib.extensions.MediaItemExt.uri
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.media.MediaPlayerContainer
import org.mjdev.tvlib.ui.components.media.MediaPlayerState.Companion.rememberMediaPlayerState
import timber.log.Timber

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class IPTVScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_iptv

    override val menuIcon: ImageVector get() = Icons.Filled.Tv
    override val immersive: Boolean = true
    override val showOnce: Boolean = true

    override val routeArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @CreateScreenShot
    @Previews
    @Composable
    override fun Content() {
        val viewModel: IPTVViewModel by rememberInstance {
            Application.getDI(LocalContext.current)
        }
        val data: Any? = args[data]
        val dataList: List<MediaItem> = remember(data) {
            viewModel.mediaItemsFor(data)
        }
        val index = remember(data) {
            dataList.indexOf<Any?> { item -> item.uri == data.uri }
        }

        pauseSyncUntilGone()

//        Gallery (
//            modifier = Modifier.fillMaxSize(),
//            list = dataList,
//            index = index,
//            customContentViewer = { _, type, _ ->
        MediaPlayerContainer(
            modifier = Modifier.fillMaxSize(),
//                    visible = (type == ItemType.Video || type == ItemType.Audio),
            state = rememberMediaPlayerState(
                items = dataList,
                itemToPlay = index,
                autoPlay = true,
                playNextOnError = false,
                onError = { error ->
                    Timber.e(error)
                    true
                }
            )
        )
//            }
//        )

    }

}
