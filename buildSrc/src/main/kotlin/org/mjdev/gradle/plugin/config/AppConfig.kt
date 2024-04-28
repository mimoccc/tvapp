/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

open class AppConfig {
    open var autoCorrectCode = false
    open var createDocumentation = false
    open var createReleaseNotes = false
    open var createZipRelease = false

//    open var renameApkOutputByAppID = false
//    open var createInfoClass = false
//    open var buildTypeInLauncherIcon = false

    open var documentationDir = "documentation/app"
    open var detectConfigFile = "config/detekt.ym"
}