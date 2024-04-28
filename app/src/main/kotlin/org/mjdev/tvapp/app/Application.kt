/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import dagger.hilt.android.HiltAndroidApp
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.cast.CastService
import org.mjdev.tvapp.sync.SyncService.Companion.createAccountAndSync
import org.mjdev.tvlib.application.TvApplication
import timber.log.Timber

@HiltAndroidApp
class Application : TvApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        createAccountAndSync()
        CastService.start(this)
    }

}