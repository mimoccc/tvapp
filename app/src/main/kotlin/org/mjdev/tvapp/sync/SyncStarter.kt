/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync

import android.content.Context
import android.content.Intent
import android.content.BroadcastReceiver
import org.mjdev.tvapp.sync.SyncService.Companion.createAccountAndSync

class SyncStarter : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        context.createAccountAndSync()
    }

}
