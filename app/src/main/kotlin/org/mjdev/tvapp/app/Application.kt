/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.mjdev.tvapp.sync.SyncService
import org.mjdev.tvapp.sync.SyncService.Companion.createSyncAccount

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        createSyncAccount()?.also { account ->
            SyncService.requestSync(account)
        }
    }

}