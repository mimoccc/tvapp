/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.adblock

import android.net.Uri
import org.mjdev.tvlib.webclient.cache.MimeTypeMapUtils
import org.mjdev.tvlib.extensions.StringExt.removeComments
import org.mjdev.tvlib.extensions.StringExt.removeEmptyLines
import org.mjdev.tvlib.extensions.StringExt.trimLines
import timber.log.Timber
import java.io.File
import java.io.InputStream
import java.net.URL

class AdBlock(inputStream: InputStream) {
    private var hosts = mutableListOf<String>()
    private val urls = mutableListOf<String>()

    // todo may be another
    private val allowedMimeTypes = mutableListOf(
        "text/html",
        "text/plain",
        "text/javascript",
        "application/javascript",
        "video/mp4",
        "application/json",
    )

    init {
        inputStream
            .bufferedReader()
            .readLines()
            .removeEmptyLines()
            .trimLines()
            .removeComments('#')
            .map { line ->
                when (line[0]) {
                    // host starts with '*'
                    '*' -> hosts.add(line.substring(1))
                    // url to block
                    else -> urls.add(line)
                }
            }
    }

    @Synchronized
    fun isBlocked(reqUrl: String?): Boolean {
        val host = try {
            Uri.parse(reqUrl).host
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
        Timber.d("host: $host")
        val mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(reqUrl)
        Timber.d("mimetype: $mimeType")
        if (mimeType?.let { allowedMimeTypes.contains(mimeType) } == false) return true
        if (host?.let { h -> hosts.contains(h) } == true) return true
        return reqUrl?.let { url ->
            urls.any { blockedURL -> url.startsWith(blockedURL) }
        } ?: false
    }

    fun isBlocked(reqUrl: URL?): Boolean = isBlocked(reqUrl.toString())

    fun isBlocked(reqUrl: Uri?): Boolean = isBlocked(reqUrl.toString())

    companion object {

        @Suppress("MemberVisibilityCanBePrivate")
        fun adBlock(
            fileWithData: File = File("file:///android_asset/adblock.txt")
        ) = AdBlock(fileWithData.inputStream())

        val instance by lazy { adBlock() }

    }

}