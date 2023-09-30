/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.updater

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.skydoves.sandwich.getOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.data.github.remote.Release
import org.mjdev.tvlib.network.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.Date

// todo better logic
class AppUpdater(
    private val context: Context,
    private val githubUser: String = "mimoccc",
    private val githubRepository: String = "tvapp",
) {

    private val service by lazy {
        updateService(githubUser, githubRepository)
    }

    private val prefs by lazy {
        context.applicationContext.getSharedPreferences(
            AppUpdater::class.simpleName,
            Context.MODE_PRIVATE
        )
    }

    private val releases: Flow<List<Release>> = flow {
        emit(service.releases().getOrNull() ?: emptyList())
    }

    private val lastRelease
        get() = releases.map { list ->
            list.filter { it.hasAPK }.maxOfOrNull { it.publishedAt }
        }

    private val serverTimestamp: Long
        get() = runBlocking {
            lastRelease.firstOrNull()?.asTimestamp ?: 0L
        }

    private val installedTimestamp
        get() = prefs.getLong(AppUpdater::class.simpleName, serverTimestamp)

//    private val externalFile: File
//        get() = File(
//            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
//            "$githubRepository-update.apk"
//        )

    val isUpdateAvailable: Boolean get() = serverTimestamp > installedTimestamp

    @MainThread
    fun updateApp() {
        // todo
//        CoroutineScope(Dispatchers.IO).launch {
//            releases.collectLatest { rs ->
//                rs.filter { r ->
//                    r.hasAPK
//                }.maxByOrNull { r ->
//                    r.publishedAt.asTimestamp
//                }?.let { release ->
//                    release.apkAsset?.downloadUrl
//                }?.also { url ->
//                    downloadFile(url) {
//                        prefs.edit()
//                            .putLong(AppUpdater::class.simpleName, lastReleaseTimestamp)
//                            .apply()
////                        CoroutineScope(Dispatchers.Main).launch {
////                            startUpdate()
////                        }
//                    }
//                }
//            }
//        }
    }

    @AnyThread
    private fun downloadFile(
        url: String,
        finalizeBlock: () -> Unit
    ) {
        // todo simply open download yet, until finished
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
            finalizeBlock()
        } catch (e: ActivityNotFoundException) {
            Timber.e(e)
        }
        // todo
//        httpClient.download(url) { result ->
//            result.onSuccess { data ->
//                FileOutputStream(externalFile).apply {
//                    write(data)
//                    flush()
//                    close()
//                    finalizeBlock()
//                }
//            }.onFailure { e ->
//                Timber.e(e)
//            }
//        }
    }

//    @AnyThread
//    private suspend fun startUpdate() {
//        val packageInstaller = PackageInstaller.getInstance(context)
//        val apkUri = Uri.fromFile(externalFile)
//        val fileName = externalFile.name
//        try {
//            when (val result = packageInstaller.createSession(apkUri) {
//                confirmation = Confirmation.DEFERRED
//                installerType = InstallerType.SESSION_BASED
//                name = fileName
//                requireUserAction = false
//                notification {
//                    title = NotificationString.resource(R.string.title_app_update)
//                    contentText =
//                        NotificationString.resource(R.string.text_update_message, fileName)
//                    // todo icon =
//                }
//            }.await()) {
//                is SessionResult.Success -> Timber.d("Success")
//                is SessionResult.Error -> Timber.e(result.cause.message)
//            }
//        } catch (e: Exception) {
//            Timber.e(e)
//        }
//    }

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

        @Suppress("DEPRECATION")
        private val String.asTimestamp: Long
            get() = try {
                Date.parse(this)
            } catch (e: Exception) {
                Timber.e(e)
                0L
            }
    }

}