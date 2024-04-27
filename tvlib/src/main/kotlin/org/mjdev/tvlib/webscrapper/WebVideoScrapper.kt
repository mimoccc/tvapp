/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import org.mjdev.tvlib.extensions.GlobalExt.launchInMain
import org.mjdev.tvlib.webscrapper.adblock.IAdBlocker
import org.mjdev.tvlib.webscrapper.adblock.NoAdBlock
import org.mjdev.tvlib.webscrapper.base.CustomWebView
import org.mjdev.tvlib.webscrapper.base.ScrapeLink
import org.mjdev.tvlib.webscrapper.base.ScrapeScope
import org.mjdev.tvlib.webscrapper.select.DocElement
import org.mjdev.tvlib.webscrapper.base.ScrapeLink.Companion.contains
import timber.log.Timber
import java.net.URL
import kotlinx.coroutines.Job
import org.mjdev.tvlib.extensions.StringExt.mimeType

@Suppress("CanBeParameter")
class WebVideoScrapper(
    private val context: Context,
    private val loadImages: Boolean = false,
    private val loadMedia: Boolean = false,
    private val loadScripts: Boolean = false,
    private val loadCssStyles: Boolean = false,
    private val loadFonts: Boolean = false,
    private val isNsfw: Boolean = false,
    private val parseUrls: List<String>,
    private val parsedLinks: List<ScrapeLink> = emptyList(),
    private val userAgent: String = USER_AGENT,
    private val onVideoFound: (suspend WebVideoScrapper.(video: Video) -> Unit)? = null,
    private val onAudioFound: (suspend WebVideoScrapper.(audio: Audio) -> Unit)? = null,
    private val onNoVideoFound: (suspend WebVideoScrapper.(link: ScrapeLink) -> Unit)? = null,
    private val onNoAudioFound: (suspend WebVideoScrapper.(link: ScrapeLink) -> Unit)? = null,
    private val onLinkParsed: (suspend WebVideoScrapper.(link: ScrapeLink) -> Unit)? = null,
    private val onFinish: (suspend WebVideoScrapper.() -> Unit)? = null,
    private val addBlocker: IAdBlocker = NoAdBlock(),
    private var pauseState: MutableState<Boolean> = mutableStateOf(false),
) {
    private var scrapeJob: Job? = null
    private val links = mutableListOf<ScrapeLink>()
    private val _parsedLinks = mutableListOf(*parsedLinks.toTypedArray())
    private lateinit var webScraper: CustomWebView

    init {
        parseUrls.forEach { url -> addLink(url) }
    }

    private fun addLink(
        url: String,
        title: String = "",
        thumbUrl: String? = null,
        parentLink: ScrapeLink? = null
    ) = links.apply {
        add(ScrapeLink(url, title, thumbUrl, parentLink))
        sortBy { link ->
            link.priority.priority
        }
    }

    fun start() {
        scrapeJob = launchInMain {
            webScraper = CustomWebView(
                context = context,
                adBlocker = addBlocker,
                userAgent = userAgent,
                loadImages = loadImages,
                loadMedia = loadMedia,
                loadScripts = loadScripts,
                loadCssStyles = loadCssStyles,
                loadFonts = loadFonts
            )
            parseLinks()
        }
    }

    fun stop() {
        pauseState.value = true
        links.clear()
        scrapeJob?.cancel()
        scrapeJob = null
    }

    private suspend fun parseLinks() {
        if (pauseState.value) {
            delay(500)
            parseLinks()
        } else {
            if (links.isNotEmpty()) {
                links.removeFirstOrNull()?.let { link ->
                    parse(link)
                }
            } else if (webScraper.isScrapping) {
                delay(100)
                parseLinks()
            } else {
                onFinish?.invoke(this)
            }
        }
    }

    private suspend fun parse(link: ScrapeLink) {
        Timber.e("Parsing :  $link")
        _parsedLinks.add(link)
        onLinkParsed?.invoke(this, link)
        try {
            webScraper.scrape(link) {
                if (videos.isNotEmpty()) {
                    videos.forEach { entry ->
                        val videoUrl = entry.source
                        if (videoUrl.isNotEmpty()) {
                            val mimeType = entry.mimeType
                            Video(
                                title = parseTitle(),
                                category = parseCategory(),
                                webUrl = url?.url ?: videoUrl,
                                videoUrl = videoUrl,
                                thumbUrl = url?.thumbUrl ?: videoUrl,
                                isNsfw = isNsfw,
                                mimeType = mimeType
                            ).also { video ->
                                Timber.e("Got video: $video")
                                onVideoFound?.invoke(this@WebVideoScrapper, video)
                            }
                        }
                    }
                } else {
                    onNoVideoFound?.invoke(this@WebVideoScrapper, link)
                }
                if (audios.isNotEmpty()) {
                    audios.forEach { entry ->
                        val audioUrl = entry.source
                        if (audioUrl.isNotEmpty()) {
                            val mimeType = entry.mimeType
                            Audio(
                                title = parseTitle(),
                                category = parseCategory(),
                                webUrl = url?.url ?: audioUrl,
                                audioUrl = audioUrl,
                                thumbUrl = url?.thumbUrl ?: audioUrl,
                                isNsfw = isNsfw,
                                mimeType = mimeType
                            ).also { audio ->
                                Timber.e("Got audio: $audio")
                                onAudioFound?.invoke(this@WebVideoScrapper, audio)
                            }
                        }
                    }
                } else {
                    onNoAudioFound?.invoke(this@WebVideoScrapper, link)
                }
                anchors.forEach { entry ->
                    val href = entry.href
                    val title = entry.title
                    val thumbUrl: String? = entry.parseThumb()
                    when {
                        addBlocker.isBlocked(href) -> {
                            Timber.e("Omitted link: $href")
                        }

                        href.startsWith("javascript:") -> {
                            Timber.e("Omitted link: $href")
                        }

                        href.contentEquals(url?.baseUri) -> {
                            Timber.e("Omitted link: $href")
                        }

                        url?.authority?.let { href.contains(it) } == false -> {
                            Timber.e("Omitted link: $href")
                        }

                        _parsedLinks.contains(href) -> {
                            Timber.e("Already scrapped link: $href")
                        }

                        else -> {
                            addLink(
                                url = href,
                                title = title,
                                thumbUrl = thumbUrl,
                                parentLink = link
                            )
                            Timber.e("Got link: $href")
                        }
                    }
                }
                if (links.isNotEmpty()) parseLinks()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // todo, better results
    @Suppress("UnnecessaryVariable")
    private fun ScrapeScope.parseCategory(): String {
        val first = URL(url?.url).authority.replace("www.", "")
        val last = url?.parentLink?.title ?: ""
        val category = if (last.isEmpty()) first else "$first | $last"
        return category
    }

    // todo, better results
    private fun ScrapeScope.parseTitle(): String {
        return title
    }

    data class Video(
        val webUrl: String = "",
        val videoUrl: String = "",
        val title: String = "",
        val category: String = "",
        val thumbUrl: String = "",
        val isNsfw: Boolean = false,
        val mimeType: String = ""
    )

    data class Audio(
        val webUrl: String = "",
        val audioUrl: String = "",
        val title: String = "",
        val category: String = "",
        val thumbUrl: String = "",
        val isNsfw: Boolean = false,
        val mimeType: String = ""
    )

    companion object {
        const val USER_AGENT =
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0"
    }

}

private fun DocElement.parseThumb(): String? {
    val image = eachImage.firstOrNull()?.source
    val picture = eachPicture.firstOrNull()?.source
    return image ?: picture
}

private val DocElement.mimeType: String
    get() = source.mimeType

private val DocElement.source: String
    get() = when (tagName) {
        "audio", "video", "picture" -> {
            absUrl("src").ifEmpty {
                val sources = children.filter { child ->
                    val tag = child.tagName
                    val src = child.attribute("src")
                    val srcSet = child.attribute("srcset")
                    val hasSrc = src.isNotEmpty() && (!src.startsWith("data"))
                    val hasSrcSet = srcSet.isNotEmpty()
                    (tag == "source") && (hasSrc || hasSrcSet)
                }
                sources.firstOrNull().let { source ->
                    source?.absUrl("src") ?: source?.absUrl("srcset") ?: ""
                }
            }
        }

        "img" -> absUrl("src")
        else -> ""
    }

private val DocElement.title: String
    get() = attribute("title")

private val DocElement.href: String
    get() = when (tagName) {
        "audio", "video", "picture" -> {
            absUrl("src").ifEmpty {
                val sources = children.filter { child ->
                    val tag = child.tagName
                    val src = child.attribute("src")
                    val srcSet = child.attribute("srcset")
                    val hasSrc = src.isNotEmpty() && (!src.startsWith("data"))
                    val hasSrcSet = srcSet.isNotEmpty()
                    (tag == "source") && (hasSrc || hasSrcSet)
                }
                sources.firstOrNull().let { source ->
                    source?.absUrl("src") ?: source?.absUrl("srcset") ?: ""
                }
            }
        }

        "img" -> absUrl("src")
        "a" -> absUrl("href")
        else -> ""
    }
