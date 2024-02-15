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
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
//    implementation(kotlin("reflect", "1.8.21"))
    implementation("com.android.tools.build:gradle-api:8.2.2")
    implementation("com.android.tools.build:gradle:8.2.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.4")
    implementation("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.squareup:kotlinpoet:1.13.2")
    implementation("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
    implementation("org.jetbrains.dokka:gfm-plugin:1.8.10")
    implementation("org.jetbrains.dokka:android-documentation-plugin:1.9.10")
    implementation("org.eclipse.jgit:org.eclipse.jgit:4.8.0.201706111038-r")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation ("org.mockito.kotlin:mockito-kotlin:5.2.1")
    implementation ("junit:junit:4.13.2")
//    implementation("com.bmuschko:gradle-docker-plugin:9.4.0")
//    implementation("com.soywiz.korlibs.korim:korim-jvm:4.0.2")
//    implementation("com.github.mukeshsolanki:photofilter:2.0.2")
//    implementation("gradle.plugin.com.autonomousapps:dependency-analysis-gradle-plugin:0.10.0")
//    implementation("com.google.apis:google-api-services-androidpublisher:v3-rev41-1.25.0")
//    implementation("org.ini4j:ini4j:0.5.4")
//    implementation("org.jsoup:jsoup:1.15.4")
//    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("net.lingala.zip4j:zip4j:1.2.4")
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