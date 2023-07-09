/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.repository

import org.mjdev.tvapp.data.Message
import org.mjdev.tvapp.data.Movie

interface IMovieRepository {

    fun getMovies(page: Int = 0, count: Int = 8): Result<List<Movie>>

    fun findMovieById(id: Long?): Result<Movie>

    fun getMessages(): Result<List<Message>>

}