/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.data.github.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
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

    override fun equals(other: Any?): Boolean =
        if (other !is Asset) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nodeId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + uploader.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + downloadCount.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + downloadUrl.hashCode()
        return result
    }

    val isAPK : Boolean get() = contentType
        .contentEquals("application/vnd.android.package-archive")

    val isDownloadable get() = state
        .contentEquals("uploaded") && downloadUrl.isNotEmpty()

}