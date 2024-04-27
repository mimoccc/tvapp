/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.updater

import com.skydoves.sandwich.ApiResponse
import org.mjdev.tvlib.data.github.remote.Release
import retrofit2.http.GET

interface AppUpdateService {

    @GET("releases")
    suspend fun releases(): ApiResponse<List<Release>>

}
