/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import android.os.Build
import androidx.annotation.RequiresApi
import org.mjdev.tvlib.webscrapper.base.UrlBuilder.Protocol

data class UrlBuilder(
    /**
     * Defines the protocol of the URL the request is made against.
     * Defaults to HTTP.
     * @see Protocol for all possible values.
     */
    var protocol: Protocol = Protocol.HTTP,

    /**
     * Defines the hostname of the URL the request is made against.
     * Defaults to "localhost".
     */
    var host: String = "localhost",

    /**
     * Defines the port of the URL the request is made against.
     * Defaults to 8080.
     */
    var port: Int = 8080,

    /**
     * Defines the path of the URL the request is made against.
     */
    var path: String = "",

    var fragment: String? = null,
) {

    private var queryParam: String = ""

    fun queryParam(init: QueryParam.() -> Unit) {
        queryParam = QueryParam().also(init).toString()
    }

    enum class Protocol(val value: String) {
        HTTP("http"),
        HTTPS("https"),
        FTP("ftp"),
        FILE("file"),
        ;

        internal fun findOrDefault(value: String): Protocol =
            entries.find { it.value == value } ?: HTTP
    }

    override fun toString(): String = buildString {
        append("${protocol.value}://")
        append(host)
        append(if (port < 0) "" else ":$port")
        append(if (path.isNotBlank() && !path.startsWith("/")) "/$path" else path)
        append(fragment?.let { "#$it" } ?: "")
        append(queryParam)
    }
}

class QueryParam {
    private val params: MutableMap<String, String> = mutableMapOf()

    @RequiresApi(Build.VERSION_CODES.N)
    infix fun String.to(value: Any?) {
        // TODO: do not ignore entries with value null
        params.computeIfAbsent(this) {
            when (value) {
                null -> "null"
                is List<*> -> value.joinToString(separator = ",")
                else -> "$value"
            }
        }
    }

    private val keyOnlyParams: MutableList<String> = mutableListOf()
    operator fun String.unaryPlus() {
        keyOnlyParams += this
    }

    override fun toString(): String {
        return buildString {
            params.forEach { (key, value) ->
                append("&$key=$value")
            }
            keyOnlyParams.forEach {
                append("&$it")
            }
        }.replaceFirst("&", "?")
    }
}
