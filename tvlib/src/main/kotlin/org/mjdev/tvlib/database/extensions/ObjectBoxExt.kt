/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.database.extensions

import io.objectbox.Box
import io.objectbox.kotlin.awaitCallInTx
import org.mjdev.tvlib.extensions.GlobalExt.launchIO

object ObjectBoxExt {

    suspend inline fun <reified V, T> Box<T>.stx(
        crossinline transaction: Box<T>.() -> V
    ) = store.awaitCallInTx {
        transaction(this)
    }

    inline fun <reified V, T> Box<T>.tx(
        crossinline transaction: Box<T>.() -> V
    ) = launchIO {
        store.awaitCallInTx {
            transaction(this@tx)
        }
    }

//    // todo remove reflection && improve search
//    @Suppress("unused")
//    inline fun <reified T> Box<T>.findById(id: Long?): T? = if (id == null) all.firstOrNull()
//    else all.firstOrNull { o -> o.property("id") == id }

//    // todo remove reflection
//    inline fun <reified T> T.property(name: String): Any? = T::class.members.firstOrNull {
//        it.name == name
//    }?.call(this)

    fun <T : Any> Box<T>.update(entity: T, query: (T) -> Boolean) {
        val exists = query(query)
        if (exists.isNotEmpty()) {
            remove(exists)
        }
        put(entity)
    }

    // todo improve for faster search
    @Suppress("MemberVisibilityCanBePrivate")
    fun <T : Any> Box<T>.query(query: (T) -> Boolean) = all.filter(query)

}
