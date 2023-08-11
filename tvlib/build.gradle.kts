@file:Suppress("UnstableApiUsage")

import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.javaVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.kotlinCompilerExtVersion
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.projectCompileSdk
import org.mjdev.gradle.plugin.MainAppPlugin.Companion.projectMinSdk

/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.devtools.ksp")
//    id("org.jetbrains.dokka") version "1.8.10"
}

android {
    namespace = "org.mjdev.tvlib"
    compileSdk = projectCompileSdk

    defaultConfig {
        minSdk = projectMinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = kotlinCompilerExtVersion
    }

    packaging {
        resources.excludes.apply {
            add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    lint {
        checkReleaseBuilds = false
    }

    hilt {
        enableAggregatingTask = true
    }

//    tasks.dokkaGfm {
//        outputDirectory.set(File(projectDir, "../wiki/documentation"))
//    }

}

dependencies {
    // base libs
    implementation("androidx.core:core-ktx:1.12.0-rc01")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // reflect / debug purposes only
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    // window manager
    implementation("androidx.window:window:1.1.0")
    // compose base libs
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.7.2")
    // more icons
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    // tv compose
    implementation("androidx.tv:tv-foundation:1.0.0-alpha08")
    implementation("androidx.tv:tv-material:1.0.0-alpha08")
    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.0")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    // moshi json
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    // sandwich
    implementation ("com.github.skydoves:sandwich:1.3.8")
    // image loading
    implementation("io.coil-kt:coil-base:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("io.coil-kt:coil-svg:2.4.0")
    implementation("io.coil-kt:coil-video:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.github.skydoves:landscapist-coil:2.2.5")
    // debug
    implementation("com.jakewharton.timber:timber:5.0.1")
    // dagger core
    implementation("com.google.dagger:dagger:2.47")
    implementation("androidx.compose.material3:material3:1.1.1")
    kapt("com.google.dagger:dagger-compiler:2.47")
    // dagger android
    implementation("com.google.dagger:dagger-android:2.47")
    implementation("com.google.dagger:dagger-android-support:2.47")
    kapt("com.google.dagger:dagger-android-processor:2.47")
    // dagger - hilt
    implementation("com.google.dagger:hilt-android:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("com.google.dagger:hilt-compiler:2.47")
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
    implementation("com.google.accompanist:accompanist-permissions:0.33.0-alpha")
    // for previews
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    // pallette
    implementation("androidx.palette:palette-ktx:1.0.0")
    // lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")
    // exif info
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    // anr
    implementation("com.github.anrwatchdog:anrwatchdog:1.4.0")
    // dm
    implementation("com.dailymotion.dailymotion-sdk-android:sdk:0.2.12")
    // yt
    // ...
    // constraints
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")?.apply {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")?.apply {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
    // testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}