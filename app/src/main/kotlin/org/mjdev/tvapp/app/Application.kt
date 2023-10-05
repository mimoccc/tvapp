/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import dagger.hilt.android.HiltAndroidApp
import org.mjdev.tvapp.sync.SyncService
import org.mjdev.tvapp.sync.SyncService.Companion.createSyncAccount
import org.mjdev.tvlib.application.TvApplication
import org.mjdev.tvlib.extensions.GlobalExt.postDelayed

@HiltAndroidApp
class Application : TvApplication() {

    override fun onCreate() {
        super.onCreate()
        createSyncAccount()?.also { account ->
            postDelayed(20000) {
                SyncService.requestSync(account)
            }
        }
    }

}