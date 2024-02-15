/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

import org.mjdev.gradle.dependency.anotherDependencies
import org.mjdev.gradle.dependency.baseDependencies
import org.mjdev.gradle.dependency.composeDependencies
import org.mjdev.gradle.dependency.daggerDependencies
import org.mjdev.gradle.dependency.encryptDependencies
import org.mjdev.gradle.dependency.exoPlayerDependencies
import org.mjdev.gradle.dependency.glideDependencies
import org.mjdev.gradle.dependency.moshiDependencies
import org.mjdev.gradle.dependency.oauthDependencies
import org.mjdev.gradle.dependency.okHttpDependencies
import org.mjdev.gradle.dependency.permissionsDependencies
import org.mjdev.gradle.dependency.retrofitDependencies
import org.mjdev.gradle.dependency.sandwichDependencies
import org.mjdev.gradle.dependency.testDependencies
import org.mjdev.gradle.dependency.timberDependencies

plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("io.objectbox")
    id("org.jetbrains.dokka")
    id("LibPlugin")
}

libConfig {
//    namespace = "org.mjdev.tvlib"
//    compileSdk = 34
//    minSdk = 21
}

android {
    namespace = "org.mjdev.tvlib"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = 21
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes.apply {
                add("META-INF/")
                add("/META-INF/{AL2.0,LGPL2.1}")
                add("/META-INF/DEPENDENCIES")
                add("/mozilla/public-suffix-list.txt")
                add("okhttp3/")
                add("kotlin/")
                add("org/")
                add(".properties")
                add(".bin")
            }
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false // todo
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
//    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.10")
    baseDependencies()
    timberDependencies()
    daggerDependencies()
    composeDependencies()
    okHttpDependencies()
    retrofitDependencies()
    moshiDependencies()
    sandwichDependencies()
    glideDependencies()
    oauthDependencies()
    exoPlayerDependencies()
    encryptDependencies()
    permissionsDependencies()
    anotherDependencies()
    testDependencies()
//    implementation ("com.github.jeziellago:compose-markdown:0.2.6")
//    implementation("org.jetbrains:markdown:0.5.0")
}
