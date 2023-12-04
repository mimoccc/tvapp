package org.mjdev.tvlib.webclient

import android.content.Context
import org.mjdev.tvlib.webclient.html.HtmlAnchor
import org.mjdev.tvlib.webclient.html.HtmlPage
import org.mjdev.tvlib.webclient.html.HtmlVideo
import java.util.*
import kotlin.collections.ArrayDeque

@Suppress("unused")
class WebScrapper(
    context: Context,
    urls: List<String> = listOf(),
    private val block: (page: HtmlPage?, videoURL: String?) -> Unit
) {

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private val webClient by lazy {
        WebClient(context) { page: HtmlPage?, resources: List<String>? ->
            if (page != null) {
                parsePage(page, page.videos, page.anchors)
            }
        }
    }

    private val parsePages: ArrayDeque<String> = ArrayDeque<String>().apply {
        addAll(urls)
    }

    @Throws(IllegalStateException::class)
    fun start() {
        if (parsePages.size > 0) {
            parseNextPage()
        } else {
            throw (IllegalStateException("No url to parse"))
        }
    }

    private fun parseNextPage() {
        if (parsePages.size > 0) {
            parsePageFromUrl(parsePages.removeFirst())
        }
    }

    private fun parsePageFromUrl(pageUrl: String?) {
        if (pageUrl != null) {
            webClient.getPage(pageUrl)
        }
    }

    private fun parsePage(page: HtmlPage, videos: List<HtmlVideo>, anchors: List<HtmlAnchor>) {
        anchors.map { anchor ->
            anchor.fullHref(page)
        }.forEach { href ->
            if (href.isNotEmpty()) {
                parsePages.add(href)
            }
        }
        parseVideos(page, videos)
        parseNextPage()
    }

    private fun parseVideos(page: HtmlPage, videos: List<HtmlVideo>) {
        if (videos.isNotEmpty()) {
            videos.forEach { video ->
                video.videoStreams.forEach { streamURL ->
                    if (streamURL.isNotEmpty()) {
                        block.invoke(page, streamURL)
                    }
                }
            }
        }
    }

}