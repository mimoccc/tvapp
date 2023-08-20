/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.data.github.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asset(

    @Json(name = "id")
    val id: Long = 0L,

    @Json(name = "node_id")
    val nodeId: String = "",

    @Json(name = "name")
    val name: String = "",

    @Json(name = "url")
    val url: String = "",

    @Json(name = "label")
    val label: String = "",

    @Json(name = "uploader")
    val uploader: User = User(),

    @Json(name = "author")
    val author: User = User(),

    @Json(name = "content_type")
    val contentType: String = "",

    @Json(name = "state")
    val state: String = "",

    @Json(name = "size")
    val size: Long = 0L,

    @Json(name = "download_count")
    val downloadCount: Long = 0L,

    @Json(name = "created_at")
    val createdAt: String = "",

    @Json(name = "updated_at")
    val updatedAt: String = "",

    @Json(name = "browser_download_url")
    val downloadUrl: String = ""

) {

    val isAPK : Boolean get() = contentType
        .contentEquals("application/vnd.android.package-archive")

    val isDownloadable get() = state
        .contentEquals("uploaded") && downloadUrl.isNotEmpty()

}