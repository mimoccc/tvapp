/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.gradle.util.GradleVersion
import java.util.EnumMap
import java.util.concurrent.TimeUnit

class GradleUpdateChecker(
    val enabled: Boolean = true,
    gradleVersionsApiBaseUrl: String,
) {

    init {
        if (enabled) {
            fetch(gradleVersionsApiBaseUrl)
        }
    }

    fun getRunningGradleVersion(): ReleaseStatus.Available =
        ReleaseStatus.Available(GradleVersion.current())

    fun getCurrentGradleVersion(): ReleaseStatus? =
        cacheMap[GradleReleaseChannel.CURRENT]

    fun getReleaseCandidateGradleVersion(): ReleaseStatus? =
        cacheMap[GradleReleaseChannel.RELEASE_CANDIDATE]

    fun getNightlyGradleVersion(): ReleaseStatus? =
        cacheMap[GradleReleaseChannel.NIGHTLY]

    companion object {
        private val cacheMap = EnumMap<GradleReleaseChannel, ReleaseStatus>(
            GradleReleaseChannel::class.java
        )
        private const val CLIENT_TIME_OUT = 15_000L
        private val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .build()
        private val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        private class VersionSite {
            var version: String? = null
        }

        private fun fetch(gradleVersionsApiBaseUrl: String) {
            for (it in GradleReleaseChannel.values()) {
                try {
                    // todo remove
                    println("Getting gradle data from : $gradleVersionsApiBaseUrl + ${it.id}")
                    // todo
                    client.newCall(
                        Request.Builder()
                            .url(gradleVersionsApiBaseUrl + it.id)
                            .build()
                    ).execute().use { response ->
                        response.body.source().let { body ->
                            val version = moshi
                                .adapter(VersionSite::class.java)
                                .fromJson(body)?.version.orEmpty()
                            if (version.isNotEmpty()) {
                                cacheMap[it] =
                                    ReleaseStatus.Available(GradleVersion.version(version))
                            } else {
                                cacheMap[it] =
                                    ReleaseStatus.Unavailable
                            }
                        }
                    }
                } catch (e: Exception) {
                    cacheMap[it] = ReleaseStatus.Failure(e.message.orEmpty())
                }
            }
        }
    }
}
