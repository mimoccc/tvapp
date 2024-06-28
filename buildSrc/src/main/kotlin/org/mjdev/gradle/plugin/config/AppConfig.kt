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

@Suppress("unused", "ConstPropertyName")
open class AppConfig : BuildConfigs() {
    open var namespace = "org.mjdev.tvapp"
    open var description = "Android tv and streaming application for any android lollipop+ device"

    open var autoCorrectCode = true
    open var ignoreCodeFailures = true
    open var createDocumentation = true
    open var reportUndocumentedFiles = false
    open var failOnDocumentationWarning = false
    open var createReleaseNotes = true
    open var createZipRelease = true

    open var createWebApp = true
    open var createWebSiteFromGit = false
    open var renameApkOutputByAppID = false
    open var createInfoClass = false
    open var launcherIconByBuildType = false

    open var codeReportsDir = "reports/app"
    open var documentationDir = "documentation/app"

    open var detectConfigFile = "config/detekt.yml"
    open var versionPropertiesFile = "config/version.app.prop"
    open var debugKeySigningFile = "config/signing.debug.prop"
    open var releaseSigningFile = "config/signing.release.prop"

    open var proguardFile = "proguard-android-optimize.txt"
    open var proguardRulesFile = "proguard-rules.pro"


    companion object {
        const val configFieldName = "appConfig"
        const val configPropertiesFile = "app.config.prop"

        const val composeEnabled = true
        const val buildConfigEnabled = true

        const val compileSdk = 34
        const val minSdk = 21

//        const val buildToolsVersion = "34.0.0-rc3"

        const val jacocoVersion = "0.8.8"

        const val multiDexEnabled = true

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
