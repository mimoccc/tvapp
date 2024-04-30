/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

import org.gradle.api.JavaVersion
import org.mjdev.gradle.plugin.config.base.BuildConfigs

@Suppress("unused")
open class AppConfig : BuildConfigs() {

    open var description = ""

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

    open var javaVersion = JavaVersion.VERSION_17

    open var projectExcludes = listOf(
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

    open var projectProguardFile = "proguard-android-optimize.txt"
    open var projectProguardRulesFile = "proguard-rules.pro"
    open var versionPropertiesFile = "config/version.prop"

}