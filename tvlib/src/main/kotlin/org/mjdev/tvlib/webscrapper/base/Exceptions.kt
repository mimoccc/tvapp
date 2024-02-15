/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

class JSTimeoutException(message: String? = null) : Exception(message ?: "")

class JSAlert(message: String? = null) : Exception(message ?: "")

class JSMessage(
    message: String?,
    lineNumber: Int,
    sourceID: String?
) : Exception("$lineNumber : ${message ?: ""} in $sourceID")

class ExceededDatabaseQuota() : Exception()

class ReceivedError(message: String? = null) : Exception(message)

class SSLError(message: String? = null) : Exception(message)