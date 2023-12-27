/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
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
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
//    id("org.jetbrains.dokka") version "1.8.10" apply false
//    id("io.gitlab.arturbosch.detekt")
//    id("com.github.ben-manes.versions")
//    id("com.bmuschko.docker-remote-api")
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation("com.android.tools.build:gradle-api:8.2.0")
    implementation("com.android.tools.build:gradle:8.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("com.squareup:javapoet:1.13.0")
//    implementation("com.bmuschko:gradle-docker-plugin:9.4.0")
//    implementation("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
//    implementation(kotlin("reflect", "1.8.21"))
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
//    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("net.lingala.zip4j:zip4j:1.2.4")
//    implementation("org.eclipse.jgit:org.eclipse.jgit:4.8.0.201706111038-r")
}

gradlePlugin {
    plugins {
        register("MainAppPlugin") {
            id = "MainAppPlugin"
            displayName = "MainAppPlugin"
            description = "Common app plugin to handle all stuffs needed."
            implementationClass = "org.mjdev.gradle.plugin.MainAppPlugin"
        }
//        register("DependencyUpdatePlugin") {
//            id = "DependencyUpdatePlugin"
//            displayName = "DependencyUpdatePlugin"
//            description = "Dependency update plugin"
//            implementationClass = "org.mjdev.gradle.plugin.DependencyUpdatePlugin"
//        }
//        register("AndroidCoreLibraryPlugin") {
//            id = "AndroidCoreLibraryPlugin"
//            implementationClass = "commons.AndroidCoreLibraryPlugin"
//        }
    }
}
