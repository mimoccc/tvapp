/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    alias(libs.plugins.android.dagger.hilt) apply false
    alias(libs.plugins.devtools.google.ksp) apply false
    alias(libs.plugins.gradle.ktlint) apply false
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://storage.googleapis.com/r8-releases/raw")
        maven("https://mvn.dailymotion.com/repository/releases/")
        google()
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.gradle.api)
        classpath(libs.gradle.kotlin.plugin)
        classpath(libs.gradle.tools.r8)
        classpath(libs.gradle.detekt.plugin)
        classpath(libs.gradle.objectbox.plugin)
        classpath(libs.gradle.paparazzi.plugin)
        classpath(libs.gradle.kotlinter)
        classpath(libs.gradle.dokka.plugin)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
