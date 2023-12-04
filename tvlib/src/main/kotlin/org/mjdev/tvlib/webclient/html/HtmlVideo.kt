package org.mjdev.tvlib.webclient.html

import android.webkit.MimeTypeMap
import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

@Suppress("unused", "SameParameterValue", "MemberVisibilityCanBePrivate")
class HtmlVideo(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "video"

    val playsInline: String get() = attr("playsinline")
    val poster: String get() = attr("poster")
    val controlsList: String get() = attr("controlslist")
    val preload: String get() = attr("preload")
    val tabindex: String get() = attr("tabindex")
    val controls: String get() = attr("controls")

    val sources: List<String>
        get() = mutableListOf<String>().apply {
            if (attr("src").isNotEmpty()) add(attr("src"))
            childs.filterIsInstance<HtmlSource>().let { list ->
                if (list.isEmpty()) {
                    listOf(attr("src"))
                } else {
                    list.map { htmlSource ->
                        htmlSource.src
                    }
                }
            }.also { list ->
                addAll(list)
            }
        }

    val videoStreams: List<String>
        get() = sources.filter {
            isVideoLink(it)
        }

    val hasStreams: Boolean get() = videoStreams.isNotEmpty()

    companion object {

        private val VIDEO_EXTENSIONS = listOf(
            "mp4",
            "m3u8",
            "hls",
            "avi",
            "mpg",
            "mpeg",
            "avi"
        )

        private val VIDEO_EXCLUDE_EXTENSIONS = listOf(
            "jpg",
            "jpeg",
            "js",
            "ico",
            "png",
            "webp",
            "css"
        )

        fun String.nonVideoExcluded(): Boolean {
            val ext = MimeTypeMap.getFileExtensionFromUrl(this)
            return !VIDEO_EXCLUDE_EXTENSIONS.contains(ext)
        }

        fun <T : List<String>> T.sortVideosByImportance(): List<String> {
            val pairs = map {
                Pair(indexOf(VIDEO_EXTENSIONS, it), it)
            }
            val filtered = pairs.filter {
                (it.first >= 0) && it.second.nonVideoExcluded()
            }
            val sorted = filtered.sortedBy { p -> p.first }
            return sorted.map { p -> p.second }
        }

        private fun indexOf(
            where: List<String>,
            what: String
        ): Int {
            for (i in where.indices) {
                if (what.contains(where[i])) {
                    return i
                }
            }
            return -1
        }

        fun isVideoLink(url: String?): Boolean {
            val isExtOK: Boolean = url?.nonVideoExcluded() ?: true
            return isExtOK && url?.let { u ->
                VIDEO_EXTENSIONS.map { ext -> (u.contains(ext)) }.isNotEmpty()
            } ?: false
        }
    }
}