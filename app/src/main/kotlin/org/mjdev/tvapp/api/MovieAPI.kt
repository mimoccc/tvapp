/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.api

import org.mjdev.tvapp.base.helpers.Option
import org.mjdev.tvapp.base.helpers.Option.Companion.unwrapOr
import org.mjdev.tvapp.data.Category
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.mock.MovieList

@Suppress("RedundantSuspendModifier")
class MovieAPI {

    suspend fun loadCategoryList(): Option<List<Category>> {
        return Option.Some(MovieList.categories.map { name ->
            Category(
                name = name,
                movieList = getMovieListByCategory(name).unwrapOr(emptyList())
            )
        })
    }

    suspend fun loadFeaturedMovieList(): Option<List<Movie>> {
        return Option.Some(MovieList.featured)
    }

    suspend fun getMovieListByCategory(
        category: String
    ): Option<List<Movie>> {
        return Option.Some(MovieList.getByCategory(category))
    }

    suspend fun findMovieById(
        id: Long
    ): Option<Movie> {
        return MovieList.findById(id).let { movie ->
            if (movie == null) Option.None
            else Option.Some(movie)
        }
    }

}