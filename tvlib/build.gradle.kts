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
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "RECOMPOSE_ENABLED", "false")
        }
        release {
            isMinifyEnabled = false // todo
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "RECOMPOSE_ENABLED", "false")
        }
        create("recomposing") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "RECOMPOSE_ENABLED", "true")
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
    // startups
//    implementation("androidx.startup:startup-runtime:1.1.1")
    // base libs
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // reflect / todo : remove
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
    // window manager
    implementation("androidx.window:window:1.2.0")
    // compose base libs
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.8.1")
    // more icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    // tv compose
    implementation("androidx.tv:tv-foundation:1.0.0-alpha10")
    implementation("androidx.tv:tv-material:1.0.0-alpha10")
    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    // okhttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.11"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    // moshi json
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    // sandwich
    implementation("com.github.skydoves:sandwich:2.0.4")
    implementation("com.github.skydoves:sandwich-retrofit:2.0.4")
    // image loading
    implementation("com.github.bumptech.glide:okhttp3-integration:4.16.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    implementation("com.github.skydoves:landscapist-glide:2.2.12")
    implementation("com.github.skydoves:landscapist-transformation:2.2.12")
    implementation("com.github.skydoves:landscapist-palette:2.2.12")
    implementation("com.github.skydoves:landscapist-placeholder:2.2.12")
    ksp("com.github.bumptech.glide:ksp:4.15.1")
    // zxing
    implementation("com.google.zxing:core:3.5.2")
    // svg
    implementation("com.caverock:androidsvg-aar:1.4")
    // oauth
    implementation("com.auth0.android:auth0:2.10.2")
    implementation("com.auth0.android:jwtdecode:2.0.2")
    // debug
    implementation("com.jakewharton.timber:timber:5.0.1")
    // dagger core
    implementation("com.google.dagger:dagger:2.49")
    implementation("androidx.compose.material3:material3:1.1.2")
    kapt("com.google.dagger:dagger-compiler:2.49")
    // dagger android
    implementation("com.google.dagger:dagger-android:2.49")
    implementation("com.google.dagger:dagger-android-support:2.49")
    kapt("com.google.dagger:dagger-android-processor:2.49")
    // dagger - hilt
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("com.google.dagger:hilt-compiler:2.49")
    // encrypt data
    implementation("com.scottyab:aescrypt:0.0.1")
    // exoplayer
    implementation("androidx.media3:media3-exoplayer:1.2.0")
    implementation("androidx.media3:media3-exoplayer-dash:1.2.0")
    implementation("androidx.media3:media3-exoplayer-hls:1.2.0")
    implementation("androidx.media3:media3-exoplayer-rtsp:1.2.0")
//    implementation("androidx.media3:media3-exoplayer-ima:1.1.0")
    implementation("androidx.media3:media3-datasource-cronet:1.2.0")
    implementation("androidx.media3:media3-datasource-okhttp:1.2.0")
    implementation("androidx.media3:media3-datasource-rtmp:1.2.0")
    implementation("androidx.media3:media3-ui:1.2.0")
//    implementation("androidx.media3:media3-ui-leanback:1.1.0")
    implementation("androidx.media3:media3-session:1.2.0")
    implementation("androidx.media3:media3-extractor:1.2.0")
    implementation("androidx.media3:media3-cast:1.2.0")
    implementation("androidx.media3:media3-exoplayer-workmanager:1.2.0")
    implementation("androidx.media3:media3-transformer:1.2.0")
    implementation("androidx.media3:media3-database:1.2.0")
    implementation("androidx.media3:media3-decoder:1.2.0")
    implementation("androidx.media3:media3-datasource:1.2.0")
    implementation("androidx.media3:media3-common:1.2.0")
    // permission
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
    // for previews
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    // pallette
    implementation("androidx.palette:palette-ktx:1.0.0")
    // lottie
    implementation("com.airbnb.android:lottie-compose:6.2.0")
    // exif info
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    // anr
    implementation("com.github.anrwatchdog:anrwatchdog:1.4.0")
    // jsoup
    implementation("org.jsoup:jsoup:1.17.1")
    // dm
    implementation("com.dailymotion.dailymotion-sdk-android:sdk:0.2.12")
    // dynamic theme
    implementation("com.google.android.material:material:1.10.0")
    // stripe payments
//    implementation("com.stripe:stripe-android:20.33.0")
//    implementation("com.stripe:stripecardscan:20.33.0")
//    implementation("com.stripe:financial-connections:20.33.0")
    // scrape
    implementation("it.skrape:skrapeit:1.2.2")
    implementation("it.skrape:skrapeit-browser-fetcher:1.2.2")
//    implementation("org.jsoup:jsoup:1.16.1")
//    implementation("net.sourceforge.htmlunit:htmlunit-android:2.67.0")
    // yt
    // ...
    // constraints
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0").apply {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0").apply {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
    // testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
