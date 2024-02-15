/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

import org.mjdev.gradle.extensions.buildConfigString
import org.mjdev.gradle.extensions.manifestPlaceholders
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.suffixToString

plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("AppPlugin")
}

appConfig {
    packageName = "org.mjdev.tvapp"

    minSdk = 21
    compileSdk = 34

    autoCorrectCode = false
    createDocumentation = false
    createReleaseNotes = true
    renameApkOutputByAppID = false
    updateDependencies = false
    createReleaseZip = true
    createInfoClass = false
    buildTypeInLauncherIcon = false

    signingConfigName = "Any"
    signingPropertiesFile = "config/keystore.properties"

    versionPropertiesFile = "config/version.properties"
}

android {
    defaultConfig {
        buildConfigString(
            "IPTV_API_URL" to "https://iptv-org.github.io/api/",
            "GITHUB_USER" to "mimoccc",
            "GITHUB_REPOSITORY" to "tvapp"
        )
        manifestPlaceholders(
            "auth0Domain" to "@string/com_auth0_domain",
            "auth0Scheme" to "demo"
        )
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isCrunchPngs = false
            isEmbedMicroApp = true
            buildConfigString(
                "SYNC_AUTH" to "${defaultConfig.applicationId}$applicationIdSuffix.sync"
            )
            stringRes(
                "sync_auth" to "${defaultConfig.applicationId}$applicationIdSuffix.sync",
                "app_name" to "TV App ${applicationIdSuffix.suffixToString()}"
            )
        }
        release {
            applicationIdSuffix = ""
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            isEmbedMicroApp = true
            buildConfigString(
                "SYNC_AUTH" to "${defaultConfig.applicationId}$applicationIdSuffix.sync"
            )
            stringRes(
                "sync_auth" to "${defaultConfig.applicationId}$applicationIdSuffix.sync",
                "app_name" to "TV App ${applicationIdSuffix.suffixToString()}"
            )
        }
    }
}
