package org.mjdev.tvlib.webclient.cache

import android.text.TextUtils
import java.util.Locale

class CacheExtensionConfig {

    private val statics: ArrayList<*> = ArrayList<Any?>(STATIC)
    private val noCache: ArrayList<*> = ArrayList<Any?>(NO_CACHE)

    fun isMedia(extension: String): Boolean {
        if (TextUtils.isEmpty(extension)) return false
        return if (NO_CACHE.contains(extension)) true
        else noCache.contains(extension.lowercase(Locale.getDefault()).trim { it <= ' ' })
    }

    fun canCache(extension: String): Boolean {
        var ext = extension
        if (TextUtils.isEmpty(ext)) return false
        ext = ext.lowercase(Locale.getDefault()).trim { it <= ' ' }
        return if (STATIC.contains(ext)) true else statics.contains(extension)
    }

//    fun addExtension(extension: String): CacheExtensionConfig {
//        add(statics, extension)
//        return this
//    }

//    fun removeExtension(extension: String): CacheExtensionConfig {
//        remove(statics, extension)
//        return this
//    }

    fun isHtml(extension: String): Boolean {
        if (TextUtils.isEmpty(extension)) {
            return false
        }
        return extension.lowercase(Locale.getDefault()).contains("html") ||
                extension.lowercase(Locale.getDefault()).contains("htm")
    }

    @Suppress("unused")
    fun clearAll() {
        clearDiskExtension()
    }

    private fun clearDiskExtension() {
        statics.clear()
    }

    companion object {

        private val STATIC = mutableListOf(
            "html", "htm", "js", "ico",
            "css", "png", "jpg", "jpeg",
            "gif", "bmp", "ttf", "woff",
            "woff2", "otf", "eot", "svg",
            "xml", "swf", "txt", "text",
            "conf", "webp",
        )

        private val NO_CACHE = mutableListOf(
            "mp4", "mp3", "ogg",
            "avi", "wmv", "flv",
            "rmvb", "3gp",
        )

//        fun addGlobalExtension(extension: String) {
//            add(STATIC, extension)
//        }

//        fun removeGlobalExtension(extension: String) {
//            remove(STATIC, extension)
//        }

//        private fun add(set: ArrayList<*>, extension: String) {
//            if (TextUtils.isEmpty(extension)) {
//                return
//            }
//            val ext = extension.replace(".", "")
//                .lowercase(Locale.getDefault())
//                .trim { it <= ' ' }
//            set.add(ext)
//        }

//        private fun remove(set: ArrayList<*>, extension: String) {
//            if (TextUtils.isEmpty(extension)) {
//                return
//            }
//            set.remove(extension.replace(".", "").lowercase(Locale.getDefault()).trim { it <= ' ' })
//        }
    }
}