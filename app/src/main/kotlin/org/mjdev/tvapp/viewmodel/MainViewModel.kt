/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.kodein.di.DI
import org.kodein.di.instance
import org.mjdev.tvapp.app.Application
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvlib.extensions.ListExt.asMap
import org.mjdev.tvlib.extensions.ListExt.takeIf
import org.mjdev.tvlib.interfaces.ItemWithImage.Companion.hasImage

class MainViewModel(context: Context) : BaseViewModel(context) {

    @Suppress("PrivatePropertyName")
    private val ITEMS_FROM_CATEGORY = 3

    val dao: DAO by instance()
    val localAudioCursor: AudioCursor by instance()
    val localVideoCursor: VideoCursor by instance()
    val localPhotoCursor: PhotoCursor by instance()

    // todo : move to cursor
    val messages
        get() = channelFlow {
            send(dao.messagesDao.all)
        }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo latest items, anr removal
    // todo : move to cursor
    val featuredMovieList
        get() = flow {
            mutableListOf<Any?>().apply {
                addAll(localAudioCursor.takeIf(ITEMS_FROM_CATEGORY) { hasImage })
                addAll(localVideoCursor.takeIf(ITEMS_FROM_CATEGORY) { hasImage })
                addAll(localPhotoCursor.takeIf(ITEMS_FROM_CATEGORY) { hasImage })
                addAll(dao.movieDao.all.takeIf(ITEMS_FROM_CATEGORY) { hasImage })
            }.also { result ->
                emit(result)
            }
        }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo improve anr
    // todo : move to cursor
    val movieList
        get() = flow {
            dao.movieDao.all.sortedBy { m ->
                m.category
            }.asMap { m ->
                Pair(m.category ?: "-", m)
            }.also { map ->
                emit(map)
            }
        }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo in sync adapter
    // todo : move to cursor
    val countryList
        get() = flow {
            emit(dao.countriesDao.all)
        }.flowOn(Dispatchers.IO).stateInViewModel()

    override fun mockDI(context: Context): DI = Application.getDI(context)

}
