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
import org.mjdev.gradle.plugin.javaVersion
import org.mjdev.gradle.plugin.kotlinCompilerExtVersion
import org.mjdev.gradle.plugin.projectCompileSdk
import org.mjdev.gradle.plugin.projectMinSdk

/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
//    kotlinAndroid()
//    kotlinKapt()
//    androidLibrary()
//    hilt()
//    ksp()
//    mainAppPlugin()
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
        }
        release {
            isMinifyEnabled = false // todo
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
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
        with(resources.excludes) {
            add("/META-INF/{AL2.0,LGPL2.1}")
            add("META-INF/")
            add("okhttp3/")
            add("kotlin/")
            add("org/")
            add(".properties")
            add(".bin")
        }
    }

    lint {
        checkReleaseBuilds = false
    }

    hilt {
        enableAggregatingTask = true
    }

    kapt {
        correctErrorTypes = true
    }

    tasks {
        dokkaGfm {
            outputDirectory.set(File(projectDir, "../wiki/documentation/"))
        }
    }
}

dependencies {
    baseDependencies()
    composeDependencies()
    okHttpDependencies()
    retrofitDependencies()
    moshiDependencies()
    sandwichDependencies()
    glideDependencies()
    oauthDependencies()
    timberDependencies()
    daggerDependencies()
    exoPlayerDependencies()
    testDependencies()
    encryptDependencies()
    permissionsDependencies()
    anotherDependencies()
}
