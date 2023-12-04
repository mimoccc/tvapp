///*
// *  Copyright (c) Milan Jurkulák 2023.
// *  Contact:
// *  e: mimoccc@gmail.com
// *  e: mj@mjdev.org
// *  w: https://mjdev.org
// */
//
//package org.mjdev.tvlib.webscrapper
//
//import androidx.core.net.toUri
//import com.gargoylesoftware.htmlunit.BrowserVersion
//import it.skrape.core.htmlDocument
//import it.skrape.fetcher.BrowserFetcher
//import it.skrape.fetcher.response
//import it.skrape.fetcher.skrape
//import it.skrape.selects.DomTreeElement
//import org.jsoup.nodes.Document
//import timber.log.Timber
//
//class WebVideoScrapper(
//    private val baseUrls: List<String>,
//    private val onVideoFound: (video: Video) -> Unit = {},
//    private val onFinish: (videos: List<Video>) -> Unit = {}
//) {
//
//    private val links: ArrayDeque<String> = ArrayDeque<String>().apply {
//        addAll(baseUrls)
//    }
//
//    private val videos = mutableListOf<Video>()
//
//    fun start() {
//        while (links.isNotEmpty()) {
//            val uri = links.removeFirstOrNull()
//            if (uri != null) {
//                Timber.e("Parsing :  $uri")
//                parse(uri)
//            }
//        }
//        onFinish(videos)
//    }
//
//    private fun parse(uri: String) {
//        try {
//            skrape(BrowserFetcher) {
//                request {
//                    url = uri
//                    sslRelaxed = true
//                    followRedirects = true
//                    timeout = 5000
//                    userAgent = BrowserVersion.BEST_SUPPORTED.userAgent
//                }
//                response {
//                    htmlDocument {
//                        eachVideo.forEach { src ->
//                            val url = normalizedUrl("${document.baseUri()}/$src")
//                            val title = parseTitle(document, url)
//                            val category = parseCategory(document, url)
//                            val thumb = parseThumb(document, url)
//                            val video = Video(
//                                title = title,
//                                category = category,
//                                url = url,
//                                thumb = thumb
//                            )
//                            videos.add(video)
//                            onVideoFound(video)
//                        }
//                        eachLink.values.forEach { href ->
//                            links.add(normalizedUrl("${document.baseUri()}/$href"))
//                        }
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            Timber.e(e)
//        }
//    }
//
//    // todo : intelligent
//    @Suppress("UNUSED_PARAMETER")
//    private fun parseThumb(document: Document, url: String): String {
//        return url
//    }
//
//    // todo : intelligent
//    @Suppress("UNUSED_PARAMETER")
//    private fun parseCategory(document: Document, url: String): String {
//        val auth = document.location().toUri().authority.toString()
//        return auth
//    }
//
//    // todo : intelligent
//    @Suppress("UNUSED_PARAMETER")
//    private fun parseTitle(document: Document, url: String): String {
//        val auth = document.location().toUri().authority.toString()
//        return document.title().replace(auth, "", ignoreCase = true)
//    }
//
//    private fun normalizedUrl(u: String): String = u
//        .replace("//", "/")
//        .let { url ->
//            if ((!url.startsWith("https://")) && url.startsWith("https:/")) {
//                url.replace("https:/", "https://")
//            } else url
//        }.let { url ->
//            if ((!url.startsWith("http://")) && url.startsWith("http:/")) {
//                url.replace("http:/", "http://")
//            } else url
//        }
//
//    data class Video(
//        val url: String,
//        val title: String,
//        val category: String,
//        val thumb: String
//    )
//}
//
//val DomTreeElement.eachVideo: List<String>
//    get() {
//        return allElements.filter { e ->
//            (e.tagName == "video") || (e.tagName == "source")
//        }.filter { e ->
//            e.hasAttribute("src")
//        }.map { e ->
//            e.attribute("src")
//        }
//    }