/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.syncers

import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.sync.SyncAdapter
import org.mjdev.tvapp.sync.base.Syncer
import org.mjdev.tvlib.database.extensions.ObjectBoxExt.tx
import org.mjdev.tvlib.database.extensions.ObjectBoxExt.update
import org.mjdev.tvlib.webscrapper.WebVideoScrapper
import org.mjdev.tvlib.webscrapper.adblock.AdBlock

class WebMovieSyncer(
    adapter: SyncAdapter,
    private val url: String
) : Syncer(adapter) {
    override suspend fun sync() {
        WebVideoScrapper(
            context = context,
            addBlock = AdBlock(context),
            baseUrls = listOf(url),
            onVideoFound = { video ->
                Movie().apply {
                    category = video.category
                    title = video.title
                    image = video.thumb
                    uri = video.url
                }.also { movie ->
                    dao.movieDao.tx {
                        update(movie) { m -> m.uri == video.url }
                    }
                }
            }
        ).start()
    }
}