/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

@Suppress("unused")
fun <T, V> HashMap<T, V>.onKeyExists(
    key: T,
    block: ((key: T, value: V?) -> (Unit))
) {
    if (this.containsKey(key)) {
        block.invoke(key, this[key])
    }
}

fun <K, V> Map<K, V>.mapToString(): String = map { kv ->
    val sdKey = kv.key.toString().replace("\"", "")
    val sdValue = kv.value.toString().replace("\"", "")
    "\t\"$sdKey\": \"$sdValue\""
}.joinToString(",\n") + "\n"