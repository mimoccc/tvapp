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

@Suppress("MemberVisibilityCanBePrivate")
@JsonClass(generateAdapter = true)
data class Release(

    @Json(name = "id")
    val id: Long = 0L,

    @Json(name = "url")
    val url: String = "",

    @Json(name = "assets_url")
    val assetsUrl: String = "",

    @Json(name = "upload_url")
    val uploadUrl: String = "",

    @Json(name = "html_url")
    val htmlUrl: String = "",

    @Json(name = "author")
    val author: User = User(),

    @Json(name = "node_id")
    val nodeId: String = "",

    @Json(name = "tag_name")
    val tagName: String = "",

    @Json(name = "target_commitish")
    val targetCommitish: String = "",

    @Json(name = "name")
    val name: String = "",

    @Json(name = "draft")
    val draft: Boolean = false,

    @Json(name = "prerelease")
    val preRelease: Boolean = false,

    @Json(name = "created_at")
    val createdAt: String = "",

    @Json(name = "published_at")
    val publishedAt: String = "",

    @Json(name = "assets")
    val assets: List<Asset> = listOf(),

    @Json(name = "tarball_url")
    val tarUrl: String = "",

    @Json(name = "zipball_url")
    val zipUrl: String = ""

) {
    val hasAssets: Boolean get() = assets.isNotEmpty()

    val hasAPK: Boolean
        get() = hasAssets && assets.any { asset ->
            asset.isAPK && asset.isDownloadable
        }

    val apkAsset: Asset?
        get() = assets.firstOrNull { asset -> asset.isAPK }

}