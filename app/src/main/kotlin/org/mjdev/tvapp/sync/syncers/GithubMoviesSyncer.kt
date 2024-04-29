/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.syncers

import androidx.compose.runtime.MutableState
import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvapp.sync.SyncAdapter
import org.mjdev.tvapp.sync.base.SyncItems
import org.mjdev.tvapp.sync.base.Syncer
import org.mjdev.tvlib.database.extensions.ObjectBoxExt.stx
import org.mjdev.tvlib.database.extensions.ObjectBoxExt.update
import org.mjdev.tvlib.extensions.ApiResponseExt.safeGet
import timber.log.Timber

class GithubMoviesSyncer(
    adapter: SyncAdapter,
    syncerName: String = GithubMoviesSyncer::class.java.simpleName
) : Syncer(adapter, syncerName) {

    override suspend fun doSync() {
        val streams = apiService.streams().safeGet()
        val channels = apiService.channels().safeGet()

        val oldMovies = movieDao.all
        val newMovies = mutableListOf<Media>()

        streams.forEach { stream ->
            channels.firstOrNull { channel ->
                channel.id == stream.channelId
            }.also { channel ->
                (channel?.categories ?: listOf(null)).forEach { ctg ->
                    newMovies.add(Media().apply {
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

        // todo if exists
        with(SyncItems(oldMovies, newMovies)) {
            toAdd.forEach { movie ->
                try {
                    dao.movieDao.stx { put(movie) }
                    Timber.d("Movie: $movie stored.")
                } catch (e: Exception) {
//                    Timber.e(e)
                }
            }

            toUpdate.forEach { movie ->
                try {
                    dao.movieDao.stx { update(movie) { m -> m.uri == movie.uri } }
                    Timber.d("Movie: $movie updated.")
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }

            toRemove.forEach { movie ->
                try {
                    dao.movieDao.stx { remove(movie) }
                    Timber.d("Movie: $movie removed.")
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }

    }

}