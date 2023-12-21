/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import java.net.URI

data class Request(
    var method: Method = Method.GET,

    var url: String = UrlBuilder().toString(),
    var proxy: ProxyBuilder? = null,

    var userAgent: String = "Mozilla/5.0 skrape.it",
    var headers: Map<String, String> = emptyMap(),
    var cookies: Map<String, String> = emptyMap(),
    var timeout: Int = 5000,
    var followRedirects: Boolean = true,
    var authentication: Authentication? = null,
    var body: String? = null,

    var sslRelaxed: Boolean = false
) {
    fun url(init: UrlBuilder.() -> Unit) {
        val formerUrl = URI(url)
        url = UrlBuilder().apply {
            protocol = protocol.findOrDefault(formerUrl.scheme)
            host = formerUrl.host
            port = formerUrl.port
            path = formerUrl.path
            fragment = formerUrl.fragment
            formerUrl.query?.let { queryParam { +it } }
        }.also(init).toString()
    }

    @Suppress("unused")
    fun urlBuilder(init: UrlBuilder.() -> Unit): String {
        return UrlBuilder().also(init).toString()
    }

    @Suppress("unused")
    fun proxyBuilder(init: ProxyBuilder.() -> Unit): ProxyBuilder = ProxyBuilder().also(init)

    @Suppress("unused")
    fun body(init: BodyBuilder.() -> Unit) {
        val invokedBody = BodyBuilder().also(init)
        body = invokedBody.data
        headers += "Content-Type" to invokedBody.contentType
    }
}

enum class Method {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    HEAD
}
