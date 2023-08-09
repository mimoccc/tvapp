/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("PrivatePropertyName")

package org.mjdev.tvapp.module

import android.content.ComponentName
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.activity.MainActivity
import org.mjdev.tvapp.repository.ApiService
import org.mjdev.tvlib.helpers.apps.AppsManager
import org.mjdev.tvlib.helpers.cursor.AudioCursor
import org.mjdev.tvlib.helpers.cursor.PhotoCursor
import org.mjdev.tvlib.helpers.cursor.VideoCursor
import org.mjdev.tvlib.network.CacheInterceptor
import org.mjdev.tvlib.network.NetworkConnectivityService
import org.mjdev.tvlib.network.NetworkConnectivityServiceImpl
import org.mjdev.tvapp.database.DAO
import org.mjdev.tvapp.repository.IMovieRepository
import org.mjdev.tvapp.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    private val isDebug = BuildConfig.DEBUG
    private val BASE_URL = BuildConfig.IPTV_API_URL


    @Singleton
    @Provides
    fun providesAppsManager(
        @ApplicationContext
        context: Context
    ): AppsManager = AppsManager(
        context, ComponentName(
            BuildConfig.APPLICATION_ID,
            MainActivity::class.java.name
        )
    )

    @Singleton
    @Provides
    fun providesAudioCursor(
        @ApplicationContext
        context: Context
    ): AudioCursor = AudioCursor(context)

    @Singleton
    @Provides
    fun providesVideoCursor(
        @ApplicationContext
        context: Context
    ): VideoCursor = VideoCursor(context)

    @Singleton
    @Provides
    fun providesPhotoCursor(
        @ApplicationContext
        context: Context
    ): PhotoCursor = PhotoCursor(context)

    @Singleton
    @Provides
    fun providesNetworkConnectivityService(
        @ApplicationContext
        context: Context
    ): NetworkConnectivityService = NetworkConnectivityServiceImpl(context)

    @Singleton
    @Provides
    fun providesMovieRepository(
        dao: DAO
    ): IMovieRepository = MovieRepository(dao)

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesCacheInterceptor() = CacheInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        cacheInterceptor: CacheInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addNetworkInterceptor(cacheInterceptor)
        if (isDebug) {
            addInterceptor(httpLoggingInterceptor)
        }
    }.build()

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideDAO(
        @ApplicationContext
        context: Context
    ): DAO = DAO(context)

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

}