/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import java.net.URL

data class ScrapeLink(
    val url: String = "",
    val title: String? = "",
    val thumbUrl: String? = null,
    val parentLink: ScrapeLink? = null,
) {

    val authority: String
        get() = URL(url).authority

    val baseUri: String
        get() = if (url.endsWith("/")) url
        else url.substring(0, url.lastIndexOf("/") + 1)

    val priority by lazy { Priority(this) }

    override fun toString(): String = "$title | $url"

    enum class Priority(
        val word: String,
        val priority: Int = 0
    ) {
        Default("", 0),
        Categories("categories", Int.MAX_VALUE - 1),
        Category("category", Int.MAX_VALUE - 2),
        Channel("channel", Int.MAX_VALUE - 3),
        Video("video", Int.MAX_VALUE - 4),
        Audio("audio", Int.MAX_VALUE - 5),
        Detail("detail", Int.MAX_VALUE - 6),
        Preview("preview", Int.MAX_VALUE - 6);

        companion object {

            operator fun invoke(
                link: ScrapeLink
            ): Priority = entries.filter { entry ->
                entry.word.isNotEmpty()
            }.firstOrNull { value ->
                link.toString().contains(value.word)
            } ?: Default

        }

    }

    companion object {

        fun List<ScrapeLink>.contains(
            url: String
        ): Boolean = any { it.url == url }

    }

}
