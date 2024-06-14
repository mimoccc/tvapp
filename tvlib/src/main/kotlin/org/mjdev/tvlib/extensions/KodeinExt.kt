/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.LazyDelegate
import org.kodein.di.instance
import kotlin.reflect.KProperty

object KodeinExt {

    private val LocalDI: ProvidableCompositionLocal<DI?>
        @Composable
        get() {
            val diAware = LocalContext.current as? DIAware
            return compositionLocalOf {
                diAware?.di
            }
        }

    @Composable
    fun localDI(
        initializer: @Composable () -> DI? = { null }
    ): DI = LocalDI.current ?: initializer() ?: throw (RuntimeException("No DI available."))

    class ComposableDILazyDelegate<V>(
        private val base: LazyDelegate<V>
    ) : LazyDelegate<V> {
        private lateinit var lazy: Lazy<V>

        override fun provideDelegate(
            receiver: Any?,
            prop: KProperty<Any?>
        ): Lazy<V> {
            if (!this::lazy.isInitialized) {
                lazy = base.provideDelegate(null, prop)
            }
            return lazy
        }
    }

    @Composable
    inline fun <reified T : Any> rememberDI(
        noinline initializer: @Composable () -> DI? = { null },
        crossinline block: @DisallowComposableCalls DI.() -> LazyDelegate<T>
    ): LazyDelegate<T> = with(localDI(initializer)) {
        remember {
            ComposableDILazyDelegate(block())
        }
    }

    @Composable
    inline fun <reified T : Any> rememberInstance(
        tag: Any? = null,
        noinline initializer: @Composable () -> DI? = { null }
    ): LazyDelegate<T> = rememberDI(initializer) {
        instance(tag = tag)
    }

}