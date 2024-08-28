/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.viewmodel

import android.content.Context
import androidx.media3.common.MediaItem
import org.kodein.di.DI
import org.kodein.di.instance
import org.mjdev.tvapp.app.Application
import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.network.NetworkConnectivityService

class IPTVViewModel(context: Context) : BaseViewModel(context) {

    val dao: DAO by instance()
    val networkInfo: NetworkConnectivityService by instance()

    private val localAudioCursor: AudioCursor by instance()
    private val localVideoCursor: VideoCursor by instance()
    private val localPhotoCursor: PhotoCursor by instance()

    private val mediaItems: List<Any> get() = dao.movieDao.all

    fun mediaItemsFor(
        data: Any?
    ): List<MediaItem> = when (data) {
        is ItemAudio -> localAudioCursor.map { item -> item.mediaItem }
        is ItemVideo -> localVideoCursor.map { item -> item.mediaItem }
        is ItemPhoto -> localPhotoCursor.map { item -> item.mediaItem }
        is Media -> mediaItems.filter { listItem ->
            (listItem as? Media?)?.category.contentEquals(data.category)
        }.map { item -> item.mediaItem }

        else -> listOf(data.mediaItem)
    }

    override fun mockDI(context: Context): DI = Application.getDI(context)

}