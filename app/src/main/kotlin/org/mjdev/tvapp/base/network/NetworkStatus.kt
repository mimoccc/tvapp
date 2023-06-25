/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import android.net.Network

sealed class NetworkStatus {

    object Unknown: NetworkStatus()

    class Connected(val network: Network): NetworkStatus()

    object Disconnected: NetworkStatus()

}