/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.repository

import com.skydoves.sandwich.ApiResponse
import org.mjdev.tvapp.data.remote.Category
import org.mjdev.tvapp.data.remote.Channel
import org.mjdev.tvapp.data.remote.Country
import org.mjdev.tvapp.data.remote.Epg
import org.mjdev.tvapp.data.remote.Language
import org.mjdev.tvapp.data.remote.Stream
import retrofit2.http.GET

@Suppress("unused")
interface ApiService {

    @GET("channels.json")
    suspend fun channels(): ApiResponse<List<Channel>>

    @GET("streams.json")
    suspend fun streams(): ApiResponse<List<Stream>>

    @GET("guides.json")
    suspend fun epg(): ApiResponse<List<Epg>>

    @GET("categories.json")
    suspend fun categories(): ApiResponse<List<Category>>

    @GET("languages.json")
    suspend fun languages(): ApiResponse<List<Language>>

    @GET("countries.json")
    suspend fun countries(): ApiResponse<List<Country>>

}