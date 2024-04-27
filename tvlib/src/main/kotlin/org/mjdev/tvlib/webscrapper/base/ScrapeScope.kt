/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import org.mjdev.tvlib.webscrapper.select.Doc
import org.mjdev.tvlib.webscrapper.select.DocElement

data class ScrapeScope(
    val url: ScrapeLink? = null,
    val title: String = "",
    val error: Throwable? = null,
    val document: Doc? = null,
    val videos: List<DocElement> = emptyList(),
    val audios: List<DocElement> = emptyList(),
    val anchors: List<DocElement> = emptyList()
)