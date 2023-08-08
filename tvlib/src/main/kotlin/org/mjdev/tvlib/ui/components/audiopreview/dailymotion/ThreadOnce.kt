/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import androidx.annotation.AnyThread
import timber.log.Timber

@Suppress("unused")
open class ThreadOnce(
    private val runnable: Runnable
) : Thread() {

    @Suppress("DEPRECATION")
    override fun run() {
        process(runnable)
        interrupt()
        try {
            stop()
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }

    private fun process(r: Runnable?) {
        try {
            r?.run()
        } catch (t: Throwable) {
            onError(t)
        }
    }

    private fun onError(t: Throwable) {
        Timber.e(t)
    }

    fun cancel() {
        try {
            interrupt()
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }

    companion object {

        @AnyThread
        fun runInThreadOnce(r: Runnable): ThreadOnce {
            val thread = ThreadOnce(r)
            thread.start()
            return thread
        }

        @Suppress("UNCHECKED_CAST")
        @AnyThread
        @Throws(InterruptedException::class)
        fun <T> runInThreadOnce(r: TRunnable<T>): T? {
            val thread = ThreadOnce(r)
            thread.start()
            return (thread.runnable as TRunnable<T>).value
        }

    }

}