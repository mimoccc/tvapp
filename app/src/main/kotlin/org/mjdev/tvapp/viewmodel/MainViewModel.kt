/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.mjdev.tvapp.base.extensions.ListExt.asMap
import org.mjdev.tvapp.base.helpers.apps.AppsManager
import org.mjdev.tvapp.base.helpers.cursor.AudioCursor
import org.mjdev.tvapp.base.helpers.cursor.PhotoCursor
import org.mjdev.tvapp.base.helpers.cursor.VideoCursor
import org.mjdev.tvapp.base.network.NetworkConnectivityService
import org.mjdev.tvapp.base.network.NetworkConnectivityServiceImpl
import org.mjdev.tvapp.base.viewmodel.BaseViewModel
import org.mjdev.tvapp.data.Message
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvapp.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IMovieRepository,
    var networkInfo: NetworkConnectivityService
) : BaseViewModel() {

    @Inject
    lateinit var appsList: AppsManager

    @Inject
    lateinit var localAudioCursor: AudioCursor

    @Inject
    lateinit var localVideoCursor: VideoCursor

    @Inject
    lateinit var localPhotoCursor: PhotoCursor

    val messages: StateFlow<List<Message>> = channelFlow {
        send(repository.getMessages().getOrThrow())
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        listOf()
    )

    val featuredMovieList: StateFlow<List<Any?>> = flow {
        mutableListOf<Any?>().apply {
            addAll(repository.getMovies().getOrThrow().takeLast(8))
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
        emit(repository.getMovies().getOrThrow().asMap { m -> Pair(m.category, m) })
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        mapOf()
    )


    companion object {

        @Suppress("unused")
        fun mockMainViewModel(context: Context): MainViewModel = MainViewModel(
            MovieRepository(DAO(context)),
            NetworkConnectivityServiceImpl(context)
        )

    }

}