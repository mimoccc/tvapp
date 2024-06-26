/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("MoveVariableDeclarationIntoWhen")

package org.mjdev.tvapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.navArgument
import org.mjdev.tvapp.R
import org.mjdev.tvapp.app.Application
import org.mjdev.tvapp.sync.SyncAdapter.Companion.pauseSyncUntilGone
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.KodeinExt.rememberInstance
import org.mjdev.tvlib.extensions.ListExt.indexOf
import org.mjdev.tvlib.extensions.MediaItemExt.uri
import org.mjdev.tvlib.navigation.AnyType
import org.mjdev.tvlib.screen.Screen
import org.mjdev.tvlib.ui.components.gallery.Gallery

class GalleryScreen : Screen() {

    private val data = "data"

    override val title = R.string.title_movie_detail

    override val menuIcon: ImageVector get() = Icons.Filled.Info

    override val immersive: Boolean = true

    override val routeArgs = listOf(
        navArgument(data) {
            nullable = true
            type = AnyType
        }
    )

    @Previews
    @Composable
    override fun Content() {
        val viewModel: DetailViewModel by rememberInstance {
            Application.getDI(LocalContext.current)
        }
        val data: Any? = remember(args) { args[data] }
        val dataList = remember(data) {
            viewModel.mediaItemsFor(data)
        }

        pauseSyncUntilGone()

        Gallery(
            modifier = Modifier.fillMaxSize(),
            list = dataList,
            index = dataList.indexOf<Any?> { item -> item.uri == data.uri },
        )
    }

}