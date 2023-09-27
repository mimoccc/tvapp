package org.mjdev.tvlib.webclient.cookies

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

@Suppress("unused")
class CookiesManager : HashMap<String, ArrayList<Cookie>>(), CookieJar {

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return url.toString().let { u ->
            this[u] ?: emptyList()
        }
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        url.toString().let { u ->
            if (!containsKey(u)) {
                put(u, ArrayList())
            }
            this[u]?.addAll(cookies)
        }
    }

}