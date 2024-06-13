/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

import org.gradle.api.JavaVersion
import org.mjdev.gradle.extensions.toHashMap
import org.mjdev.gradle.plugin.config.base.BuildConfigs

open class LibConfig : BuildConfigs() {
    open var namespace = "org.mjdev.tvlib"
    open var description = "Smart TV android app lib for android applications running on any android device"

    open var autoCorrectCode = true
    open var ignoreCodeFailures = true
    open var createDocumentation = true
    open var reportUndocumentedFiles = false
    open var failOnDocumentationWarning = false
    open var minify = false

    open var codeReportsDir = "reports/lib"
    open var documentationDir = "documentation/lib"

    open var detectConfigFile = "config/detekt.yml"
    open var versionPropertiesFile = "config/version.lib.prop"

    open var proguardFile = "proguard-android-optimize.txt"
    open var proguardRulesFile = "proguard-rules.pro"

    companion object {
        const val configFieldName = "libConfig"
        const val configPropertiesFile = "lib.config.prop"

        const val composeEnabled = true
        const val buildConfigEnabled = true

        const val compileSdk = 34
        const val minSdk = 21

        const val jacocoVersion = "0.8.8"

        val javaVersion = JavaVersion.VERSION_17

        val projectExcludes = listOf(
            "META-INF/",
            "/META-INF/{AL2.0,LGPL2.1}",
            "/META-INF/DEPENDENCIES",
            "/mozilla/public-suffix-list.txt",
            "okhttp3/",
            "kotlin/",
            "org/",
            ".properties",
            ".bin",
        )
    }

    override fun toMap(): Map<*, *> = toHashMap()
}
