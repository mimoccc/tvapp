/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper

import android.content.Context
import androidx.core.net.toUri
import com.gargoylesoftware.htmlunit.BrowserVersion
import org.mjdev.tvlib.webscrapper.fetcher.BlockingFetcher
import org.mjdev.tvlib.webscrapper.base.Request
import org.mjdev.tvlib.webscrapper.base.response
import org.mjdev.tvlib.webscrapper.base.skrape
import org.jsoup.nodes.Document
import org.mjdev.tvlib.webscrapper.adblock.IAdBlocker
import org.mjdev.tvlib.webscrapper.adblock.NoAdBlock
import org.mjdev.tvlib.webscrapper.fetcher.BrowserFetcher
import org.mjdev.tvlib.webscrapper.parser.htmlDocument
import timber.log.Timber

class WebVideoScrapper(
    private val context: Context,
    private val baseUrls: List<String>,
    private val addBlock: IAdBlocker = NoAdBlock(),
    private val onVideoFound: (video: Video) -> Unit = {},
    private val onFinish: (videos: List<Video>) -> Unit = {}
) {
    private val fetcher: BlockingFetcher<Request> by lazy {
        BrowserFetcher(context, addBlock)
    }
    private val links: ArrayDeque<String> = ArrayDeque<String>().apply {
        addAll(baseUrls)
    }
    private val videos = mutableListOf<Video>()

    fun start() {
        while (links.isNotEmpty()) {
            val uri = links.removeFirstOrNull()
            if (uri != null) {
                Timber.e("Parsing :  $uri")
                parse(uri)
            }
        }
        onFinish(videos)
    }

    private fun parse(uri: String) {
        try {
            skrape(fetcher) {
                request {
                    url = uri
                    sslRelaxed = true
                    followRedirects = true
                    timeout = 5000
                    userAgent = BrowserVersion.BEST_SUPPORTED.userAgent
                }
                response {
                    htmlDocument {
                        eachVideo.forEach { src ->
                            val url = normalizedUrl(baseUri, src)
                            val title = parseTitle(document, url)
                            val category = parseCategory(document, url)
                            val thumb = parseThumb(document, url)
                            val video = Video(
                                title = title,
                                category = category,
                                url = url,
                                thumb = thumb
                            )
                            videos.add(video)
                            onVideoFound(video)
                        }
                        eachLink.values.forEach { href ->
                            if ((!href.startsWith("#")) && (href != "/") && (!href.contentEquals(
                                    baseUri
                                ))
                            ) {
                                links.add(normalizedUrl(baseUri, href))
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // todo : intelligent
    @Suppress("UNUSED_PARAMETER")
    private fun parseThumb(document: Document, url: String): String {
        return url
    }

    // todo : intelligent
    @Suppress("UNUSED_PARAMETER")
    private fun parseCategory(document: Document, url: String): String {
        return document.location().toUri().authority.toString()
    }

    // todo : intelligent
    @Suppress("UNUSED_PARAMETER")
    private fun parseTitle(document: Document, url: String): String {
        val auth = document.location().toUri().authority.toString()
        return document.title().replace(auth, "", ignoreCase = true)
    }

    private fun normalizedUrl(base: String, href: String): String = href.let {
        if (it.startsWith("/")) it.replaceFirstChar { "" } else it
    }.let {
        if (base.contentEquals(it)) base else "$base/$it"
    }

    data class Video(
        val url: String,
        val title: String,
        val category: String,
        val thumb: String
    )
}