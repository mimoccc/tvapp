/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive

class NetworkConnectivityServiceImpl constructor(
    val context: Context
) : NetworkConnectivityService {

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val request by lazy {
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
            .build()
    }

    private var networkState: NetworkStatus? = null

    private val connectivityCallback = ConnectivityCallback { status ->
        networkState = status
    }

    init {
        connectivityManager.registerNetworkCallback(request, connectivityCallback)
    }

    override val networkStatus: Flow<NetworkStatus?> = callbackFlow {
        while (isActive) {
            trySend(networkState)
            delay(200L)
        }
        awaitClose {
            connectivityManager.unregisterNetworkCallback(connectivityCallback)
        }
    }.distinctUntilChanged().flowOn(Dispatchers.IO)

}