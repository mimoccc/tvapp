/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.cast

import com.google.android.gms.cast.SessionState
import com.google.android.gms.cast.framework.SessionTransferCallback

class CastSessionTransferCallback(
    val onMediaTransferring: (transferType: Int) -> Unit,
    val onMediaTransferred: (transferType: Int, sessionState: SessionState) -> Unit,
    val onMediaTransferFailed: (transferType: Int, transferFailedReason: Int) -> Unit
) : SessionTransferCallback() {

    override fun onTransferring(@TransferType transferType: Int) {
        onMediaTransferring(transferType)
    }

    override fun onTransferred(
        @TransferType
        transferType: Int,
        sessionState: SessionState
    ) {
        onMediaTransferred(transferType, sessionState)
    }

    override fun onTransferFailed(
        @TransferType
        transferType: Int,
        @TransferFailedReason
        transferFailedReason: Int
    ) {
        onMediaTransferFailed(transferType, transferFailedReason)
    }

}
