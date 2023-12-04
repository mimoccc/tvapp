/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import dagger.hilt.android.HiltAndroidApp
import org.mjdev.tvapp.sync.SyncService.Companion.createSyncAccount
import org.mjdev.tvapp.sync.SyncService.Companion.requestSync
import org.mjdev.tvlib.application.TvApplication

@HiltAndroidApp
class Application : TvApplication() {

    private fun createAccountAndSync() {
        createSyncAccount()?.let { account ->
            requestSync(account)
        }
    }

    override fun onCreate() {
        super.onCreate()
        createAccountAndSync()
    }

}