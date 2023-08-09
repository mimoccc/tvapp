/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.repository

import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.database.DAO.Companion.tx
import org.mjdev.tvlib.extensions.GlobalExt.runSafe
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private var dao: DAO
) : IMovieRepository {

    override suspend fun getMovies(
        page: Int,
        count: Int
    ): Result<List<Movie>> = runSafe {
        val fromIdx = page * count
        var toIdx = fromIdx + count
        dao.movieDao.tx {
            all.let { list ->
                if (fromIdx >= list.size)
                    emptyList()
                else if (toIdx >= list.size) {
                    toIdx = list.size - 1
                    list.subList(fromIdx, toIdx)
                } else {
                    list.subList(fromIdx, toIdx)
                }
            }
        } ?: emptyList()
    }

    override suspend fun findMovieById(
        id: Long?
    ): Result<Movie> = runSafe {
        dao.movieDao.tx {
            get(id!!)
        } ?: throw (Exception("Movie not found."))
    }

    override suspend fun getMessages(): Result<List<Message>> = runSafe {
        dao.messagesDao.tx {
            all
        } ?: emptyList()
    }

}