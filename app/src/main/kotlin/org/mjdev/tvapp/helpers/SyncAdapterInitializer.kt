/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.helpers

import android.content.Context
import androidx.startup.Initializer
import org.mjdev.tvapp.sync.SyncService
import org.mjdev.tvapp.sync.SyncService.Companion.createSyncAccount
import org.mjdev.tvlib.extensions.GlobalExt.postDelayed
import timber.log.Timber

@Suppress("unused")
class SyncAdapterInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.d("Sync started.")
        context.createSyncAccount()?.also { account ->
            postDelayed(20000) {
                SyncService.requestSync(account)
            }
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}