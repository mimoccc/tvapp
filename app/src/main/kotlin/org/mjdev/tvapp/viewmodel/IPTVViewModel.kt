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
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.network.NetworkConnectivityService
import javax.inject.Inject
import kotlin.reflect.KClass

@Suppress("MemberVisibilityCanBePrivate")
@HiltViewModel
class IPTVViewModel @Inject constructor() : BaseViewModel() {

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

    val mediaItems: List<Any> get() = dao.movieDao.all

    val cache = mutableMapOf<KClass<*>, List<MediaItem>>()

    fun mediaItemsFor(
        data: Any?
    ): List<MediaItem> = when (data) {
        is ItemAudio -> getCachedList<ItemAudio> { localAudioCursor }
        is ItemVideo -> getCachedList<ItemVideo> { localVideoCursor }
        is ItemPhoto -> getCachedList<ItemPhoto> { localPhotoCursor }
        is Movie -> getCachedList<Movie> {
            mediaItems.filter { listItem ->
                (listItem as? Movie?)?.category == data.category
            }
        }
        else -> listOf(data.mediaItem)
    }

    private inline fun <reified T> getCachedList(creator: () -> List<*>): List<MediaItem> {
        if (!cache.containsKey(T::class)) {
            creator().let { list ->
                cache[T::class] = list.map { item -> item.mediaItem }
            }
        }
        return cache[T::class] ?: emptyList()
    }

    companion object {

        @Suppress("unused")
        fun mock(
            context: Context
        ): IPTVViewModel = IPTVViewModel().apply {
            dao = DAO(context)
            networkInfo = NetworkConnectivityService(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}