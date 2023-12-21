/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import java.util.*

data class Cookie(
    val name: String,
    val value: String,
    val expires: Expires,
    val maxAge: Int?,
    val domain: Domain,
    val path: String = "/",
    val sameSite: SameSite = SameSite.LAX,
    val secure: Boolean = false,
    val httpOnly: Boolean = false
)

enum class SameSite {
    STRICT,
    LAX,
    NONE
}

sealed class Expires {
    data object Session : Expires()
    data class Date(val date: String) : Expires()
}

data class Domain(val domain: String, val includesSubdomains: Boolean)

fun String.toCookie(origin: String): Cookie {
    val attributes = this.split(";").map { it.trim() }
    val (name, value) = attributes[0].split("=")

    val path = attributes.getAttribute("path") ?: "/"
    val expires = attributes.getAttribute("expires").toExpires()
    val maxAge = attributes.getAttribute("max-age")?.toInt()
    val domain = when (val domainUrl = attributes.getAttribute("domain")) {
        null -> Domain(origin, false)
        else -> Domain(domainUrl, true)
    }
    val sameSite = attributes.getAttribute("samesite").toSameSite()
    val secure = attributes.any { it.lowercase(Locale.getDefault()) == "secure" }
    val httpOnly = attributes.any { it.lowercase(Locale.getDefault()) == "httponly" }
    return Cookie(name, value, expires, maxAge, domain, path, sameSite, secure, httpOnly)
}

private fun List<String>.getAttribute(attributeName: String) =
    this.find { it.startsWith("${attributeName}=", ignoreCase = true) }?.takeLastWhile { it != '=' }

private fun String?.toSameSite(): SameSite {
    return when (this?.lowercase(Locale.getDefault())) {
        "strict" -> SameSite.STRICT
        "lax" -> SameSite.LAX
        "none" -> SameSite.NONE
        else -> SameSite.LAX
    }
}

private fun String?.toExpires(): Expires {
    return when (this) {
        null -> Expires.Session
        else -> Expires.Date(this)
    }
}

val String.urlOrigin: String
    get() = this.substringAfter("://").substringBefore("/").substringBefore(":")
