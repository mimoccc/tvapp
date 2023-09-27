package org.mjdev.tvlib.webclient.cache

import timber.log.Timber
import java.io.File
import java.net.MalformedURLException
import java.net.URL

@Suppress("unused")
class DynamicCacheLoader {

    fun getResByUrl(file: File?, url: String?): File? {
        val uPath = getUrlPath(url)
        if (file?.isDirectory == true) {
            val files = file.listFiles() ?: emptyArray()
            for (child in files) {
                if (child.isDirectory) {
                    val targetFile = getResByUrl(child, url)
                    if (targetFile != null) {
                        return targetFile
                    }
                } else {
                    if (uPath.endsWith(child.name))
                        return child
                }
            }
        } else {
            if (uPath.endsWith(file?.name.toString()))
                return file
        }
        return null
    }

    private fun getUrlPath(url: String?): String {
        var uPath = ""
        try {
            val u = URL(url)
            uPath = u.path
            if (uPath.startsWith("/")) {
                if (uPath.length == 1) return uPath
                uPath = uPath.substring(1)
            }
        } catch (e: MalformedURLException) {
            Timber.e(e)
        }
        return uPath
    }

    companion object {
        val instance: DynamicCacheLoader by lazy {
            DynamicCacheLoader()
        }
    }

}