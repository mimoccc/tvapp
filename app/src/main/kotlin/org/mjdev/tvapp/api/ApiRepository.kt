/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.api

import org.mjdev.tvapp.data.remote.Category
import org.mjdev.tvapp.data.remote.Channel
import org.mjdev.tvapp.data.remote.Epg
import org.mjdev.tvapp.data.remote.Stream
import org.mjdev.tvlib.extensions.GlobalExt.runSafe

class ApiRepository(
    private val apiService: ApiService
) {

    suspend fun channels(): Result<List<Channel>> = runSafe {
        apiService.channels().execute().body() ?: emptyList()
    }

    suspend fun streams(): Result<List<Stream>> = runSafe {
        apiService.streams().execute().body() ?: emptyList()
    }

    suspend fun epg(): Result<List<Epg>> = runSafe {
        apiService.epg().execute().body() ?: emptyList()
    }

    suspend fun categories(): Result<List<Category>> = runSafe {
        apiService.categories().execute().body() ?: emptyList()
    }

}