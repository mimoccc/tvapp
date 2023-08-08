/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

abstract class TRunnable<T> : Thread(), Runnable {

    var value: T? = null

    protected abstract fun runWithResult(): T

    override fun run() {
        value = runWithResult()
    }

}