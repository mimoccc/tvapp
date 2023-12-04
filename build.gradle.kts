/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    kotlin("android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2" apply true
    // todo ktorfit https://foso.github.io/Ktorfit/
//    id("de.jensklingenberg.ktorfit") version "1.10.2" apply true
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools:r8:8.1.72")
        classpath("io.objectbox:objectbox-gradle-plugin:3.6.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("com.android.tools.build:gradle-api:8.1.4")
//        classpath("com.newrelic.agent.android:agent-gradle-plugin:5.9.0")
//        classpath ("app.cash.paparazzi:paparazzi-gradle-plugin:1.3.1")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
