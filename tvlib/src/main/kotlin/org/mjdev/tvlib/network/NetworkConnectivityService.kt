/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class NetworkConnectivityService (
    val context: Context
) {

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    val networkStatus = callbackFlow {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkStatus.Disconnected()).isSuccess
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Connected(network)).isSuccess
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Disconnected(network)).isSuccess
            }
        }
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }.flowOn(Dispatchers.IO)

    companion object {

        @Composable
        fun rememberNetworkService(): NetworkConnectivityService {
            val context = LocalContext.current
            return remember(context) {
                NetworkConnectivityService(context)
            }
        }

    }

}
