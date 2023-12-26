/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.dependency

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlin.String

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.releaseImplementation(dependencyNotation: String): Dependency? =
    add("releaseImplementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependency: Dependency): Dependency? =
    add("androidTestImplementation", dependency)

fun DependencyHandler.testAnnotationProcessor(dependencyNotation: String): Dependency? =
    add("testAnnotationProcessor", dependencyNotation)

fun DependencyHandler.androidTestAnnotationProcessor(dependencyNotation: String): Dependency? =
    add("androidTestAnnotationProcessor", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.implementation(dependency: Dependency): Dependency? =
    add("implementation", dependency)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.ksp(dependencyNotation: String): Dependency? =
    add("ksp", dependencyNotation)

fun DependencyHandler.dokkaPlugin(dependencyNotation: String): Dependency? =
    add("dokkaPlugin", dependencyNotation)

fun DependencyHandler.mockDependencies() = arrayOf(
    implementation("io.mockk:mockk:1.9")
)

fun DependencyHandler.okHttpDependencies() = arrayOf(
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.12")),
    implementation("com.squareup.okhttp3:okhttp"),
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12"),
)

fun DependencyHandler.retrofitDependencies() = arrayOf(
    implementation("com.squareup.retrofit2:retrofit:2.9.0"),
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0"),
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"),
)

fun DependencyHandler.moshiDependencies() = arrayOf(
    implementation("com.squareup.moshi:moshi:1.15.0"),
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0"),
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"),
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0"),
)

fun DependencyHandler.exoPlayerDependencies() = arrayOf(
    implementation("androidx.media3:media3-exoplayer:1.2.0"),
    implementation("androidx.media3:media3-exoplayer-dash:1.2.0"),
    implementation("androidx.media3:media3-exoplayer-hls:1.2.0"),
    implementation("androidx.media3:media3-exoplayer-rtsp:1.2.0"),
    implementation("androidx.media3:media3-datasource-cronet:1.2.0"),
    implementation("androidx.media3:media3-datasource-okhttp:1.2.0"),
    implementation("androidx.media3:media3-datasource-rtmp:1.2.0"),
    implementation("androidx.media3:media3-ui:1.2.0"),
    implementation("androidx.media3:media3-session:1.2.0"),
    implementation("androidx.media3:media3-extractor:1.2.0"),
    implementation("androidx.media3:media3-cast:1.2.0"),
    implementation("androidx.media3:media3-exoplayer-workmanager:1.2.0"),
    implementation("androidx.media3:media3-transformer:1.2.0"),
    implementation("androidx.media3:media3-database:1.2.0"),
    implementation("androidx.media3:media3-decoder:1.2.0"),
    implementation("androidx.media3:media3-datasource:1.2.0"),
    implementation("androidx.media3:media3-common:1.2.0"),
    implementation("androidx.media3:media3-exoplayer-ima:1.1.0"),
    implementation("androidx.media3:media3-ui-leanback:1.1.0"),
)

fun DependencyHandler.composeDependencies() = arrayOf(
    implementation(platform("androidx.compose:compose-bom:2023.10.01")),
    implementation("androidx.compose.ui:ui-tooling"),
    implementation("androidx.activity:activity-compose:1.8.2"),
    // more icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4"),
    // tv compose
    implementation("androidx.tv:tv-foundation:1.0.0-alpha10"),
    implementation("androidx.tv:tv-material:1.0.0-alpha10"),
    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2"),
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.6"),
    // todo remove, dynamic background & colors
    implementation("androidx.compose.material3:material3:1.1.2"),
    // previews
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0"),
    // foundation
    implementation("androidx.compose.foundation:foundation:1.1.1"),
)

fun DependencyHandler.baseDependencies() = arrayOf(
    // startups
//    implementation("androidx.startup:startup-runtime:1.1.1"),
    // dokka
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.10"),
    // core
    implementation("androidx.core:core-ktx:1.12.0"),
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"),
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"),
    // reflect / todo : remove
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22"),
    // window manager
    implementation("androidx.window:window:1.2.0"),
    // fix duplicates
    implementation("androidx.work:work-runtime-ktx:2.9.0"),
)

fun DependencyHandler.sandwichDependencies() = arrayOf(
    implementation("com.github.skydoves:sandwich:2.0.5"),
    implementation("com.github.skydoves:sandwich-retrofit:2.0.5"),
)

fun DependencyHandler.glideDependencies() = arrayOf(
    // glide
    implementation("com.github.bumptech.glide:okhttp3-integration:4.16.0"),
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01"),
    // landscapist
    implementation("com.github.skydoves:landscapist-glide:2.2.13"),
    implementation("com.github.skydoves:landscapist-transformation:2.2.13"),
    implementation("com.github.skydoves:landscapist-palette:2.2.13"),
    implementation("com.github.skydoves:landscapist-placeholder:2.2.13"),
    // glide annotations
    ksp("com.github.bumptech.glide:ksp:4.16.0"),
)

fun DependencyHandler.daggerDependencies() = arrayOf(
    // dagger
    implementation("com.google.dagger:dagger:2.50"),
    // dagger android
    implementation("com.google.dagger:dagger-android:2.50"),
    implementation("com.google.dagger:dagger-android-support:2.50"),
    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.50"),
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0"),
    // dagger annotations
    kapt("com.google.dagger:dagger-compiler:2.50"),
    kapt("com.google.dagger:dagger-android-processor:2.50"),
    kapt("com.google.dagger:hilt-compiler:2.50"),
)

fun DependencyHandler.timberDependencies() = arrayOf(
    implementation("com.jakewharton.timber:timber:5.0.1"),
)

fun DependencyHandler.oauthDependencies() = arrayOf(
    implementation("com.auth0.android:auth0:2.10.2"),
    implementation("com.auth0.android:jwtdecode:2.0.2"),
)

fun DependencyHandler.testDependencies() = arrayOf(
    testImplementation("junit:junit:4.13.2"),
    androidTestImplementation("androidx.test.ext:junit:1.1.5"),
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1"),
    // anr
    implementation("com.github.anrwatchdog:anrwatchdog:1.4.0"),
)

fun DependencyHandler.encryptDependencies() = arrayOf(
    implementation("com.scottyab:aescrypt:0.0.1"),
)

fun DependencyHandler.permissionsDependencies() = arrayOf(
// permission
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha"),
)

fun DependencyHandler.widgetDependencies() = arrayOf(
    implementation("androidx.glance:glance-appwidget:1.0.0"),
    implementation("androidx.glance:glance-material:1.0.0"),
    implementation("androidx.glance:glance-material3:1.0.0"),
)

fun DependencyHandler.anotherDependencies() = arrayOf(
    // zxing
    implementation("com.google.zxing:core:3.5.2"),
    // svg
    implementation("com.caverock:androidsvg-aar:1.4"),
    // pallette
    implementation("androidx.palette:palette-ktx:1.0.0"),
    // lottie
    implementation("com.airbnb.android:lottie-compose:6.2.0"),
    // exif info
    implementation("androidx.exifinterface:exifinterface:1.3.7"),
    // jsoup
    implementation("org.jsoup:jsoup:1.17.1"),
    // dm
    implementation("com.dailymotion.dailymotion-sdk-android:sdk:0.2.12"),
    // dynamic theme
    implementation("com.google.android.material:material:1.11.0"),
    // stripe payments
    implementation("com.stripe:stripe-android:20.33.0"),
    implementation("com.stripe:stripecardscan:20.33.0"),
    implementation("com.stripe:financial-connections:20.33.0"),
    // scrape
    implementation("net.sourceforge.htmlunit:htmlunit-android:2.67.0"),
    // flip card
    implementation("com.wajahatkarim:flippable:1.5.4"),
    // ui controller
//    implementation("com.google.accompanist:accompanist-systemuicontroller:0.23.1"),
    // insets
//    implementation("com.google.accompanist:accompanist-insets:0.23.1"),
    // indicators
//    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1"),
    // settings
//    implementation("com.github.alorma:compose-settings-ui-m3:1.3.0")
    // yt
    // ...
    // a.i.
//    implementation("com.google.ai.client.generativeai:generativeai:0.1.1"),
)

fun DependencyHandler.mjdevTvLib() = arrayOf(
    implementation(project(mapOf("path" to ":tvlib")))
)