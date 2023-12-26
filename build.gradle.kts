/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

plugins {
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2" apply true
    id("org.jetbrains.dokka") version "1.8.10" apply true
    // todo ktorfit https://foso.github.io/Ktorfit/
//    id("de.jensklingenberg.ktorfit") version "1.10.2" apply true
}

buildscript {

    repositories {
        mavenCentral()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("com.android.tools.build:gradle-api:8.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.android.tools:r8:8.2.33")
        classpath("io.objectbox:objectbox-gradle-plugin:3.7.1")
//        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
//        classpath("com.diffplug.spotless:spotless-plugin-gradle:6.4.2")
//        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0-RC2")
//        classpath("com.bmuschko:gradle-docker-plugin:9.4.0")
//        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
//        classpath("com.newrelic.agent.android:agent-gradle-plugin:5.9.0")
//        classpath ("app.cash.paparazzi:paparazzi-gradle-plugin:1.3.1")
    }
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.dokka")
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
