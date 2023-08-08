/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.audiopreview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
internal class NetworkReceiver(
    val context:Context,
    private val networkListener: NetworkListener
) : BroadcastReceiver() {

    init {
        context.registerReceiver(
            this,
            IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
            )
        )
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (isOnline(context)) {
            networkListener.onNetworkAvailable()
        } else {
            networkListener.onNetworkUnavailable()
        }
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }

    companion object {

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    }

}