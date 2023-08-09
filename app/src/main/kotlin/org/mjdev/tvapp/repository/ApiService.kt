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
    fun channels(): ApiResponse<List<Channel>>

    @GET("streams.json")
    fun streams(): ApiResponse<List<Stream>>

    @GET("guides.json")
    fun epg(): ApiResponse<List<Epg>>

    @GET("categories.json")
    fun categories(): ApiResponse<List<Category>>

    @GET("languages.json")
    fun languages(): ApiResponse<List<Language>>

    @GET("countries.json")
    fun countries(): ApiResponse<List<Country>>

}