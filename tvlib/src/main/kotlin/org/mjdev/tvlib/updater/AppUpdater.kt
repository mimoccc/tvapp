/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.updater

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.skydoves.sandwich.getOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.data.github.remote.Release
import org.mjdev.tvlib.network.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class AppUpdater(
    private val context: Context,
    private val githubUser: String = "mimoccc",
    private val githubRepository: String = "tvapp",
    private val repoName: String = AppUpdater::class.simpleName ?: "AppUpdater",
    private val keyName: String = repoName
) {

    private val service by lazy {
        updateService(githubUser, githubRepository)
    }

    private val prefs by lazy {
        context.getSharedPreferences(repoName, MODE_PRIVATE)
    }

    private val releases: Flow<List<Release>> = flow {
        emit(service.releases().getOrNull() ?: emptyList())
    }.flowOn(Dispatchers.IO)

    private val lastRelease: Flow<Release?> =
        releases.map {
            it.filter { r ->
                r.hasAPK
            }.minByOrNull { r ->
                r.publishedAt
            }
        }

    private val serverTimestamp: Flow<Long> =
        lastRelease.map { r ->
            r?.publishedAt?.asTimestamp ?: 0L
        }

    private val installedTimestamp: Flow<Long> = serverTimestamp.map { st ->
        prefs.getLong(keyName, st)
    }

    val isUpdateAvailable: Flow<Boolean> = serverTimestamp.map { st ->
        val lt = installedTimestamp.firstOrNull() ?: 0L
        if (lt == 0L) {
            prefs.edit().putLong(keyName, st).apply()
            false
        } else (st > lt)
    }

    @MainThread
    fun updateApp() {
        CoroutineScope(Dispatchers.IO).launch {
            releases.collectLatest { rs ->
                rs.filter { r ->
                    r.hasAPK
                }.maxByOrNull { r ->
                    r.publishedAt.asTimestamp
                }?.let { release ->
                    release.apkAsset?.downloadUrl
                }?.also { url ->
                    downloadFile(url)
                }
            }
        }
    }

    private fun downloadFile(url: String) {
        Intent(Intent.ACTION_VIEW).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            data = url.toUri()
        }.also { intent ->
            context.startActivity(intent)
        }
    }

    companion object {

        private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun okHttpClient(
            cacheInterceptor: CacheInterceptor = CacheInterceptor(),
            httpLoggingInterceptor: HttpLoggingInterceptor = httpLoggingInterceptor(),
        ): OkHttpClient = OkHttpClient.Builder().apply {
            addNetworkInterceptor(cacheInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()

        private fun retrofit(
            okHttpClient: OkHttpClient,
            baseUrl: String = "",
        ): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        fun updateService(
            githubUser: String = "mimoccc",
            githubRepository: String = "tvapp",
            baseUrl: String = "https://api.github.com/repos/$githubUser/$githubRepository/",
            retrofit: Retrofit = retrofit(okHttpClient(), baseUrl)
        ): AppUpdateService = retrofit.create(AppUpdateService::class.java)

        @Composable
        fun rememberAppUpdater(
            githubUser: String = "mimoccc",
            githubRepository: String = "tvapp",
        ): AppUpdater {
            val context = LocalContext.current
            return remember(context) {
                AppUpdater(context, githubUser, githubRepository)
            }
        }

        private val String.asTimestamp: Long
            get() = runCatching {
                SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.US
                ).parse(this)?.time ?: 0L
            }.getOrDefault(0L)
    }

}