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
import org.mjdev.tvapp.api.MovieAPI
import org.mjdev.tvapp.base.network.NetworkConnectivityService
import org.mjdev.tvapp.base.network.NetworkConnectivityServiceImpl
import org.mjdev.tvapp.base.viewmodel.BaseViewModel
import org.mjdev.tvapp.data.Category
import org.mjdev.tvapp.data.Message
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.repository.IRepository
import org.mjdev.tvapp.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: IRepository,
    var networkInfo: NetworkConnectivityService
) : BaseViewModel() {

    val messages: StateFlow<List<Message>> = channelFlow<List<Message>> {
        send(listOf())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), listOf())

    val featuredMovieList: StateFlow<List<Movie>> = flow {
        emit(movieRepository.getFeaturedMovieList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), listOf())

    val categoryList: StateFlow<List<Category>> = flow {
        emit(movieRepository.getCategoryList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), listOf())

    companion object {

        @Suppress("unused")
        fun mock(context: Context) = MainViewModel(
            MovieRepository(MovieAPI()),
            NetworkConnectivityServiceImpl(context),
        )

    }

}