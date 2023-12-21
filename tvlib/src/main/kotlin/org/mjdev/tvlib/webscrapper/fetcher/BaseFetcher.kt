/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.fetcher

import org.mjdev.tvlib.webscrapper.base.Result

interface BlockingFetcher<T> {
    fun fetch(request: T): Result
    val requestBuilder: T
}

interface NonBlockingFetcher<T> {
    suspend fun fetch(request: T): Result
    val requestBuilder: T
}
