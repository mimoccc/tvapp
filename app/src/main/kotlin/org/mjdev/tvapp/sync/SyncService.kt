/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Service
import android.content.ContentResolver
import android.content.ContentResolver.setIsSyncable
import android.content.ContentResolver.setSyncAutomatically
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.annotation.Keep
import dagger.hilt.android.AndroidEntryPoint
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.ApiService
import timber.log.Timber
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate")
@Keep
@AndroidEntryPoint
class SyncService : Service() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var dao: DAO

    override fun onCreate() {
        super.onCreate()
        synchronized(sSyncAdapterLock) {
            sSyncAdapter = sSyncAdapter ?: SyncAdapter(
                context = this,
                apiService = apiService,
                dao = dao
            )
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return sSyncAdapter?.syncAdapterBinder ?: throw IllegalStateException()
    }

    companion object {

        private const val ACCOUNT_NAME: String = BuildConfig.SYNC_AUTH
        private const val ACCOUNT_TYPE: String = BuildConfig.SYNC_AUTH

        private const val AUTHORITY: String = BuildConfig.SYNC_AUTH

        private var sSyncAdapter: SyncAdapter? = null
        private val sSyncAdapterLock = Any()

        @Suppress("unused")
        fun Context.createSyncAccount(): Account? {
            return try {
                val accountManager = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
                Account(ACCOUNT_NAME, ACCOUNT_TYPE).also { account ->
                    val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
                    if (accounts.isEmpty()) {
                        if (accountManager.addAccountExplicitly(account, "", Bundle())) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                accountManager.notifyAccountAuthenticated(account)
                            }
                            setIsSyncable(account, AUTHORITY, 1)
                            setSyncAutomatically(account, AUTHORITY, true)
                        } else {
                            Timber.e("Can not create sync account")
                        }
                    } else {
                        Timber.d("Account already exists.")
                    }
                }
            } catch (e: Throwable) {
                Timber.e(e)
                null
            }
        }

        fun requestSync(account: Account) {
            ContentResolver.requestSync(
                account,
                AUTHORITY,
                Bundle().apply {
                    putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true)
                    putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true)
                }
            )
        }

        fun Context.createAccountAndSync() {
            createSyncAccount()?.let { account ->
                requestSync(account)
            }
        }

    }

}
