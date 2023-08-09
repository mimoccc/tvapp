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
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.activity.MainActivity
import org.mjdev.tvlib.extensions.ListExt.asMap
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvapp.repository.MovieRepository
import org.mjdev.tvlib.helpers.apps.appsManager
import org.mjdev.tvlib.network.NetworkConnectivityServiceImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    context: Context
) : BaseViewModel() {

    @Inject
    lateinit var movieRepository: IMovieRepository

    @Inject
    lateinit var networkInfo: NetworkConnectivityService

    @Inject
    lateinit var localAudioCursor: AudioCursor

    @Inject
    lateinit var localVideoCursor: VideoCursor

    @Inject
    lateinit var localPhotoCursor: PhotoCursor

    val apps = appsManager(
        context,
        ComponentName(
            BuildConfig.APPLICATION_ID,
            MainActivity::class.java.name
        )
    ).stateInViewModel()

    val messages: StateFlow<List<Message>> = channelFlow {
        send(movieRepository.getMessages().getOrThrow())
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        listOf()
    )

    val featuredMovieList: StateFlow<List<Any?>> = flow {
        mutableListOf<Any?>().apply {
            addAll(movieRepository.getMovies().getOrThrow().takeLast(8))
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
            emit(result)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        listOf()
    )

    val categoryList: StateFlow<Map<String, List<Movie>>> = flow {
        movieRepository.getMovies().getOrThrow().sortedBy { m ->
            m.category
        }.asMap { m ->
            Pair(m.category, m)
        }.also { map ->
            emit(map)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        mapOf()
    )

    val countryList: StateFlow<List<String>> = flow {
        movieRepository.getMovies().getOrThrow().filter { it.country != null }.map {
            it.country.toString()
        }.distinct().sortedBy {
            it
        }.also {
            emit(it)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        listOf()
    )

    fun findMovie(id: Long?): Movie? = runBlocking {
        movieRepository.findMovieById(id).getOrNull()
    }

    companion object {

        @Suppress("unused")
        fun mockMainViewModel(
            context: Context
        ): MainViewModel = MainViewModel(context).apply {
            movieRepository = MovieRepository(DAO(context))
            networkInfo = NetworkConnectivityServiceImpl(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}