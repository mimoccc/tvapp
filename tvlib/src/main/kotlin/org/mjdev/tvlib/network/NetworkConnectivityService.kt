/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityService {

    val networkStatus: Flow<NetworkStatus?>

    companion object {

        @Composable
        fun rememberNetworkService(): NetworkConnectivityService {
            val context = LocalContext.current
            return remember {
                NetworkConnectivityServiceImpl(context)
            }
        }

    }

}