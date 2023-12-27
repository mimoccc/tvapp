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
    private val fetcher by lazy { BrowserFetcher(context, addBlock) }
    private val links = ArrayDeque<String>().apply { addAll(baseUrls) }
    private val scannedLinks = mutableListOf<String>()
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
        scannedLinks.add(uri)
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
                            val video = Video(
                                title = parseTitle(document, src),
                                category = parseCategory(document, src),
                                url = src,
                                thumb = parseThumb(document, src)
                            )
                            videos.add(video)
                            onVideoFound(video)
                        }
                        eachLink.values.forEach { href ->
                            when {
                                href.startsWith("javascript:") -> {}
                                href.contentEquals(baseUri) -> {}
                                scannedLinks.contains(href) -> {}
                                else -> links.add(href)
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
        return document.title().replace(auth, "", ignoreCase = true).trim()
    }

    data class Video(
        val url: String,
        val title: String,
        val category: String,
        val thumb: String
    )

}
