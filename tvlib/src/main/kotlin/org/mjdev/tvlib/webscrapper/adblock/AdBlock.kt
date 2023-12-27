/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.adblock

import android.content.Context
import android.net.Uri
import org.mjdev.tvlib.extensions.StringExt.removeComments
import org.mjdev.tvlib.extensions.StringExt.removeEmptyLines
import org.mjdev.tvlib.extensions.StringExt.trimLines
import org.mjdev.tvlib.helpers.http.MimeTypeMapUtils
import timber.log.Timber
import java.io.File
import java.io.InputStream
import java.net.URL

@Suppress("unused")
class AdBlock(
    inputStream: InputStream,
    private val blockMimeTypes: Boolean = false
) : IAdBlocker {

    constructor(file: File) : this(file.inputStream())

    constructor(context: Context, fileName: String) : this(context.assets.open(fileName))

    constructor(context: Context) : this(context, "adblock.txt")

    private var hosts = mutableListOf<String>()
    private val urls = mutableListOf<String>()

    // todo may be another ime types
    private val allowedMimeTypes = mutableListOf(
        "text/html",
        "text/plain",
        "text/javascript",
        "application/javascript",
        "video/mp4",
        "application/json",
    )

    init {
        try {
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
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    @Synchronized
    override fun isBlocked(reqUrl: String?): Boolean {
        val host = try {
            Uri.parse(reqUrl).host
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
        Timber.d("host: $host")
        if (blockMimeTypes) {
            val mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(reqUrl)
            Timber.d("mimetype: $mimeType")
            if (mimeType?.let { allowedMimeTypes.contains(it) } == false) {
                return true
            }
        }
        if (host?.let { h -> hosts.contains(h) } == true) return true
        return reqUrl?.let { url ->
            urls.any { blockedURL -> url.startsWith(blockedURL) }
        } ?: false
    }

    override fun isBlocked(reqUrl: URL?): Boolean =
        isBlocked(reqUrl.toString())

    override fun isBlocked(reqUrl: Uri?): Boolean =
        isBlocked(reqUrl.toString())

}