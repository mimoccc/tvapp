/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import android.net.ConnectivityManager
import android.net.Network

class ConnectivityCallback(
    val trySend: (data: NetworkStatus) -> Unit
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        trySend(NetworkStatus.Connected(network))
    }

    override fun onUnavailable() {
        trySend(NetworkStatus.Disconnected)
    }

    override fun onLost(network: Network) {
        trySend(NetworkStatus.Disconnected)
    }

}