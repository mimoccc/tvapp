/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.annotation.Keep

@Keep
class AuthenticatorService : Service() {

    private lateinit var authenticator: Authenticator

    override fun onCreate() {
        authenticator = Authenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder =
        authenticator.iBinder

}