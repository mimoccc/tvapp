/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    kotlin("android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
    }
    dependencies {
        classpath("com.android.tools:r8:8.1.56")
        classpath("io.objectbox:objectbox-gradle-plugin:3.6.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}