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
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    private val keyName: String = repoName,
    private val isDebug: Boolean = BuildConfig.DEBUG,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {
    private val prefs by lazy {
        context.getSharedPreferences(repoName, MODE_PRIVATE)
    }

    private val releases: Flow<List<Release>> = flow {
        updateService(isDebug, githubUser, githubRepository).also { service ->
            emit(service.releases().getOrNull() ?: emptyList())
        }
    }

    private val lastRelease: Flow<Release?> = releases.map { releases ->
        releases.filter { r ->
            r.hasAPK
        }.maxByOrNull { r ->
            r.publishedAt
        }
    }

    private val serverTimestamp: Flow<Long> = lastRelease.map { r ->
        r?.publishedAt?.asTimestamp ?: 0L
    }

    private val installedTimestamp: Flow<Long> = flow {
        prefs.getLong(keyName, 0L).also { timestamp ->
            emit(timestamp)
        }
    }

    val isUpdateAvailable: Flow<Boolean> = serverTimestamp.map { st ->
        val lt = installedTimestamp.first()
        if (st > 0) (st > lt) else false
    }

    private fun storeTimestamp(timestamp: Long) {
        prefs.edit().apply {
            putLong(keyName, timestamp)
        }.apply()
    }

    @MainThread
    fun updateApp() = coroutineScope.launch {
        lastRelease.collectLatest { release ->
            if (release != null) {
                storeTimestamp(release.publishedAt.asTimestamp)
                if (release.apkAsset != null) {
                    downloadFile(release.apkAsset!!.downloadUrl)
                }
            }
        }
    }

    // todo better
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
            isDebug: Boolean = false,
            cacheInterceptor: CacheInterceptor = CacheInterceptor(),
//            adBlockInterceptor: AdBlockInterceptor = AdBlockInterceptor(),
            httpLoggingInterceptor: HttpLoggingInterceptor = httpLoggingInterceptor(),
        ): OkHttpClient = OkHttpClient.Builder().apply {
            addNetworkInterceptor(cacheInterceptor)
//            addNetworkInterceptor(adBlockInterceptor)
            if (isDebug) {
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
            isDebug: Boolean = false,
            githubUser: String = "mimoccc",
            githubRepository: String = "tvapp",
            baseUrl: String = "https://api.github.com/repos/$githubUser/$githubRepository/",
            retrofit: Retrofit = retrofit(okHttpClient(isDebug = isDebug), baseUrl)
        ): AppUpdateService = retrofit.create(AppUpdateService::class.java)

        @Composable
        fun rememberAppUpdater(
            githubUser: String = "mimoccc",
            githubRepository: String = "tvapp",
            isDebug: Boolean = BuildConfig.DEBUG
        ): AppUpdater = LocalContext.current.let { context ->
            remember(context) {
                AppUpdater(
                    context = context,
                    githubUser = githubUser,
                    githubRepository = githubRepository,
                    isDebug = isDebug
                )
            }
        }

        private val String.asTimestamp: Long
            get() = runCatching {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                    .parse(this)?.time ?: 0L
            }.getOrDefault(0L)
    }

}