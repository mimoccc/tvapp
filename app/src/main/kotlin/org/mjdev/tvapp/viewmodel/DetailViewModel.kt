/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.mjdev.tvlib.viewmodel.BaseViewModel
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvapp.repository.MovieRepository
import org.mjdev.tvapp.state.DetailsLoadingState
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: IMovieRepository
) : BaseViewModel() {

    fun detailsLoadingState(
        data: Any?
    ): StateFlow<DetailsLoadingState> = flow {
        try {
            when (data) {
                is Int, is Long -> {
                    val movieId: Long? = data as? Long
                    val result = movieRepository.findMovieById(movieId)
                    val state = if (result.isSuccess) {
                        DetailsLoadingState.Ready(result.getOrThrow())
                    } else {
                        DetailsLoadingState.NotFound
                    }
                    emit(state)
                }
                is String -> {
                    emit(DetailsLoadingState.Ready(data))
                }
                is Uri, is URL -> {
                    emit(DetailsLoadingState.Ready(data))
                }
                else -> {
                    emit(DetailsLoadingState.Ready(data))
                }
            }
        } catch (e: Exception) {
            emit(DetailsLoadingState.Error(e))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DetailsLoadingState.Loading
    )

    companion object {

        @Suppress("unused")
        fun mockDetailViewModel(
            context: Context
        ): DetailViewModel = DetailViewModel(MovieRepository(DAO(context)))

    }

}