/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.VariantDimension
import kotlin.String

fun VariantDimension.buildConfigBoolean(
    vararg fields: Pair<String, Boolean>
) {
    fields.forEach { pair ->
        buildConfigField("Boolean", pair.first, pair.second.toString())
    }
}

fun VariantDimension.buildConfigString(
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        buildConfigField("String", pair.first, "\"${pair.second}\"")
    }
}

fun VariantDimension.stringRes(
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        resValue("string", pair.first, pair.second)
    }
}

fun VariantDimension.manifestPlaceholders(
    vararg fields: Pair<String, Any>
) {
    manifestPlaceholders.apply {
        fields.forEach { pair ->
            put(pair.first, pair.second)
        }
    }
}
