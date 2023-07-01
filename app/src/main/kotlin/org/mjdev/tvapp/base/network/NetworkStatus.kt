/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import android.net.Network
import androidx.compose.runtime.State

@Suppress("unused")
sealed class NetworkStatus {

    object Unknown : NetworkStatus()

    class Connected(val network: Network?) : NetworkStatus()

    object Disconnected : NetworkStatus()

}

val State<NetworkStatus?>?.isConnected: Boolean
    get() = this?.value is NetworkStatus.Connected

val State<NetworkStatus?>?.isNotConnected: Boolean
    get() = !isConnected