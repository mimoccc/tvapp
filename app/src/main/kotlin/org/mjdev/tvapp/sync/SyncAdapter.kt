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
import org.mjdev.tvapp.R
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.database.DAO.Companion.tx
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvlib.extensions.GlobalExt.safeGet
import org.mjdev.tvlib.extensions.ListExt.contains
import timber.log.Timber

@Suppress("unused", "PrivatePropertyName")
class SyncAdapter(
    context: Context,
    val repository: IMovieRepository,
    val apiService: ApiService,
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
        val noCategoryString = context.getString(R.string.no_category)
        try {
            runBlocking(Dispatchers.IO) {
                val movieUris = movieDao.all.map { m -> m.uri }.toMutableList()
                flow {
                    val streams = apiService.streams().safeGet()
                    val channels = apiService.channels().safeGet()
                    streams.forEach { stream ->
                        val channel = channels.firstOrNull { channel ->
                            channel.id == stream.channel
                        }
                        if (channel != null) {
                            emit(Movie().apply {
                                title = channel.name
                                uri = stream.url
                                category = channel.categories?.firstOrNull() ?: noCategoryString
                                image = channel.logo
                                country = channel.country
                                isNsfw = channel.isNsfw ?: false
                            })
                        } else {
                            emit(Movie().apply {
                                title = stream.channel
                                uri = stream.url
                                category = noCategoryString
                                image = null
                                country = null
                                isNsfw = false
                            })
                        }
                    }
                }.collect { movie ->
                    val exists = movieUris.contains { uri -> uri == movie.uri }
                    if (exists) {
                        // todo duplicities
                    } else {
                        dao.movieDao.tx {
                            put(movie)
                        }
                        Timber.d("Movie: $movie stored.")
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}