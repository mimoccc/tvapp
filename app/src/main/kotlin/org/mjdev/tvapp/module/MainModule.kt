/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.helpers.http.CacheInterceptor
import org.mjdev.tvlib.helpers.http.NetworkConnectionInterceptor
import org.mjdev.tvlib.helpers.http.UserAgentInterceptor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.webscrapper.adblock.AdBlockInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val MainModule = DI.Module("MainModule") {
    val baseUrl = BuildConfig.IPTV_API_URL

    bind<String>() with singleton { String() }

    bindSingleton<NetworkConnectivityService> { NetworkConnectivityService(instance()) }
    bindSingleton<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }
    bindSingleton<CacheInterceptor> { CacheInterceptor() }
    bindSingleton<AdBlockInterceptor> { AdBlockInterceptor(instance()) }
    bindSingleton<UserAgentInterceptor> { UserAgentInterceptor(instance()) }
    bindSingleton<NetworkConnectionInterceptor> { NetworkConnectionInterceptor(instance()) }
    bindSingleton<Moshi> { Moshi.Builder().build() }
    bindSingleton<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(instance<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    bindSingleton<ApiService> {
        val retrofit: Retrofit = instance()
        retrofit.create(ApiService::class.java)
    }

    bindProvider<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    bindProvider<AudioCursor> { AudioCursor(instance()) }
    bindProvider<VideoCursor> { VideoCursor(instance()) }
    bindProvider<PhotoCursor> { PhotoCursor(instance()) }
    bindProvider<OkHttpClient> {
        OkHttpClient.Builder().apply {
            addNetworkInterceptor(instance<NetworkConnectionInterceptor>())
            addNetworkInterceptor(instance<UserAgentInterceptor>())
            addNetworkInterceptor(instance<AdBlockInterceptor>())
            addNetworkInterceptor(instance<CacheInterceptor>())
            if (BuildConfig.DEBUG) {
                addInterceptor(instance<HttpLoggingInterceptor>())
            }
        }.build()
    }
}