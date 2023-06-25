/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.api.MovieAPI
import org.mjdev.tvapp.base.network.NetworkConnectivityService
import org.mjdev.tvapp.base.network.NetworkConnectivityServiceImpl
import org.mjdev.tvapp.repository.IRepository
import org.mjdev.tvapp.repository.MovieRepository
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    private val isDebug = BuildConfig.DEBUG

    @Singleton
    @Provides
    fun providesNetworkConnectivityService(
        @ApplicationContext
        context: Context
    ): NetworkConnectivityService = NetworkConnectivityServiceImpl(context)

    @Singleton
    @Provides
    fun providesMovieRepository(movieApi: MovieAPI): IRepository = MovieRepository(movieApi)

    @Singleton
    @Provides
    fun providesMovieAPI() = MovieAPI()

//    @Singleton
//    @Provides
//    fun providesHttpLoggingInterceptor(
//    ) = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }

//    @Singleton
//    @Provides
//    fun providesCacheInterceptor() = CacheInterceptor()

//    @Singleton
//    @Provides
//    fun providesOkHttpClient(
//        cacheInterceptor: CacheInterceptor,
//        httpLoggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient = OkHttpClient.Builder().apply {
//        addNetworkInterceptor(cacheInterceptor)
//        if (isDebug) {
//            addInterceptor(httpLoggingInterceptor)
//        }
//    }.build()

//    @Singleton
//    @Provides
//    fun providesRetrofit(
//        okHttpClient: OkHttpClient
//    ): Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .build()

//    @Singleton
//    @Provides
//    fun providesApiService(
//        retrofit: Retrofit
//    ): ApiService = retrofit.create(ApiService::class.java)


//    @Singleton
//    @Provides
//    fun providesMoshi(): Moshi = Moshi.Builder().build()

//    @Singleton
//    @Provides
//    fun providesApiRepository(
//        apiService: ApiService
//    ): INetworkRepository = ApiRepository(apiService)

//    @Singleton
//    @Provides
//    fun provideDAO(
//        @ApplicationContext
//        context: Context
//    ): DAO = OpenHelperManager.getHelper(context, DAO::class.java)

}