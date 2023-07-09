/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.repository

import org.mjdev.tvapp.base.extensions.GlobalExt.runSafe
import org.mjdev.tvapp.data.Message
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.database.DAO
import javax.inject.Inject

class MovieRepository @Inject constructor(
    var dao: DAO
) : IMovieRepository {

    override fun getMovies(
        page: Int,
        count: Int
    ): Result<List<Movie>> = runSafe {
        dao.movieDao.all.subList(page * count, page)
    }

    override fun findMovieById(
        id: Long?
    ): Result<Movie> = runSafe {
        if ((id != null) && (id > 0)) dao.movieDao.get(id)
        else throw (Exception("Movie not found."))
    }

    override fun getMessages(): Result<List<Message>> = runSafe {
        dao.messagesDao.all
    }

}