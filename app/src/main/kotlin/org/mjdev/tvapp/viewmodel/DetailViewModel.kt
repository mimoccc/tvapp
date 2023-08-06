/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvapp.repository.MovieRepository
import org.mjdev.tvlib.network.NetworkConnectivityServiceImpl
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

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

    fun movieList() = runBlocking {
        movieRepository.getMovies().getOrNull() ?: emptyList()
    }

    companion object {

        @Suppress("unused")
        fun mockDetailViewModel(
            context: Context
        ): DetailViewModel = DetailViewModel().apply {
            movieRepository = MovieRepository(DAO(context))
            networkInfo = NetworkConnectivityServiceImpl(context)
            localAudioCursor = AudioCursor(context)
            localVideoCursor = VideoCursor(context)
            localPhotoCursor = PhotoCursor(context)
        }

    }

}