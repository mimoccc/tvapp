/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.base

import android.content.Context
import org.mjdev.tvapp.sync.SyncAdapter

abstract class Syncer(
    private val adapter: SyncAdapter
) {
    val context: Context get() = adapter.context
    val dao get() = adapter.dao
    val apiService get() = adapter.apiService
    val movieDao get() = adapter.movieDao

    abstract suspend fun sync()

}