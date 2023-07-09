package org.mjdev.tvapp.sync

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import io.objectbox.Box
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.mjdev.tvapp.base.extensions.ListExt.containsNot
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import timber.log.Timber

@Suppress("unused", "PrivatePropertyName")
class SyncAdapter(
    context: Context,
    val repository: IMovieRepository,
    val dao: DAO
) : AbstractThreadedSyncAdapter(context, true, false) {

    private val READ_COUNT = 8

    private val movieDao: Box<Movie> get() = dao.movieDao

    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        try {
            val movieUris = movieDao.all.map { m ->
                m.uri
            }.toMutableList()

            runBlocking(Dispatchers.IO) {
                flow {
                    var page = 0
                    var totalCnt: Long = 0
                    var readCnt: Long = 0
                    do {
                        repository.getMovies(page, READ_COUNT).let { result ->
                            if (result.isSuccess) {
                                result.getOrNull()?.let { moviesData ->
                                    readCnt = moviesData.size.toLong()
                                    syncResult?.let { sr ->
                                        sr.stats.numEntries = readCnt
                                        moviesData.forEach { movie ->
                                            emit(movie)
                                            sr.stats.numInserts = readCnt
                                            totalCnt += readCnt
                                        }
                                    }
                                    page += 1
                                }
                            } else {
                                Timber.e(result.exceptionOrNull() ?: Exception("Unknown error"))
                            }
                        }
                    } while (readCnt > 0)
                }.collect { data ->
                    if (movieUris.containsNot { uri -> uri == data.uri }) {
                        dao.movieDao.put(data)
                        Timber.d("Movie: $data stored.")
                    } else {
                        // todo duplicities
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}