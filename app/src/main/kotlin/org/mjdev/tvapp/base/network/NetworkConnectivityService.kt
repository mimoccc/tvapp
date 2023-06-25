/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.network

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityService {

    val networkStatus: Flow<NetworkStatus>

}