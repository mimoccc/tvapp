/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    alias(libs.plugins.gradle.devtools.google.ksp) apply false
    alias(libs.plugins.gradle.ktlint) apply false
    alias(libs.plugins.gradle.versions) apply true
    alias(libs.plugins.gradle.catalogs.update) apply true
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
        classpath(libs.gradle.markdown.plugin)
        classpath(libs.gradle.ospackage.plugin)
    }

    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
    }
}

versionCatalogUpdate {
    sortByKey = true
    pin {
//        versions = ["my-version-name", "other-version"]
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        groups = ["com.somegroup", "com.someothergroup"]
    }
    keep {
//        versions = ["my-version-name", "other-version"]
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        groups = ["com.somegroup", "com.someothergroup"]
        keepUnusedVersions = false
        keepUnusedLibraries = false
        keepUnusedPlugins = false
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
