/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import androidx.annotation.Keep
import io.objectbox.Box
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvapp.sync.base.Syncer
import org.mjdev.tvapp.sync.syncers.GithubMoviesSyncer
import org.mjdev.tvapp.sync.syncers.WebMovieSyncer
import org.mjdev.tvlib.extensions.GlobalExt.launchIO

@Keep
@Suppress("unused")
class SyncAdapter(
    context: Context,
    val apiService: ApiService,
    val dao: DAO,
    val movieDao: Box<Movie> = dao.movieDao,
) : AbstractThreadedSyncAdapter(context, true, false) {

    private fun ifDebug(syncer: Syncer) = if (BuildConfig.DEBUG) syncer else null
    private fun ifNotDebug(syncer: Syncer) = if (!BuildConfig.DEBUG) syncer else null

    private val customSyncers: List<Syncer> by lazy {
        listOfNotNull(
            ifNotDebug(GithubMoviesSyncer(this)),
            ifDebug(WebMovieSyncer(this, "https://tekeye.uk/html/html5-video-test-page")),
            ifDebug(WebMovieSyncer(this, "https://prehraj.to/hledej/filmy")),
            ifDebug(WebMovieSyncer(this, "https://movie4kto.net/")),
            ifDebug(WebMovieSyncer(this, "https://movie4kto.net/")),
        )
    }

    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        launchIO {
            customSyncers.forEach { syncer -> syncer.sync() }
        }
    }
}

