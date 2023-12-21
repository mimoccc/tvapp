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
import java.util.*

interface Authentication {
    fun toHeaderValue(): String
    @RequiresApi(Build.VERSION_CODES.O)
    fun String.base64Encoded(): String = Base64.getEncoder().encodeToString(toByteArray()).orEmpty()
}

@Suppress("unused")
class BasicAuth(
    private var username: String = "",
    private var password: String = ""
) : Authentication {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun toHeaderValue(): String = "Basic ${"$username:$password".base64Encoded()}"
}

@Suppress("unused")
class OAuth2(
    var clientId: String = "",
    var clientSecret: String = ""
) : Authentication {
    override fun toHeaderValue(): String = "Bearer TODO"
}

@Suppress("unused")
fun basic(init: BasicAuth.() -> Unit): Authentication = BasicAuth().also(init)

@Suppress("unused")
fun oauth2(init: OAuth2.() -> Unit): Authentication = OAuth2().also(init)
