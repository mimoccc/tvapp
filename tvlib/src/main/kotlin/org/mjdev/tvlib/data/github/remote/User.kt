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
data class User(

    @Json(name = "id")
    val id: Long = 0L,

    @Json(name = "node_id")
    val nodeId: String = "",

    @Json(name = "login")
    val login: String = "",

    @Json(name = "avatar_url")
    val avatarUrl: String = "",

    @Json(name = "gravatar_id")
    val gravatarId: String = "",

    @Json(name = "url")
    val url: String = "",

    @Json(name = "html_url")
    val htmlUrl: String = "",

    @Json(name = "followers_url")
    val followersUrl: String = "",

    @Json(name = "following_url")
    val followingUrl: String = "",

    @Json(name = "gists_url")
    val gistsUrl: String = "",

    @Json(name = "starred_url")
    val starredUrl: String = "",

    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String = "",

    @Json(name = "organizations_url")
    val organizationsUrl: String = "",

    @Json(name = "repos_url")
    val reposUrl: String = "",

    @Json(name = "events_url")
    val eventsUrl: String = "",

    @Json(name = "received_events_url")
    val receivedEventsUrl: String = "",

    @Json(name = "type")
    val type: String = "",

    @Json(name = "site_admin")
    val siteAdmin: Boolean = false

) {
    override fun equals(other: Any?): Boolean =
        if (other !is User) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nodeId.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + avatarUrl.hashCode()
        result = 31 * result + gravatarId.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + htmlUrl.hashCode()
        result = 31 * result + followersUrl.hashCode()
        result = 31 * result + followingUrl.hashCode()
        result = 31 * result + gistsUrl.hashCode()
        result = 31 * result + starredUrl.hashCode()
        result = 31 * result + subscriptionsUrl.hashCode()
        result = 31 * result + organizationsUrl.hashCode()
        result = 31 * result + reposUrl.hashCode()
        result = 31 * result + eventsUrl.hashCode()
        result = 31 * result + receivedEventsUrl.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + siteAdmin.hashCode()
        return result
    }
}
