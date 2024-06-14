/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.Context
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
import kotlin.reflect.KClass

class DetailViewModel(context: Context) : BaseViewModel(context) {

    private val cache = mutableMapOf<KClass<*>, List<*>>()

    val dao: DAO by instance()

    private val localAudioCursor: AudioCursor by instance()
    private val localVideoCursor: VideoCursor by instance()
    private val localPhotoCursor: PhotoCursor by instance()

    // todo : improve on changes
    private inline fun <reified T> getCachedList(creator: () -> List<T>): List<*> {
        if (!cache.containsKey(T::class)) {
            creator().let { list ->
                cache[T::class] = list
            }
        }
        return cache[T::class] ?: emptyList<T>()
    }

    fun mediaItemsFor(
        data: Any?
    ): List<Any?> = when (data) {
        is ItemAudio -> getCachedList { localAudioCursor }
        is ItemVideo -> getCachedList { localVideoCursor }
        is ItemPhoto -> getCachedList { localPhotoCursor }
        is Media -> getCachedList { dao.allMediaItems }
        else -> listOf(data.mediaItem)
    }

    override fun mockDI(context: Context): DI = Application.getDI(context)

}
