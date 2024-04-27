/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.network

import android.net.Network
import androidx.compose.runtime.State

@Suppress("unused")
sealed class NetworkStatus {

    data object Unknown : NetworkStatus()

    class Connected(val network: Network? = null) : NetworkStatus()

    class Disconnected(val network: Network? = null) : NetworkStatus()

}

val State<NetworkStatus?>?.isConnected: Boolean
    get() = this?.value !is NetworkStatus.Disconnected

val State<NetworkStatus?>?.isNotConnected: Boolean
    get() = this?.value is NetworkStatus.Disconnected
