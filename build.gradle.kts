/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    kotlin("android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
    }
    dependencies {
        classpath("com.android.tools:r8:8.1.56")
        classpath("io.objectbox:objectbox-gradle-plugin:3.6.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
    }
}