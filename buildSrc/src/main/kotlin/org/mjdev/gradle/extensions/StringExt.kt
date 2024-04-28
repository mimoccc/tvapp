/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import java.io.File
import java.net.URLEncoder
import java.util.Locale
import kotlin.String

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

fun String.unCapitalize(): String {
    if (isNotEmpty()) {
        val firstChar = this[0]
        if (firstChar.isUpperCase()) {
            return buildString {
                val titleChar = firstChar.lowercase()
                if (titleChar != firstChar.lowercase()) {
                    append(titleChar)
                } else {
                    append(this@unCapitalize.substring(0, 1).lowercase())
                }
                append(this@unCapitalize.substring(1))
            }
        }
    }
    return this
}

fun String?.suffixToString() =
    (this ?: "").replace(".", "").capitalize()

fun String.writeToFile(path: String): File {
    val file = File(path)
    file.parentFile?.mkdirs()
    this.writeToFile(file)
    return file
}

fun String.writeToFile(file: File?): File? {
    file?.parentFile?.mkdirs()
    file?.writeText(this)
    return file
}

fun String.writeToFile(dir: File?, fileName: String): File {
    dir?.mkdirs()
    val file = File(dir, fileName)
    file.writeText(this)
    return file
}

fun String?.urlEncode(encoding: String = "utf-8"): String? {
    return this?.let { URLEncoder.encode(it, encoding) }
}

private val ESCAPES = object : HashMap<String, String>() {
    init {
        this["@"] = "\\@"
        this["?"] = "\\?"
        this["<"] = "&lt;"
        this[">"] = "&gt;"
        this["\'"] = "\\'"
        this["\""] = "\\\""
        this["&"] = "&amp;"
        this["..."] = "…"
    }
}

fun String.isCDATA(): Boolean {
    return this.contains("<![CDATA[", true)
}

fun String?.escapeXml(): String {
    return this?.let { s ->
        val isCData = s.isCDATA()
        val ret = StringBuilder().apply {
            s.forEach { c -> ESCAPES[c.toString()]?.let { ec -> append(ec) } ?: append(c) }
        }.toString()
        if (isCData)
            ret.replace("&lt;![CDATA[", "<![CDATA[").replace("]]&gt;", "]]>")
        else
            ret
    } ?: ""
}

fun String?.toPlayStoreUrl(): String {
    return (this ?: "").let { "https://play.google.com/store/apps/details?id=$it" }
}

fun List<String>.filterNotEmpty(): List<String> = filter { it.isNotEmpty() }
