/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import org.mjdev.gradle.base.PluginExtension
import org.mjdev.gradle.extensions.packageName

@Suppress("RedundantVisibilityModifier")
public open class AppPluginParams : PluginExtension("appConfig") {
    public open var packageName = "org.mjdev.tvapp"

    public open var applicationId = packageName

    public open var minSdk = 21
    public open var compileSdk = 34
    public open var multiDexEnabled = true

    public open var versionPropertiesFile = CONFIG_VERSION_PROPERTIES_FILE
    public open var signingPropertiesFile = CONFIG_KEYSTORE_PROPERTIES_FILE
    public open var signingConfigName = SIGNING_CONFIG_NAME
    public open var createDocumentation = false
    public open var createReleaseNotes = false
    public open var updateDependencies = false
    public open var autoCorrectCode = false
    public open var createReleaseZip = false
    public open var renameApkOutputByAppID = false
    public open var createInfoClass = false
    public open var buildTypeInLauncherIcon = false

    companion object {
        const val CONFIG_VERSION_PROPERTIES_FILE = "config/version.properties"
        const val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"
        const val SIGNING_CONFIG_NAME = "Any"
    }
}