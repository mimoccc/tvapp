/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

import org.mjdev.gradle.plugin.config.base.BuildConfigs

@Suppress("unused")
open class AppConfig : BuildConfigs() {

    open var description = ""

    open var autoCorrectCode = false
    open var ignoreCodeFailures = true
    open var createDocumentation = false
    open var reportUndocumentedFiles = false
    open var failOnDocumentationWarning = false
    open var createReleaseNotes = false
    open var createZipRelease = false

    open var createWebApp = false
    open var createWebSiteFromGit = false
    open var renameApkOutputByAppID = false
    open var createInfoClass = false
    open var launcherIconByBuildType = false

    open var codeReportsDir = "reports/app"
    open var documentationDir = "documentation/app"
    open var detectConfigFile = "config/detekt.yml"

}