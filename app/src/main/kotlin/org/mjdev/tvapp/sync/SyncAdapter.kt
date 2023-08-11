package org.mjdev.tvapp.sync

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import io.objectbox.Box
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.database.DAO.Companion.tx
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvlib.extensions.GlobalExt.safeGet
import org.mjdev.tvlib.extensions.ListExt.contains
import timber.log.Timber

@Suppress("unused", "PrivatePropertyName")
class SyncAdapter(
    context: Context,
    val apiService: ApiService,
    val dao: DAO
) : AbstractThreadedSyncAdapter(context, true, false) {

    private val READ_COUNT = 8

    private val coroutineScope by lazy {
        CoroutineScope(Dispatchers.IO)
    }

    private val movieDao: Box<Movie> get() = dao.movieDao

    private val moviesSynced by lazy {
        movieDao.all.toMutableList()
    }

    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        try {
            coroutineScope.launch {
                channelFlow {
                    val streams = apiService.streams().safeGet()
                    val channels = apiService.channels().safeGet()
                    streams.forEach { stream ->
                        channels.firstOrNull { channel ->
                            channel.id == stream.channelId
                        }.also { channel ->
                            (channel?.categories ?: listOf(null)).forEach { ctg ->
                                send(Movie().apply {
                                    title = channel?.name ?: stream.channelId
                                    uri = stream.url
                                    category = ctg
                                    image = channel?.logo
                                    country = channel?.country
                                    isNsfw = channel?.isNsfw ?: false
                                })
                            }
                        }
                    }
                }.collect { movie ->
                    val exists = moviesSynced.contains { synced ->
                        synced.uri == movie.uri
                    }
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