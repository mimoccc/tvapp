/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import java.lang.reflect.Modifier

//@Suppress("unused")
//fun <T, V> HashMap<T, V>.onKeyExists(
//    key: T,
//    block: ((key: T, value: V?) -> (Unit))
//) {
//    if (this.containsKey(key)) {
//        block.invoke(key, this[key])
//    }
//}

fun <K, V> Map<K, V>.mapToString(
    separator: String = ": ",
    quoteFirst: Boolean = true,
    quoteSecond: Boolean = true,
    prefix: String = "\t",
    suffix: String = ",",
): String = map { kv ->
    val sdKey = kv.key.toString().replace("\"", "")
    val sdValue = kv.value.toString().replace("\"", "")
    val qf = if (quoteFirst) "\"" else ""
    val qs = if (quoteSecond) "\"" else ""
    "$prefix$qf$sdKey$qf$separator$qs$sdValue$qs"
}.joinToString("$suffix\n") + "\n"

inline fun <reified T : Any> T.toHashMap(): Map<Any, Any> = mutableMapOf<Any, Any>().apply {
    val fields = T::class.java.declaredFields.filter {
        !Modifier.isStatic(it.modifiers)
    }.onEach {
        it.trySetAccessible()
    }
    fields.forEach { field ->
        if (field.name.lowercase() != "companion") {
            val key = field.name
            val value = field.get(this@toHashMap)
            put(key, value)
        }
    }
}