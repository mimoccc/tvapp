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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.R
import org.mjdev.tvapp.activity.MainActivity
import org.mjdev.tvlib.extensions.ListExt.asMap
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.data.local.Movie_
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvlib.helpers.apps.appsManager
import org.mjdev.tvlib.network.NetworkConnectivityServiceImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    context: Context
) : BaseViewModel() {

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

    private val noCategoryString by lazy {
        context.getString(R.string.no_category)
    }

    val apps = appsManager(
        context, ComponentName(
            BuildConfig.APPLICATION_ID,
            MainActivity::class.java.name
        )
    ).stateInViewModel()

    val messages: StateFlow<List<Message>> = channelFlow {
        send(dao.messagesDao.all)
    }.stateInViewModel()

    val featuredMovieList: StateFlow<List<Any?>> = channelFlow {
        mutableListOf<Any?>().apply {
            // todo latest
            addAll(dao.movieDao.query().build().find(0, 8))
            if (localAudioCursor.count > 0) {
                add(localAudioCursor.getData(0))
            }
            if (localVideoCursor.count > 0) {
                add(localVideoCursor.getData(0))
            }
            if (localPhotoCursor.count > 0) {
                add(localPhotoCursor.getData(0))
            }
        }.also { result ->
            send(result)
        }
    }.stateInViewModel()

    val movieList: StateFlow<Map<String, List<Movie>>> = flow {
        dao.movieDao.all.map { movie ->
            movie.apply {
                category = category ?: noCategoryString
            }
        }.sortedBy { m ->
            m.category
        }.asMap { m ->
            Pair(m.category, m)
        }.also { map ->
            emit(map)
        }
    }.stateInViewModel()

    val countryList: StateFlow<List<String>> = flow {
        dao.movieDao.all.filter { it.country != null }.map {
            it.country.toString()
        }.distinct().sortedBy {
            it
        }.also {
            emit(it)
        }
    }.stateInViewModel()

    fun findMovie(
        id: Long?,
        findBlock: (movie: Movie?) -> Unit
    ): Movie? = dao.movieDao.query()
        .equal(Movie_.id, id ?: -1)
        .build()
        .findFirst()
        .apply(findBlock)

    companion object {

        @Suppress("unused")
        fun mock(
            context: Context
        ): MainViewModel = MainViewModel(context).apply {
            dao = DAO(context)
            networkInfo = NetworkConnectivityServiceImpl(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}