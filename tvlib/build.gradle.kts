@file:Suppress("UnstableApiUsage")

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
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
//    id("org.jetbrains.dokka") version "1.8.10"
}

android {
    namespace = "org.mjdev.tvlib"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
//        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    lint {
        checkReleaseBuilds = false
    }

//    tasks.dokkaGfm {
//        outputDirectory.set(File(projectDir, "../wiki/documentation"))
//    }

}

dependencies {
    // base libs
    implementation("androidx.core:core-ktx:1.12.0-alpha05")
    // reflect / debug purposes only
    implementation(kotlin("reflect", "1.8.21"))
    // window manager
    implementation("androidx.window:window:1.1.0")
    // compose base libs
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
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
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("io.coil-kt:coil-svg:2.4.0")
    implementation("io.coil-kt:coil-video:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.github.skydoves:landscapist-coil:2.2.2")
    // constraint layout // todo remove
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
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
    implementation("com.google.accompanist:accompanist-permissions:0.31.5-beta")
    // for previews
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    // pallette
    implementation("androidx.palette:palette-ktx:1.0.0")
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