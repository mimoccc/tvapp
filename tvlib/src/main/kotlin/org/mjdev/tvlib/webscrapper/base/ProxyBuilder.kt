/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import java.net.InetSocketAddress
import java.net.Proxy

data class ProxyBuilder(
    var type: Proxy.Type = Proxy.Type.HTTP,
    var host: String = "",
    var port: Int = 0
) {
    @Suppress("unused")
    fun toProxy(): Proxy {
        if (host.isBlank() || port == 0) {
            return Proxy.NO_PROXY
        }
        return Proxy(type, InetSocketAddress(host, port))
    }
}
