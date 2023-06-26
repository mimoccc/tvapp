/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.api

import org.mjdev.tvapp.base.helpers.Result
import org.mjdev.tvapp.base.helpers.Result.Companion.unwrapOr
import org.mjdev.tvapp.data.Category
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.mock.MovieList

@Suppress("RedundantSuspendModifier")
class MovieAPI {

    suspend fun loadCategoryList(): Result<List<Category>> {
        return Result.Success(MovieList.categories.map { name ->
            Category(
                name = name,
                movieList = getMovieListByCategory(name).unwrapOr(emptyList())
            )
        })
    }

    suspend fun loadFeaturedMovieList(): Result<List<Movie>> {
        return Result.Success(MovieList.featured)
    }

    suspend fun getMovieListByCategory(
        category: String
    ): Result<List<Movie>> {
        return Result.Success(MovieList.getByCategory(category))
    }

    suspend fun findMovieById(
        id: Long
    ): Result<Movie> {
        return MovieList.findById(id).let { movie ->
            if (movie == null) Result.Empty
            else Result.Success(movie)
        }
    }

}