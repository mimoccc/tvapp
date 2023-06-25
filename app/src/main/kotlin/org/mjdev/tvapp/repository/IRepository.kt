/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.repository

import org.mjdev.tvapp.data.Category
import org.mjdev.tvapp.data.Movie

interface IRepository {

    suspend fun findMovieById(id: Long?): Movie?

    suspend fun getFeaturedMovieList(): List<Movie>

    suspend fun getCategoryList(): List<Category>

}