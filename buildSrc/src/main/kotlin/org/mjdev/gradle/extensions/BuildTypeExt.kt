/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationVariantDimension

fun ApplicationVariantDimension.buildConfigBoolean(
    vararg fields: Pair<String, Boolean>
) {
    fields.forEach { pair ->
        buildConfigField("Boolean", pair.first, pair.second.toString())
    }
}

fun ApplicationVariantDimension.buildConfigString(
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        buildConfigField("String", pair.first, "\"${pair.second}\"")
    }
}

fun ApplicationVariantDimension.stringRes(
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        resValue("string", pair.first, pair.second)
    }
}

fun ApplicationVariantDimension.stringRes(
    name: String,
    value: String
) {
    resValue("string", name, value)
}

fun ApplicationVariantDimension.addSyncProviderAuthString(
    applicationId: String,
    name: String = "sync_auth",
    suffix: String = "sync"
) {
    buildConfigStringWithPackageName(name.uppercase() to suffix)
    stringResWithPackageName(applicationId, name to suffix)
}

fun ApplicationVariantDimension.stringResWithPackageName(
    applicationId: String,
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        resValue("string", pair.first, "$applicationId.${pair.second}")
    }
}

fun ApplicationVariantDimension.buildConfigStringWithPackageName(
    vararg fields: Pair<String, String>
) {
    fields.forEach { pair ->
        buildConfigField(
            "String",
            pair.first,
            "APPLICATION_ID + " + "\".${pair.second}\""
        )
    }
}

fun ApplicationVariantDimension.manifestPlaceholders(
    vararg placeholders: Pair<String, Any>
) {
    placeholders.forEach { pair -> manifestPlaceholders.put(pair.first, pair.second) }
}