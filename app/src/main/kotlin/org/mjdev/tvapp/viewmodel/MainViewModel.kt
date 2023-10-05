/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.ComponentName
import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.activity.MainActivity
import org.mjdev.tvapp.data.local.Country
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.database.DAO.Companion.findById
import org.mjdev.tvlib.extensions.ListExt.asMap
import org.mjdev.tvlib.helpers.apps.appsManager
import org.mjdev.tvlib.interfaces.ItemWithImage.Companion.hasImage
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    context: Context
) : BaseViewModel() {

    @Suppress("PrivatePropertyName")
    private val ITEMS_FROM_CATEGORY = 3

    private val excludedActivity = ComponentName(
        BuildConfig.APPLICATION_ID,
        MainActivity::class.java.name
    )

    @Inject
    lateinit var dao: DAO

    @Inject
    lateinit var localAudioCursor: AudioCursor

    @Inject
    lateinit var localVideoCursor: VideoCursor

    @Inject
    lateinit var localPhotoCursor: PhotoCursor

    // todo : move to cursor
    val apps = appsManager(context, excludedActivity).stateInViewModel()

    // todo : move to cursor
    val messages = channelFlow {
        send(dao.messagesDao.all)
    }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo latest items, anr removal
    // todo : move to cursor
    val featuredMovieList = flow {
        mutableListOf<Any?>().apply {
            addAll(localAudioCursor.takeIf(ITEMS_FROM_CATEGORY) {
                hasImage
            })
            addAll(localVideoCursor.takeIf(ITEMS_FROM_CATEGORY) {
                hasImage
            })
            addAll(localPhotoCursor.takeIf(ITEMS_FROM_CATEGORY) {
                hasImage
            })
            addAll(dao.movieDao.all.take(ITEMS_FROM_CATEGORY))
        }.also { result ->
            emit(result)
        }
    }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo improve anr
    // todo : move to cursor
    val movieList = flow {
        dao.movieDao.all.sortedBy { m ->
            m.category
        }.asMap { m ->
            Pair(m.category, m)
        }.also { map ->
            emit(map)
        }
    }.flowOn(Dispatchers.IO).stateInViewModel()

    // todo in sync adapter
    // todo : move to cursor
    val countryList: StateFlow<List<Country>> = flow {
        emit(dao.countriesDao.all)
    }.flowOn(Dispatchers.IO).stateInViewModel()

    fun findMovie(
        id: Long?,
        findBlock: (movie: Movie?) -> Unit
    ): Movie? = dao.movieDao.findById(id).apply(findBlock)

    companion object {

        @Suppress("unused")
        fun mock(
            context: Context
        ): MainViewModel = MainViewModel(context).apply {
            dao = DAO(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}
