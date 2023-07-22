/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.api

import org.mjdev.tvapp.data.remote.Category
import org.mjdev.tvapp.data.remote.Channel
import org.mjdev.tvapp.data.remote.Country
import org.mjdev.tvapp.data.remote.Epg
import org.mjdev.tvapp.data.remote.Language
import org.mjdev.tvapp.data.remote.Stream
import retrofit2.Call
import retrofit2.http.GET

@Suppress("unused")
interface ApiService {

    @GET("channels.json")
    fun channels(): Call<List<Channel>>

    @GET("streams.json")
    fun streams(): Call<List<Stream>>

    @GET("guides.json")
    fun epg(): Call<List<Epg>>

    @GET("categories.json")
    fun categories(): Call<List<Category>>

    @GET("languages.json")
    fun languages(): Call<List<Language>>

    @GET("countries.json")
    fun countries(): Call<List<Country>>

}