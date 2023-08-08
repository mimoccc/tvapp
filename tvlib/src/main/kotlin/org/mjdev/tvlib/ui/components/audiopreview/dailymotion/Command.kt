/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Command (
    @Json(name = "methodName")
    var methodName: String = "",
    @Json(name = "params")
    var params: Array<out Any> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Command
        if (methodName != other.methodName) return false
        if (!params.contentEquals(other.params)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = methodName.hashCode()
        result = 31 * result + params.contentHashCode()
        return result
    }
}