/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("DEPRECATION")

@Suppress("JcenterRepositoryObsolete")
repositories {
    jcenter()
    google()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://www.jitpack.io")
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://oss.sonatype.org/content/repositories/public")
}

plugins {
    `kotlin-dsl`
//    `java-gradle-plugin`
//    `groovy-gradle-plugin`
//    id("org.jetbrains.dokka") version "1.8.10"
//    id("com.google.devtools.ksp")
}

dependencies {
    implementation(localGroovy())
    implementation(gradleApi())
//    implementation(kotlin("reflect", "1.8.21"))
//    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("com.twelvemonkeys.imageio:imageio-core:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-webp:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-png:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-bmp:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-jpeg:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-tiff:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-batik:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-metadata:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-psd:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-icns:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-pnm:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-sgi:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-pict:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-tga:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-pcx:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-iff:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-pdf:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-pnm:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-hdr:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-clippath:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-thumbsdb:3.9.4")
//    implementation("com.twelvemonkeys.imageio:imageio-xwd:3.9.4")
//    implementation("com.soywiz.korlibs.korim:korim-jvm:4.0.2")
//    implementation("com.github.mukeshsolanki:photofilter:2.0.2")
//    implementation("gradle.plugin.com.autonomousapps:dependency-analysis-gradle-plugin:0.10.0")
//    implementation("com.google.apis:google-api-services-androidpublisher:v3-rev41-1.25.0")
//    implementation("com.squareup:javapoet:1.13.0")
//    implementation("com.squareup:kotlinpoet:1.13.2")
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
//    implementation("org.ini4j:ini4j:0.5.4")
//    implementation("org.jsoup:jsoup:1.15.4")
//    implementation("net.lingala.zip4j:zip4j:1.2.4")
//    implementation("org.eclipse.jgit:org.eclipse.jgit:4.8.0.201706111038-r")
//    implementation("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
}

gradlePlugin {
    plugins {
        create("MainAppPlugin") {
            id = "MainAppPlugin"
            implementationClass = "org.mjdev.gradle.plugin.MainAppPlugin"
        }
    }
}