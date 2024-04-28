/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.cast

import android.annotation.SuppressLint
import android.app.Service
import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.content.res.Configuration
import android.os.Build
import android.os.IBinder
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import com.google.android.gms.cast.SessionState
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import com.google.android.gms.cast.framework.SessionTransferCallback
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import org.mjdev.tvapp.R
import timber.log.Timber

@Suppress("UNUSED_PARAMETER", "unused")
@Keep
class CastService : Service(), SessionManagerListener<CastSession> {

    private val notificationManager by lazy {
        NotificationManagerCompat.from(this)
    }

    private val notificationChannelID
        get() = getString(R.string.app_name)

    private val isGooglePlayServicesAvailable
        get() = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) ==
                ConnectionResult.SUCCESS

    private val isNotTv: Boolean
        get() {
            val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
            return uiModeManager.currentModeType != Configuration.UI_MODE_TYPE_TELEVISION
        }

    private val isCastApiAvailable: Boolean
        get() = isNotTv && isGooglePlayServicesAvailable

    @Suppress("DEPRECATION")
    private val castContext: CastContext?
        get() {
            return if (isCastApiAvailable) try {
                CastContext.getSharedInstance(this)
            } catch (e: Exception) {
                Timber.e(e)
                null
            } else null
        }

    private val sessionManager
        get() = castContext?.sessionManager

    @delegate:RequiresApi(Build.VERSION_CODES.O)
    private val notificationChannel by lazy {
        NotificationChannelCompat.Builder(
            notificationChannelID,
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        ).build().apply {
            notificationManager.createNotificationChannel(this)
        }
    }

    private val notification by lazy {
        NotificationCompat
            .Builder(
                this,
                notificationChannelID
            )
            .setContentTitle("Cast service")
            .setContentText("Cast service")
            .build()
    }

    private val sessionTransferCallback = CastSessionTransferCallback(
        onMediaTransferring = { transferType ->
            onMediaTransferring(transferType)
        },
        onMediaTransferred = { transferType, sessionState ->
            onMediaTransferred(transferType, sessionState)
        },
        onMediaTransferFailed = { transferType, transferFailedReason ->
            onMediaTransferFailed(transferType, transferFailedReason)
        }
    )

    private fun onMediaTransferring(transferType: Int) {
    }

    private fun onMediaTransferred(transferType: Int, sessionState: SessionState) {
    }

    private fun onMediaTransferFailed(transferType: Int, transferFailedReason: Int) {

    }

    fun onTransferring(@SessionTransferCallback.TransferType transferType: Int) {
        // Perform necessary steps prior to onTransferred
    }

    fun onTransferred(
        @SessionTransferCallback.TransferType
        transferType: Int,
        sessionState: SessionState?
    ) {
//        if (transferType == TRANSFER_TYPE_FROM_REMOTE_TO_LOCAL) {
//            // Remote stream is transferred to the local device.
//            // Retrieve information from the SessionState to continue playback on the local player.
//        }
    }

    fun onTransferFailed(
        @SessionTransferCallback.TransferType
        transferType: Int,
        @SessionTransferCallback.TransferFailedReason
        transferFailedReason: Int
    ) {
        // Handle transfer failure.
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ServiceCompat.startForeground(
                    this,
                    1,
                    notification,
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
                )
            }
            sessionManager?.addSessionManagerListener(this, CastSession::class.java)
            castContext?.addSessionTransferCallback(sessionTransferCallback)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun onDestroy() {
        try {
            sessionManager?.removeSessionManagerListener(this, CastSession::class.java)
            castContext?.removeSessionTransferCallback(sessionTransferCallback)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onSessionEnded(session: CastSession, p1: Int) {
    }

    override fun onSessionEnding(session: CastSession) {
    }

    override fun onSessionResumeFailed(session: CastSession, p1: Int) {
    }

    override fun onSessionResumed(session: CastSession, p1: Boolean) {
    }

    override fun onSessionResuming(session: CastSession, p1: String) {
    }

    override fun onSessionStartFailed(session: CastSession, p1: Int) {
    }

    override fun onSessionStarted(session: CastSession, p1: String) {
    }

    override fun onSessionStarting(session: CastSession) {
    }

    override fun onSessionSuspended(session: CastSession, p1: Int) {
    }

    companion object {

        // todo
        fun start(context: Context) {
//            try {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    context.startForegroundService(Intent(context, CastService::class.java))
//                } else {
//                    context.startService(Intent(context, CastService::class.java))
//                }
//            } catch (e: Exception) {
//                Timber.e(e)
//            }
        }

        fun stop(context: Context) {
            try {
                context.stopService(Intent(context, CastService::class.java))
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

}