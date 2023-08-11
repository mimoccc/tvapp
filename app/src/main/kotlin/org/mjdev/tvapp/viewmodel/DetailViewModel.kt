/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.viewmodel

import android.content.Context
import androidx.media3.common.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.network.NetworkConnectivityServiceImpl
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate")
@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var dao: DAO

    @Inject
    lateinit var networkInfo: NetworkConnectivityService

    @Inject
    lateinit var localAudioCursor: AudioCursor

    @Inject
    lateinit var localVideoCursor: VideoCursor

    @Inject
    lateinit var localPhotoCursor: PhotoCursor

    val mediaItems: List<MediaItem> by lazy {
        dao.movieDao.all.map { item ->
            item.mediaItem
        }
    }

    fun mediaItemsFor(
        data: Any?
    ): List<Any?> = when (data) {
        is ItemAudio -> localAudioCursor
        is ItemVideo -> localVideoCursor
        is ItemPhoto -> localPhotoCursor
        is Movie -> mediaItems
        else -> listOf(data)
    }

    companion object {

        @Suppress("unused")
        fun mockDetailViewModel(
            context: Context
        ): DetailViewModel = DetailViewModel().apply {
            dao = DAO(context)
            networkInfo = NetworkConnectivityServiceImpl(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}