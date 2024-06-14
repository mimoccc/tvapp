/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXContextTranslators
import org.kodein.di.bind
import org.kodein.di.singleton
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.cast.CastService
import org.mjdev.tvapp.module.ApplicationModule
import org.mjdev.tvapp.sync.SyncService.Companion.createAccountAndSync
import org.mjdev.tvlib.application.TvApplication
import timber.log.Timber

class Application : TvApplication(), DIAware {

    override val di by lazy { getDI(this) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        createAccountAndSync()
        CastService.start(this)
    }

    companion object {
        fun getDI(context: Context): DI = DI.lazy {
            import(androidXContextTranslators)
            bind<Context>() with singleton { context }
            import(ApplicationModule)
        }
    }

}