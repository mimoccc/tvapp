/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import org.gradle.api.initialization.Settings
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import java.lang.reflect.Modifier
import java.util.Locale

inline fun <reified T> Project.fromBuildPropertiesFile(
    config: T,
    configPropertiesFile: String
) {
    val props = project.loadBuildPropertiesFile(configPropertiesFile, false)
    val members = T::class.java.declaredFields.filter {
        !Modifier.isStatic(it.modifiers)
    }.onEach {
        it.trySetAccessible()
    }
    props.forEach { prop ->
        members.firstOrNull { field ->
            prop.key == field.name
        }?.apply {
            val value = prop.value.cast(this.type)
            try {
                set(config, value)
            } catch (e: Exception) {
                println("> Error set: $name[$type] = $value, ${e.message}")
            }
        }
    }
}

fun <T : Any> T.cast(type: Class<*>?): Any? = when (type?.simpleName?.lowercase()) {
    "string" -> this.toString()
    "int" -> parseInt(this)
    "long" -> parseLong(this)
    "boolean" -> parseBoolean(this)
    "double" -> parseDouble(this)
    else -> {
        println("> Cast missing for type: ${type?.simpleName}")
        null
    }
}

fun parseBoolean(value: Any): Boolean? = when (value) {
    is Boolean -> value
    is CharSequence ->
        when (value.toString().lowercase(Locale.US)) {
            "true" -> true
            "false" -> false
            else -> null
        }

    is Number ->
        when (value.toInt()) {
            0 -> false
            1 -> true
            else -> null
        }

    else -> null
}

fun parseInt(value: Any): Int? = when (value) {
    is Int -> value
    is CharSequence -> value.toString().toIntOrNull()
    is Number -> value.toInt()
    else -> null
}

fun parseLong(value: Any): Long? = when (value) {
    is Long -> value
    is CharSequence -> value.toString().toLongOrNull()
    is Number -> value.toLong()
    else -> null
}

fun parseDouble(value: Any): Double? = when (value) {
    is Double -> value
    is CharSequence -> value.toString().toDoubleOrNull()
    is Number -> value.toDouble()
    else -> null
}

//fun <T : ExtensionAware> T.ifCi(action: T.() -> Unit) {
//    if (ci) {
//        action()
//    }
//}

//fun <T : ExtensionAware> T.ifLocal(action: T.() -> Unit) {
//    if (!ci) {
//        action()
//    }
//}