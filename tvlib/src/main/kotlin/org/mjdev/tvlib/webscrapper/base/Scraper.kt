/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import kotlinx.coroutines.runBlocking
import org.mjdev.tvlib.webscrapper.fetcher.BlockingFetcher
import org.mjdev.tvlib.webscrapper.fetcher.NonBlockingFetcher
import kotlin.reflect.full.createInstance

class Scraper<R>(
    private val fetcher: NonBlockingFetcher<R>,
    private val preparedRequest: R
) {
    constructor(
        client: NonBlockingFetcher<R>
    ) : this(client, client.requestBuilder)

    suspend fun request(
        init: suspend R.() -> Unit
    ): Scraper<R> {
        init(preparedRequest)
        return this
    }

    suspend fun scrape(): Result =
        fetcher.fetch(preparedRequest)
}

private class FetcherConverter<T>(
    val blockingFetcher: BlockingFetcher<T>,
) : NonBlockingFetcher<T> {

    override suspend fun fetch(
        request: T
    ): Result = blockingFetcher.fetch(request)

    override val requestBuilder: T
        get() = blockingFetcher.requestBuilder
}

fun <R, T> skrape(
    fetcher: BlockingFetcher<R>,
    init: suspend Scraper<R>.() -> T
): T = runBlocking {
    Scraper(FetcherConverter(fetcher)).init()
}

@Suppress("unused")
suspend fun <R, T> skrape(
    fetcher: NonBlockingFetcher<R>,
    init: suspend Scraper<R>.() -> T
): T = Scraper(fetcher).init()

@Suppress("unused")
suspend fun Scraper<*>.expect(
    result: suspend Result.() -> Unit
) {
    response(result)
}

@Suppress("unused")
fun Scraper<*>.expectBlocking(
    result: suspend Result.() -> Unit
) {
    runBlocking { response(result) }
}

@Suppress("unused")
suspend fun <T> Scraper<*>.extract(
    result: suspend Result.() -> T
): T = response(result)

suspend fun <T> Scraper<*>.response(
    result: suspend Result.() -> T
): T = scrape().result()

@Suppress("unused")
fun <T> Scraper<*>.extractBlocking(
    result: suspend Result.() -> T
): T = runBlocking { response(result) }

@Suppress("unused")
suspend inline fun <reified T : Any> Scraper<*>.extractIt(
    crossinline result: Result.(T) -> Unit
): T = with(T::class) {
    response { createInstance().also { result(it) } }
}

@Suppress("unused")
inline fun <reified T : Any> Scraper<*>.extractItBlocking(
    crossinline result: Result.(T) -> Unit
): T = runBlocking { extractIt(result) }
