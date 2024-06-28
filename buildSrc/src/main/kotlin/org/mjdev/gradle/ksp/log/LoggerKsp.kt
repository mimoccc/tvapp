/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.log

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode
import org.gradle.api.logging.Logger

class LoggerKsp(
    val log :  Logger
) : KSPLogger {
    override fun error(message: String, symbol: KSNode?) {
        log.error(message, symbol)
    }

    override fun exception(e: Throwable) {
        log.error(e.message)
    }

    override fun info(message: String, symbol: KSNode?) {
        log.info(message, symbol)
    }

    override fun logging(message: String, symbol: KSNode?) {
        log.lifecycle(message, symbol)
    }

    override fun warn(message: String, symbol: KSNode?) {
        log.warn(message, symbol)
    }

}