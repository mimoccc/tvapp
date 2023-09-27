package org.mjdev.tvlib.webclient.cache

import android.webkit.MimeTypeMap
import java.util.Locale

@Suppress("Deprecation")
object MimeTypeMapUtils {

    fun getFileExtensionFromUrl(url: String?): String {
        var burl = url?.toLowerCase(Locale.ROOT)
        if (!burl.isNullOrEmpty()) {
            val fragment = burl.lastIndexOf('#')
            if (fragment > 0) burl = burl.substring(0, fragment)
            val query = burl.lastIndexOf('?')
            if (query > 0) burl = burl.substring(0, query)
            val filenamePos = burl.lastIndexOf('/')
            val filename = if (0 <= filenamePos) burl.substring(filenamePos + 1) else burl
            if (filename.isNotEmpty()) {
                val dotPos = filename.lastIndexOf('.')
                if (0 <= dotPos) return filename.substring(dotPos + 1)
            }
        }
        return ""
    }

    fun getMimeTypeFromUrl(url: String?): String? {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(getFileExtensionFromUrl(url))
    }

//    fun getMimeTypeFromExtension(extension: String?): String? {
//        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
//    }

}