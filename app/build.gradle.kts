/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    id("AppPlugin")
}

// todo
appConfig {
    autoCorrectCode = false
    createDocumentation = true
//    createReleaseNotes = true
//    renameApkOutputByAppID = false
//    createReleaseZip = true
//    createInfoClass = false
//    buildTypeInLauncherIcon = false
}

dependencies {
    implementation(project(mapOf("path" to ":tvlib")))

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.activity)
    // more icons
    implementation(libs.androidx.material.icons.extended)
    // tv compose
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    // view model
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    // navigation
    implementation(libs.androidx.compose.navigation)
    // todo remove, dynamic background & colors
    implementation(libs.androidx.material3)
    // previews
    debugImplementation(libs.androidx.customview.poolingcontainer)
    // foundation
    implementation(libs.androidx.foundation)
    // dagger
    implementation(libs.dagger)
    // dagger android
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    // dagger hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.compose.hilt.navigation)
    // dagger annotations
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.android.processor)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.moshi)
    implementation(libs.moshi.retrofit.converter)
    ksp(libs.moshi.kotlin.codegen)
    // okhttp
    implementation(platform(libs.okhttp3.bom))
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    // timber
    implementation(libs.timber)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    // sandwich
    implementation(libs.sandwich)
    implementation(libs.sandwich.retrofit)
    // glide
    implementation(libs.glide.okhttp3.integration)
    implementation(libs.glide.compose)
    ksp(libs.glide.ksp)
    // landscapist
    implementation(libs.landscapist.glide)
    implementation(libs.landscapist.transformation)
    implementation(libs.landscapist.palette)
    implementation(libs.landscapist.placeholder)
    // exif
    implementation(libs.androidx.exifinterface)
    // exoPlayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.exoplayer.hls)
    implementation(libs.androidx.media3.exoplayer.rtsp)
    implementation(libs.androidx.media3.datasource.cronet)
    implementation(libs.androidx.media3.datasource.okhttp)
    implementation(libs.androidx.media3.datasource.rtmp)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.session)
    implementation(libs.androidx.media3.extractor)
    implementation(libs.androidx.media3.cast)
    implementation(libs.androidx.media3.exoplayer.workmanager)
    implementation(libs.androidx.media3.transformer)
    implementation(libs.androidx.media3.database)
    implementation(libs.androidx.media3.decoder)
    implementation(libs.androidx.media3.datasource)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer.ima)
    implementation(libs.androidx.media3.ui.leanback)
    // encrypt
    implementation(libs.aescrypt)
    // permission
    implementation(libs.accompanist.permissions)
    // another
    // zxing
    implementation(libs.zxing.core)
    // svg
    implementation(libs.androidsvg.aar)
    // pallette
    implementation(libs.androidx.palette.ktx)
    // lottie
    implementation(libs.compose.lottie)
    // jsoup
    implementation(libs.jsoup)
    // dm
    implementation(libs.dailymotion.sdk.android)
    // dynamic theme
    implementation(libs.android.material)
    // widget
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material)
    implementation(libs.androidx.glance.material3)
    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // anr
    implementation(libs.anrwatchdog)
    // oauth
    implementation(libs.auth0)
    implementation(libs.android.jwtdecode)
}
