/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

@Suppress("UNUSED_PARAMETER")
class NetworkConnectivityServiceImpl constructor(
    context: Context
) : NetworkConnectivityService {
//    private val connectivityManager = context
//        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // todo error ConnectivityManager$TooManyRequestsException
    override val networkStatus: Flow<NetworkStatus> = callbackFlow {
        trySend(NetworkStatus.Connected(null))
//        val connectivityCallback = ConnectivityCallback { status ->
//            trySend(status)
//        }
//        val request = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
//            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
//            .build()
//        connectivityManager.registerNetworkCallback(request, connectivityCallback)
        awaitClose {
//            connectivityManager.unregisterNetworkCallback(connectivityCallback)
        }
    }.distinctUntilChanged().flowOn(Dispatchers.IO)

}