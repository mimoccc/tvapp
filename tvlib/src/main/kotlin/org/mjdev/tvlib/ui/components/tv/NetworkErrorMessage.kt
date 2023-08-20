/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import org.mjdev.tvlib.R
import org.mjdev.tvlib.extensions.ComposeExt.asException
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.network.NetworkConnectivityService.Companion.rememberNetworkService
import org.mjdev.tvlib.network.NetworkStatus
import org.mjdev.tvlib.network.isNotConnected

@Composable
fun NetworkErrorMessage(
    message: Any? = R.string.error_no_network,
    dismissible: Boolean = false,
    backgroundColor: Color = Color.Black,
    networkService: NetworkConnectivityService = rememberNetworkService(),
    networkState: State<NetworkStatus?> = networkService.networkStatus
        .collectAsState(NetworkStatus.Connected(null)),
) {
    if (networkState.isNotConnected) {
        ErrorMessage(
            error = message.asException(),
            backgroundColor = backgroundColor,
            dismissible = dismissible
        )
    }
}