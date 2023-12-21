/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.parser

import org.intellij.lang.annotations.Language
import org.jsoup.nodes.Document
import org.mjdev.tvlib.webscrapper.fetcher.BrowserFetcher
import org.mjdev.tvlib.webscrapper.select.Doc
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import org.jsoup.parser.Parser.parse as jSoupParser
import org.mjdev.tvlib.webscrapper.base.Result

@Suppress("unused", "MemberVisibilityCanBePrivate")
internal class Parser(
    var html: String,
    val charset: Charset,
    val jsExecution: Boolean,
    val baseUri: String,
) {

    fun parse(): Doc {
        return if (jsExecution) {
            jSoupParser(BrowserFetcher.render(html), baseUri).toDocWrapper()
        } else {
            jSoupParser(html, baseUri).toDocWrapper()
        }
    }

    private fun Document.toDocWrapper() = Doc(this)

    class MissingDependencyException(message: String = "") : Exception(message)
}

@Suppress("unused")
fun <T> htmlDocument(
    @Language("HTML") html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T,
): T = htmlDocument(html, charset, jsExecution, baseUri).init()

@Suppress("unused")
fun <T> htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T,
): T = htmlDocument(file, charset, jsExecution, baseUri).init()

@Suppress("unused")
fun <T> htmlDocument(
    bytes: InputStream,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
    init: Doc.() -> T,
): T = htmlDocument(bytes, charset, jsExecution, baseUri).init()

fun htmlDocument(
    @Language("HTML") html: String,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
): Doc = Parser(html, charset, jsExecution, baseUri).parse()

fun htmlDocument(
    file: File,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
): Doc = htmlDocument(file.readText(charset), charset, jsExecution, baseUri)

fun htmlDocument(
    bytes: InputStream,
    charset: Charset = Charsets.UTF_8,
    jsExecution: Boolean = false,
    baseUri: String = "",
): Doc = htmlDocument(bytes.bufferedReader().use(BufferedReader::readText), charset, jsExecution, baseUri)

@Suppress("unused")
val Result.document: Doc
    get() = htmlDocument { this }

fun <T> Result.htmlDocument(
    executeJS: Boolean = false,
    init: Doc.() -> T
): T = htmlDocument(
    html = responseBody,
    baseUri = baseUri,
    jsExecution = executeJS
).init()
