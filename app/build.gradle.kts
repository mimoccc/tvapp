/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("UnstableApiUsage")

import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.kapt
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.javaVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.kotlinCompilerExtVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.loadKeyStoreProperties
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.versionCode
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.versionName

@Suppress("PropertyName")
val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"

@Suppress("PropertyName")
val SIGNING_CONFIG_NAME = "Any"

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.objectbox")
    id("MainAppPlugin")
}

android {
    namespace = "org.mjdev.tvapp"
    compileSdk = 34

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

        minSdk = 21
        targetSdk = 34

        versionCode = project.versionCode
        versionName = project.versionName

        signingConfig = signingConfigs[SIGNING_CONFIG_NAME]
    }

    buildTypes {

        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "DEBUG_RECOMPOSE", "false")
            buildConfigField(
                "String",
                "SYNC_AUTH",
                "\"${defaultConfig.applicationId}${applicationIdSuffix}.sync\""
            )
            resValue(
                "string",
                "sync_auth",
                "${defaultConfig.applicationId}${applicationIdSuffix}.sync"
            )
        }

        release {
            applicationIdSuffix = ""
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "DEBUG_RECOMPOSE", "false")
            buildConfigField(
                "String",
                "SYNC_AUTH",
                "\"${defaultConfig.applicationId}${applicationIdSuffix}.sync\""
            )
            resValue(
                "string",
                "sync_auth",
                "${defaultConfig.applicationId}${applicationIdSuffix}.sync"
            )
        }

        create("recomposing") {
            applicationIdSuffix = ".rcmps"
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "DEBUG_RECOMPOSE", "true")
            buildConfigField(
                "String",
                "SYNC_AUTH",
                "\"${defaultConfig.applicationId}${applicationIdSuffix}.sync\""
            )
            resValue(
                "string",
                "sync_auth",
                "${defaultConfig.applicationId}${applicationIdSuffix}.sync"
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
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    lint {
        checkReleaseBuilds = false
    }

    subprojects {
        apply(plugin = "org.jetbrains.dokka")
    }

    tasks.dokkaGfm {
        outputDirectory.set(File(projectDir, "../wiki/documentation"))
    }

}

dependencies {

    // base libs
    implementation("androidx.core:core-ktx:1.12.0-alpha05")
    // reflect / debug purposes only
    implementation(kotlin("reflect", "1.8.21"))
    // window manager
    implementation("androidx.window:window:1.1.0")
    // compose base libs
    implementation(
        platform("androidx.compose:compose-bom:2023.06.01")
    )
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.7.2")
    // more icons
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    // tv compose
    implementation("androidx.tv:tv-foundation:1.0.0-SNAPSHOT")
    implementation("androidx.tv:tv-material:1.0.0-alpha07")
    // paging
    implementation("androidx.paging:paging-compose:3.2.0-rc01")
    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.0-beta02")
    // image loading
    implementation("io.coil-kt:coil-base:2.4.0")
    implementation("io.coil-kt:coil-gif:2.2.2")
    implementation("io.coil-kt:coil-svg:1.2.2")
    implementation("io.coil-kt:coil-video:1.2.2")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.github.skydoves:landscapist-coil:2.2.2")
    // constraint layout // todo remove
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // debug
    implementation("com.jakewharton.timber:timber:5.0.1")
    // dagger core
    implementation("com.google.dagger:dagger:2.46.1")
    implementation("androidx.compose.material3:material3:1.1.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    // dagger android
    implementation("com.google.dagger:dagger-android:2.46.1")
    implementation("com.google.dagger:dagger-android-support:2.46.1")
    kapt("com.google.dagger:dagger-android-processor:2.46.1")
    // dagger - hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("com.google.dagger:hilt-compiler:2.46.1")
    // okhttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    // moshi json
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    // encrypt data
    implementation("com.scottyab:aescrypt:0.0.1")
    // exoplayer
    implementation("androidx.media3:media3-exoplayer:1.1.0")
    implementation("androidx.media3:media3-exoplayer-dash:1.1.0")
    implementation("androidx.media3:media3-exoplayer-hls:1.1.0")
    implementation("androidx.media3:media3-exoplayer-rtsp:1.1.0")
    implementation("androidx.media3:media3-exoplayer-ima:1.1.0")
    implementation("androidx.media3:media3-datasource-cronet:1.1.0")
    implementation("androidx.media3:media3-datasource-okhttp:1.1.0")
    implementation("androidx.media3:media3-datasource-rtmp:1.1.0")
    implementation("androidx.media3:media3-ui:1.1.0")
    implementation("androidx.media3:media3-ui-leanback:1.1.0")
    implementation("androidx.media3:media3-session:1.1.0")
    implementation("androidx.media3:media3-extractor:1.1.0")
    implementation("androidx.media3:media3-cast:1.1.0")
    implementation("androidx.media3:media3-exoplayer-workmanager:1.1.0")
    implementation("androidx.media3:media3-transformer:1.1.0")
    implementation("androidx.media3:media3-database:1.1.0")
    implementation("androidx.media3:media3-decoder:1.1.0")
    implementation("androidx.media3:media3-datasource:1.1.0")
    implementation("androidx.media3:media3-common:1.1.0")
    // permission
    implementation("com.google.accompanist:accompanist-permissions:0.31.5-beta")

    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")?.apply {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")?.apply {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

}