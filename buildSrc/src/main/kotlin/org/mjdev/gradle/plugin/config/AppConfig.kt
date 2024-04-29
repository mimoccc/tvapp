/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

import org.mjdev.gradle.extensions.BuildTypeFnc

open class AppConfig {

    private val buildTypes = mutableMapOf<String, BuildTypeFnc>()

    open var autoCorrectCode = false
    open var ignoreCodeFailures = true
    open var createDocumentation = false
    open var reportUndocumentedFiles = false
    open var failOnDocumentationWarning = false
    open var createReleaseNotes = false
    open var createZipRelease = false
    open var renameApkOutputByAppID = false

//    open var createInfoClass = false
//    open var buildTypeInLauncherIcon = false

    open var codeReportsDir = "reports/app"
    open var documentationDir = "documentation/app"
    open var detectConfigFile = "config/detekt.yml"

    fun default (action:BuildTypeFnc) {
        buildTypes.put("debug", action)
    }

    fun debug(action: BuildTypeFnc) {
        buildTypes.put("debug", action)
    }

    fun release(action: BuildTypeFnc) {
        buildTypes.put("release", action)
    }

    fun minified(action: BuildTypeFnc) {
        buildTypes.put("minified", action)
    }

    fun test(action: BuildTypeFnc) {
        buildTypes.put("test", action)
    }

    fun publish(action: BuildTypeFnc) {
        buildTypes.put("test", action)
    }

}