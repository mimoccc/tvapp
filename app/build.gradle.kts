/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.mjdev.gradle.dependency.anotherDependencies
import org.mjdev.gradle.dependency.baseDependencies
import org.mjdev.gradle.dependency.composeDependencies
import org.mjdev.gradle.dependency.daggerDependencies
import org.mjdev.gradle.dependency.encryptDependencies
import org.mjdev.gradle.dependency.exoPlayerDependencies
import org.mjdev.gradle.dependency.glideDependencies
import org.mjdev.gradle.dependency.mjdevTvLib
import org.mjdev.gradle.dependency.moshiDependencies
import org.mjdev.gradle.dependency.okHttpDependencies
import org.mjdev.gradle.dependency.permissionsDependencies
import org.mjdev.gradle.dependency.retrofitDependencies
import org.mjdev.gradle.dependency.sandwichDependencies
import org.mjdev.gradle.dependency.testDependencies
import org.mjdev.gradle.dependency.timberDependencies
import org.mjdev.gradle.dependency.widgetDependencies
import org.mjdev.gradle.extensions.buildConfigString
import org.mjdev.gradle.extensions.loadKeyStoreProperties
import org.mjdev.gradle.extensions.manifestPlaceholders
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.suffixToString
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.plugin.javaVersion
import org.mjdev.gradle.plugin.kotlinCompilerExtVersion
import org.mjdev.gradle.plugin.projectCompileSdk
import org.mjdev.gradle.plugin.projectMinSdk

@Suppress("PropertyName")
val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"

@Suppress("PropertyName")
val SIGNING_CONFIG_NAME = "Any"

plugins {
//    kotlinAndroid()
//    kotlinKapt()
//    kotlinParcelize()
//    androidApplication()
//    hilt()
//    ksp()
//    mainAppPlugin()
//    objectBox()
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
//    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
//    id ("app.cash.paparazzi") version "1.3.1"
//    id("DependencyUpdatePlugin")
//    id("AndroidCoreLibraryPlugin")
    id("MainAppPlugin")
    id("io.objectbox")
}

android {
    namespace = "org.mjdev.tvapp"
    compileSdk = projectCompileSdk

    signingConfigs {
        loadKeyStoreProperties(
            CONFIG_KEYSTORE_PROPERTIES_FILE
        ) { aliasKey, passwordKey, fileStore, passwordStore ->
            create(SIGNING_CONFIG_NAME) {
                keyAlias = aliasKey
                keyPassword = passwordKey
                storeFile = fileStore
                storePassword = passwordStore
            }
        }
    }

    defaultConfig {
        applicationId = "org.mjdev.tvapp"

        minSdk = projectMinSdk
        targetSdk = projectCompileSdk

        versionCode = project.versionCode
        versionName = project.versionName

        signingConfig = signingConfigs[SIGNING_CONFIG_NAME]

        buildConfigString(
            "IPTV_API_URL" to "https://iptv-org.github.io/api/",
            "GITHUB_USER" to "mimoccc",
            "GITHUB_REPOSITORY" to "tvapp"
        )

        multiDexEnabled = true

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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigString(
                "SYNC_AUTH" to "${defaultConfig.applicationId}$applicationIdSuffix.sync"
            )
            stringRes(
                "sync_auth" to "${defaultConfig.applicationId}$applicationIdSuffix.sync",
                "app_name" to "TV App ${applicationIdSuffix.suffixToString()}"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = kotlinCompilerExtVersion
    }

    packaging {
        resources.excludes.apply {
            add("/META-INF/{AL2.0,LGPL2.1}")
            add("/META-INF/DEPENDENCIES")
            add("/mozilla/public-suffix-list.txt")
        }
    }

    lint {
        checkReleaseBuilds = false
    }

    kapt {
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = true
    }

    applicationVariants.all {
        outputs.map {
            it as BaseVariantOutputImpl
        }.forEach { output ->
            val outputFileName = "$applicationId-$versionName.apk"
            output.outputFileName = outputFileName
        }
    }

    tasks {
        dokkaGfm {
            outputDirectory.set(File(projectDir, "../wiki/documentation/"))
        }
    }

    mainAppConfig {
        createDocumentation = true
    }
}

dependencies {
    mjdevTvLib()
    baseDependencies()
    composeDependencies()
    daggerDependencies()
    moshiDependencies()
    okHttpDependencies()
    timberDependencies()
    retrofitDependencies()
    sandwichDependencies()
    glideDependencies()
    exoPlayerDependencies()
    testDependencies()
    encryptDependencies()
    permissionsDependencies()
    anotherDependencies()
    widgetDependencies()
}
