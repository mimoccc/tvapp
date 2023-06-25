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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.mjdev.tvapp.api.MovieAPI
import org.mjdev.tvapp.base.viewmodel.BaseViewModel
import org.mjdev.tvapp.repository.IRepository
import org.mjdev.tvapp.repository.MovieRepository
import org.mjdev.tvapp.state.DetailsLoadingState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: IRepository
) : BaseViewModel() {

    fun detailsLoadingState(
        movieId: Long?
    ): StateFlow<DetailsLoadingState> = flow {
        val movie = movieRepository.findMovieById(id = movieId)
        val state = if (movie == null) {
            DetailsLoadingState.NotFound
        } else {
            DetailsLoadingState.Ready(movie = movie)
        }
        emit(state)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DetailsLoadingState.Loading
    )

    companion object {

        @Suppress("unused", "UNUSED_PARAMETER")
        fun mock(context: Context) = DetailViewModel(MovieRepository(MovieAPI()))

    }
}