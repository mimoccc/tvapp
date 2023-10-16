/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.kapt
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.javaVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.kotlinCompilerExtVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.loadKeyStoreProperties
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.projectCompileSdk
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.projectMinSdk
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.versionCode
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.versionName

@Suppress("PropertyName")
val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"

@Suppress("PropertyName")
val SIGNING_CONFIG_NAME = "Any"

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("io.objectbox")
    id("MainAppPlugin")
    kotlin("kapt")
    id("com.google.devtools.ksp")
//    id("org.jetbrains.dokka") version "1.8.10"
//    id ("app.cash.paparazzi") version "1.3.1"
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

        buildConfigField("String", "IPTV_API_URL", "\"https://iptv-org.github.io/api/\"")
        buildConfigField("String", "GITHUB_USER", "\"mimoccc\"")
        buildConfigField("String", "GITHUB_REPOSITORY", "\"tvapp\"")

        manifestPlaceholders.apply {
            put("auth0Domain", "@string/com_auth0_domain")
            put("auth0Scheme", "demo")
        }

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
            isDebuggable = false
            isMinifyEnabled = false // todo
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
            multiDexEnabled = true
            matchingFallbacks += listOf("debug")
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

    hilt {
        enableAggregatingTask = true
    }

//    subprojects {
//        apply(plugin = "org.jetbrains.dokka")
//    }

//    tasks.dokkaGfm {
//        outputDirectory.set(File(projectDir, "../wiki/documentation"))
//    }

}

dependencies {
    // todo remove
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20-RC")
    // startups
    implementation("androidx.startup:startup-runtime:1.1.1")
    // tv library by mjde milan jurkulak
    implementation(project(mapOf("path" to ":tvlib")))
    // base libs
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // compose base libs
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.8.0")
    // more icons
    implementation("androidx.compose.material:material-icons-extended:1.5.3")
    // tv compose
    implementation("androidx.tv:tv-foundation:1.0.0-alpha10")
    implementation("androidx.tv:tv-material:1.0.0-alpha10")
    // dagger - hilt
    implementation("com.google.dagger:dagger-android:2.48.1")
    implementation("com.google.dagger:dagger-android-support:2.48.1")
    implementation("com.google.dagger:dagger:2.48.1")
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-beta01")
    // moshi json
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // okhttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.11"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    // debug
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.android.volley:volley:1.2.1")
    // kapt
    kapt("com.google.dagger:dagger-compiler:2.48.1")
    kapt("com.google.dagger:dagger-android-processor:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    // sandwich
    implementation("com.github.skydoves:sandwich:1.3.9")
    // encrypt data
    implementation("com.scottyab:aescrypt:0.0.1")
    // permission
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
    // previews
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    // lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")
    // oauth
    implementation("com.auth0.android:auth0:2.10.2")
    implementation("com.auth0.android:jwtdecode:2.0.2")
    // widgets
    implementation("androidx.glance:glance-appwidget:1.0.0")
    implementation("androidx.glance:glance-material:1.0.0")
    implementation("androidx.glance:glance-material3:1.0.0")
    // fix duplicates
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    // exoplayer
    implementation("androidx.media3:media3-exoplayer:1.1.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.1.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.1.1")
    implementation("androidx.media3:media3-exoplayer-rtsp:1.1.1")
//    implementation("androidx.media3:media3-exoplayer-ima:1.1.0")
    implementation("androidx.media3:media3-datasource-cronet:1.1.1")
    implementation("androidx.media3:media3-datasource-okhttp:1.1.1")
    implementation("androidx.media3:media3-datasource-rtmp:1.1.1")
    implementation("androidx.media3:media3-ui:1.1.1")
//    implementation("androidx.media3:media3-ui-leanback:1.1.0")
    implementation("androidx.media3:media3-session:1.1.1")
    implementation("androidx.media3:media3-extractor:1.1.1")
    implementation("androidx.media3:media3-cast:1.1.1")
    implementation("androidx.media3:media3-exoplayer-workmanager:1.1.1")
    implementation("androidx.media3:media3-transformer:1.1.1")
    implementation("androidx.media3:media3-database:1.1.1")
    implementation("androidx.media3:media3-decoder:1.1.1")
    implementation("androidx.media3:media3-datasource:1.1.1")
    implementation("androidx.media3:media3-common:1.1.1")
    // images
    implementation("io.coil-kt:coil-base:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("io.coil-kt:coil-svg:2.4.0")
    implementation("io.coil-kt:coil-video:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.github.skydoves:landscapist-coil:2.2.10")
    // libs mismatch
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0").apply {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0").apply {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
    // fix duplicates
    configurations {
        all {
            resolutionStrategy {
                force("androidx.work:work-runtime-ktx:2.8.1")
            }
        }
    }
}