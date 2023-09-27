package org.mjdev.tvlib.webclient

import android.content.Context
import org.mjdev.tvlib.webclient.html.HtmlAnchor
import org.mjdev.tvlib.webclient.html.HtmlPage
import org.mjdev.tvlib.webclient.html.HtmlVideo
import java.util.*

@Suppress("unused")
class WebScrapper(
    val context: Context,
    urls: List<String> = listOf("https://prehraj.to/hledej/filmy"),
    private val block: (page: HtmlPage?, data: Any?) -> Unit
) {

    private val webClient: WebClient by lazy {
        WebClient(context)
    }

    private val parsePages: Deque<String> = LinkedList()

    init {
        parsePages.addAll(urls)
        parseNextPage()
    }

    private fun parseNextPage() {
        if (parsePages.size > 0) {
            parsePageFromUrl(parsePages.pop())
        }
    }

    @Synchronized
    fun parsePageFromUrl(pageUrl: String?) {
        if (pageUrl != null) {
            webClient.getPage(pageUrl) { page, _ ->
                if (page != null) {
                    parsePage(page, page.videos, page.anchors)
                }
            }
        }
    }

    @Synchronized
    fun parsePage(page: HtmlPage, videos: List<HtmlVideo>, anchors: List<HtmlAnchor>) {
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