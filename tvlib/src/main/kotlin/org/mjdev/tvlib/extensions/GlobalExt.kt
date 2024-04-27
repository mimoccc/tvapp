/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("unused", "MemberVisibilityCanBePrivate")
object GlobalExt {

    val UI = Dispatchers.Main
    val IO = Dispatchers.IO

    fun launch(
        context: CoroutineContext = IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = CoroutineScope(context).launch(
        context = context,
        start = start,
        block = block
    )

    fun launchUI(
        block: suspend CoroutineScope.() -> Unit
    ) = launch(UI) { block() }

    fun launchIO(
        block: suspend CoroutineScope.() -> Unit
    ) = launch(IO) { block() }

    fun launchInMain(block: suspend CoroutineScope.() -> Unit): Job = CoroutineScope(
        Dispatchers.Main
    ).launch(
        EmptyCoroutineContext,
        CoroutineStart.DEFAULT,
        block
    )

    fun <T> withScope(
        scope: T,
        block: T.() -> Unit
    ) = block.invoke(scope)

    fun <T> T.postDelayed(
        delay: Long,
        block: T.() -> Unit
    ) = Handler(Looper.myLooper() ?: Looper.getMainLooper()).postDelayed({
        block.invoke(this)
    }, delay)

    fun MutableState<Boolean>.toggle() {
        value = !value
    }

    inline fun <T> with(receiver: T, block: T.() -> Unit): T {
        block.invoke(receiver)
        return receiver
    }

}
