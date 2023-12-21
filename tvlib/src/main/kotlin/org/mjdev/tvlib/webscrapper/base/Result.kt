/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

@Suppress("unused")
class Result(
    val responseBody: String,
    val responseStatus: Status,
    val contentType: ContentType,
    val headers: Map<String, String>,
    val baseUri: String = "",
    val cookies: List<Cookie>,
) {
    infix fun httpHeader(name: String): String? = this.headers[name]

    fun httpHeaders(init: Map<String, String>.() -> Unit): Map<String, String> {
        headers.apply(init)
        return headers
    }

    fun httpHeader(name: String, init: String?.() -> Unit): String? {
        val header = headers[name]
        header.apply(init)
        return header
    }

    fun <T> status(init: Status.() -> T): T {
        return responseStatus.init()
    }

    fun cookies(init: List<Cookie>.() -> Unit): List<Cookie> =
        cookies.apply(init)

    data class Status(
        val code: Int,
        val message: String,
    )
}

typealias ContentType = String?
