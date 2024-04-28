/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync

import android.accounts.Account
import android.annotation.SuppressLint
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import io.objectbox.Box
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.events.PauseEvent
import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvapp.sync.base.Syncer
import org.mjdev.tvapp.sync.syncers.GithubMoviesSyncer
import org.mjdev.tvlib.events.post.postEvent
import org.mjdev.tvlib.extensions.GlobalExt.launchIO

@Keep
@Suppress("unused" , "DEPRECATION")
class SyncAdapter(
    context: Context,
    val apiService: ApiService,
    val dao: DAO,
    val movieDao: Box<Media> = dao.movieDao,
) : AbstractThreadedSyncAdapter(context, true, false) {

    private val connManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val activeNetworkInfo
        get() = connManager.activeNetworkInfo

    private val isConnected
        get() = activeNetworkInfo?.isAvailable == true && activeNetworkInfo?.isConnected == true

    private fun ifDebug(syncer: Syncer) = if (BuildConfig.DEBUG) syncer else null
    private fun ifNotDebug(syncer: Syncer) = if (!BuildConfig.DEBUG) syncer else null

    private val customSyncers: List<Syncer> by lazy {
        if (isConnected) {
            listOfNotNull(
                GithubMoviesSyncer(this),
            )
        } else {
            emptyList()
        }
    }

    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        launchIO {
            customSyncers.forEach { syncer ->
                syncer.sync(this)
            }
        }
    }

    companion object {

        fun pauseSync() =
            postEvent(PauseEvent())

        @SuppressLint("ComposableNaming")
        @Composable
        fun pauseSyncUntilGone() {
            val coroutineScope = rememberCoroutineScope()
            var disposed = false
            DisposableEffect(Unit) {
                coroutineScope.launch {
                    do {
                        PauseEvent().let { event ->
                            postEvent(event)
                            delay(event.timeout / 2)
                        }
                    } while (!disposed)
                }
                onDispose {
                    disposed = true
                }
            }
        }
    }

}
