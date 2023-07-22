/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.repository

import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.Movie

interface IMovieRepository {

    suspend fun getMovies(page: Int = 0, count: Int = Int.MAX_VALUE): Result<List<Movie>>

    suspend fun findMovieById(id: Long?): Result<Movie>

    suspend fun getMessages(): Result<List<Message>>

}