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
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.module.MainModule
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.network.NetworkConnectivityService
import kotlin.reflect.KClass

class IPTVViewModel (
    context: Context
) : BaseViewModel(context) {

    val dao: DAO by instance()
    val networkInfo: NetworkConnectivityService by instance()

    private val localAudioCursor: AudioCursor by instance()
    private val localVideoCursor: VideoCursor by instance()
    private val localPhotoCursor: PhotoCursor by instance()

    private val mediaItems: List<Any> get() = dao.movieDao.all
    private val cache = mutableMapOf<KClass<*>, List<MediaItem>>()

    fun mediaItemsFor(
        data: Any?
    ): List<MediaItem> = when (data) {
        is ItemAudio -> getCachedList<ItemAudio> { localAudioCursor }
        is ItemVideo -> getCachedList<ItemVideo> { localVideoCursor }
        is ItemPhoto -> getCachedList<ItemPhoto> { localPhotoCursor }
        is Media -> getCachedList<Media> {
            mediaItems.filter { listItem ->
                (listItem as? Media?)?.category == data.category
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

    override fun mockDI(context: Context): DI = DI.lazy(allowSilentOverride = true) {
        bind<Context>() with singleton { context }
        bind<DAO>() with singleton { DAO(context) }
        import(MainModule)
    }

}