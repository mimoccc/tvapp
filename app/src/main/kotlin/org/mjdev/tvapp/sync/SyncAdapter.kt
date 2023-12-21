package org.mjdev.tvapp.sync

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import androidx.annotation.Keep
import io.objectbox.Box
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.database.DAO.Companion.tx
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvlib.extensions.GlobalExt.IO
import org.mjdev.tvlib.extensions.GlobalExt.launch
import org.mjdev.tvlib.extensions.GlobalExt.safeGet
import org.mjdev.tvlib.interfaces.ItemWithUri.Companion.hasUri
import timber.log.Timber

@Keep
@Suppress("unused")
class SyncAdapter(
    context: Context,
    val apiService: ApiService,
    val dao: DAO
) : AbstractThreadedSyncAdapter(context, true, false) {

    private val movieDao: Box<Movie> get() = dao.movieDao

    private val urls = listOf(
        "https://prehraj.to/hledej/filmy",
//        "https://meatyhunks.com",
    )


    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        launch(IO) {
            try {
                val streams = apiService.streams().safeGet()
                val channels = apiService.channels().safeGet()
                val oldMovies = movieDao.all
                val newMovies = mutableListOf<Movie>()

                streams.forEach { stream ->
                    channels.firstOrNull { channel ->
                        channel.id == stream.channelId
                    }.also { channel ->
                        (channel?.categories ?: listOf(null)).forEach { ctg ->
                            newMovies.add(Movie().apply {
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

                with(SyncItems(oldMovies, newMovies)) {
                    toAdd.forEach { movie ->
                        try {
                            dao.movieDao.tx {
                                put(movie)
                            }
                            Timber.d("Movie: $movie stored.")
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                    toUpdate.forEach { movie ->
                        try {
                            dao.movieDao.tx {
                                put(movie)
                            }
                            Timber.d("Movie: $movie updated.")
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                    toRemove.forEach { movie ->
                        try {
                            dao.movieDao.tx {
                                remove(movie)
                            }
                            Timber.d("Movie: $movie removed.")
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                }

                // todo non ui thread
//            launch(UI) {
//                WebScrapper(context, urls) { page, videoURL ->
//                    try {
//                        val auth = page?.url?.toUri()?.authority ?: ""
//                        val movie = Movie().apply {
//                            this.category = auth
//                            this.title = page?.title?.replace(auth, "")
//                            this.image = videoURL
//                            this.uri = videoURL
//                        }
//                        dao.movieDao.put(movie)
//                        Timber.d("Movie: $movie stored.")
//                    } catch (e: Exception) {
//                        Timber.e(e)
//                    }
//                }.start()
//            }

//            WebVideoScrapper(
//                baseUrls = urls,
//                onVideoFound = { video ->
//                    dao.movieDao.put(Movie().apply {
//                        category = video.category
//                        title = video.title
//                        image = video.thumb
//                        uri = video.url
//                    })
//                },
//                onFinish = { videos ->
//
//                }
//            ).start()

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    class SyncItems(
        private val oldMovies: List<Movie>,
        private val newMovies: List<Movie>
    ) {

        val toAdd = mutableListOf<Movie>()
        val toUpdate = mutableListOf<Movie>()
        val toRemove = mutableListOf<Movie>()

        init {

            newMovies.filter { newMovie ->
                newMovie.hasUri && (oldMovies.count { oldMovie ->
                    oldMovie.uri.contentEquals(newMovie.uri)
                } == 0)
            }.also { addMovies ->
                toAdd.addAll(addMovies)
            }

            newMovies.filter { newMovie ->
                newMovie.hasUri && (oldMovies.count { oldMovie ->
                    oldMovie.uri.contentEquals(newMovie.uri)
                } > 0)
            }.also { updateMovies ->
                toUpdate.addAll(updateMovies)
            }

            oldMovies.filter { oldMovie ->
                (newMovies.count { newMovie ->
                    newMovie.uri.contentEquals(oldMovie.uri)
                } == 0)
            }.also { addMovies ->
                toRemove.addAll(addMovies)
            }

        }

    }

}