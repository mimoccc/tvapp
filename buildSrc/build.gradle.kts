/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@Suppress("DEPRECATION", "JcenterRepositoryObsolete")
repositories {
    jcenter()
    google()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://www.jitpack.io")
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://oss.sonatype.org/content/repositories/public")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    maven(url = uri("https://packages.jetbrains.team/maven/p/reflekt/reflekt"))
}

plugins {
    `kotlin-dsl`
//    id("org.jetbrains.reflekt") version "1.5.30"
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation(kotlin("reflect"))
    implementation(libs.gradle.api)
    implementation(libs.gradle)
    implementation(libs.gradle.kotlin.plugin)
    implementation(libs.gradle.detekt.plugin)
    implementation(libs.gradle.dokka.plugin)
    implementation(libs.gradle.markdown.plugin)
    implementation(libs.gradle.kotlinter)
    implementation(libs.jsoup)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.poet.java)
    implementation(libs.poet.kotlin)
    implementation(libs.eclipse.jgit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.moshi.kotlin)
    implementation(libs.kotlin.mockito)
    implementation(libs.junit)
    implementation(libs.kotlin.compose.compiler)
    implementation(libs.symbol.processing.api)
//    implementation(libs.kotlin.reflekt.dsl)
//    implementation(libs.symbol.processing.embeddable)
//    implementation(libs.apk.parser)
//    implementation(libs.gradle.docker.plugin)
//    implementation(libs.korim.jvm)
//    implementation(libs.photofilter)
//    implementation(libs.dependency.analysis.gradle.plugin)
//    implementation(libs.google.api.services.androidpublisher)
//    implementation(libs.ini4j)
}

gradlePlugin {
    plugins {
        register("AppPlugin") {
            id = "AppPlugin"
            displayName = "AppPlugin"
            description = "Common application plugin to handle all stuffs needed."
            implementationClass = "org.mjdev.gradle.plugin.AppPlugin"
        }
        register("LibPlugin") {
            id = "LibPlugin"
            displayName = "LibPlugin"
            description = "Common library plugin to handle all stuffs needed."
            implementationClass = "org.mjdev.gradle.plugin.LibPlugin"
        }
    }
}