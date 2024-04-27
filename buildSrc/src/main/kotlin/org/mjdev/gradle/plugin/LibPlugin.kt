/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.dokka.Platform
import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin
import org.mjdev.gradle.base.BasePlugin
import org.mjdev.gradle.plugin.config.LibConfig

@Suppress("UnstableApiUsage")
class LibPlugin : BasePlugin() {
    private val projectNamespace = "org.mjdev.tvlib"
    private val projectCompileSdk = 34
    private val projectMinSdk = 21
    private val projectJavaVersion = JavaVersion.VERSION_17
    private val projectKotlinCompilerVersion = "1.5.8"
    private val projectExcludes = listOf(
        "META-INF/",
        "/META-INF/{AL2.0,LGPL2.1}",
        "/META-INF/DEPENDENCIES",
        "/mozilla/public-suffix-list.txt",
        "okhttp3/",
        "kotlin/",
        "org/",
        ".properties",
        ".bin",
    )
    private val projectProguardFile = "proguard-android-optimize.txt"
    private val projectProguardRulesFile = "proguard-rules.pro"
    private val projectJacocoVersion = "0.8.8"
    private val projectLibVersionsFile = "gradle/libs.versions.toml"
    private val configFieldName = "libConfig"

    override fun Project.work() {
//        loadVersionCatalog(projectLibVersionsFile)
        extension<LibConfig>(configFieldName)
        apply(plugin ="version-catalog")
        apply(plugin = "kotlin-android")
        apply(plugin = "com.android.library")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "kotlin-parcelize")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "com.google.dagger.hilt.android")
        apply(plugin = "dagger.hilt.android.plugin")
        apply(plugin = "io.objectbox")
        apply(DetektPlugin::class)
        apply(KotlinterPlugin::class)
        apply("org.jetbrains.dokka")
        configure<LibraryExtension> {
            namespace = projectNamespace
            compileSdk = projectCompileSdk
            compileOptions {
                sourceCompatibility = projectJavaVersion
                targetCompatibility = projectJavaVersion
            }
            defaultConfig {
                minSdk = projectMinSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = projectKotlinCompilerVersion
            }
            packaging {
                resources {
                    excludes.addAll(projectExcludes)
                }
            }
            buildTypes {
                debug {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile(projectProguardFile),
                        projectProguardRulesFile
                    )
                }
                release {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile(projectProguardFile),
                        projectProguardRulesFile
                    )
                }
            }
            sourceSets {
                getByName("main") { jniLibs.srcDirs() }
            }
            lint {
                checkAllWarnings = true
                showAll = true
                explainIssues = true
                abortOnError = true
                warningsAsErrors = true
                disable += "UnusedIds"
            }
            testOptions {
                unitTests.isReturnDefaultValues = true
            }
            testCoverage {
                jacocoVersion = projectJacocoVersion
            }
        }
        afterEvaluate {
            val libConfig = project.extension<LibConfig>()
            kotlinCompileOptions{
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = projectJavaVersion.toString()
                }
            }
            detektTask {
                if (libConfig.autoCorrectCode) runAfterAssembleTask()
            }
            dokkaTask {
                outputDirectory.set(
                    project.rootDir.resolve(libConfig.documentationDir)
                )
                moduleName.set(project.name)
                suppressObviousFunctions.set(false)
                dokkaSourceSets.configureEach {
                    offlineMode.set(false)
                    includeNonPublic.set(true)
                    skipDeprecated.set(false)
                    reportUndocumented.set(true)
                    skipEmptyPackages.set(false)
                    platform.set(Platform.jvm)
                    jdkVersion.set(17)
                    noStdlibLink.set(false)
                    noJdkLink.set(false)
                    noAndroidSdkLink.set(false)
                }
                if (libConfig.createDocumentation) runAfterAssembleTask()
            }
            configure<DetektExtension> {
                @Suppress("DEPRECATION")
                config = files(project.rootDir.resolve(libConfig.detectConfigFile))
            }
            configure<KotlinterExtension> {
                ignoreFailures = false
                reporters = arrayOf("checkstyle", "plain")
            }
//            configurations.getByName("releaseRuntimeClasspath") {
//                resolutionStrategy.activateDependencyLocking()
//            }
//            configurations.getByName("debugRuntimeClasspath") {
//                resolutionStrategy.activateDependencyLocking()
//            }
        }
        dependencyLocking {
//            lockMode.set(LockMode.STRICT)
        }
        dependencies {
//            add(IMPLEMENTATION, Dependencies.AndroidX.ANNOTATION)
//            add(IMPLEMENTATION, Dependencies.AndroidX.APPCOMPAT)
//            add(IMPLEMENTATION, Dependencies.Kotlin.STD_LIB)
//            // core
//            implementation(libs.androidx.ktx)
//            implementation(libs.kotlinx.coroutines.android)
//            implementation(libs.kotlinx.coroutines.core)
//            // reflect / todo : remove
//            implementation(libs.kotlin.reflect)
//            // window manager
//            implementation(libs.androidx.window)
//            // fix duplicates
//            implementation(libs.androidx.work.runtime.ktx)
//            // compose
//            implementation(platform(libs.androidx.compose.bom))
//            implementation(libs.androidx.compose.ui.tooling)
//            implementation(libs.androidx.compose.activity)
//            // more icons
//            implementation(libs.androidx.material.icons.extended)
//            // tv compose
//            implementation(libs.androidx.tv.foundation)
//            implementation(libs.androidx.tv.material)
//            // view model
//            implementation(libs.androidx.compose.lifecycle.viewmodel)
//            // navigation
//            implementation(libs.androidx.compose.navigation)
//            // todo remove, dynamic background & colors
//            implementation(libs.androidx.material3)
//            // previews
//            debugImplementation(libs.androidx.customview.poolingcontainer)
//            // foundation
//            implementation(libs.androidx.foundation)
//            // dagger
//            implementation(libs.dagger)
//            // dagger android
//            implementation(libs.dagger.android)
//            implementation(libs.dagger.android.support)
//            // dagger hilt
//            implementation(libs.dagger.hilt.android)
//            implementation(libs.androidx.compose.hilt.navigation)
//            kapt(libs.dagger.compiler)
//            kapt(libs.dagger.android.processor)
//            kapt(libs.dagger.hilt.compiler)
//            // moshi
//            implementation(libs.moshi)
//            implementation(libs.moshi.retrofit.converter)
//            ksp(libs.moshi.kotlin.codegen)
//            // okhttp
//            implementation(platform(libs.okhttp3.bom))
//            implementation(libs.okhttp3)
//            implementation(libs.okhttp3.logging.interceptor)
//            // timber
//            implementation(libs.timber)
//            // retrofit
//            implementation(libs.retrofit)
//            implementation(libs.retrofit2.kotlin.coroutines.adapter)
//            // sandwich
//            implementation(libs.sandwich)
//            implementation(libs.sandwich.retrofit)
//            // glide
//            implementation(libs.glide.okhttp3.integration)
//            implementation(libs.glide.compose)
//            ksp(libs.glide.ksp)
//            // landscapist
//            implementation(libs.landscapist.glide)
//            implementation(libs.landscapist.transformation)
//            implementation(libs.landscapist.palette)
//            implementation(libs.landscapist.placeholder)
//            // exif
//            implementation(libs.androidx.exifinterface)
//            // exoPlayer
//            implementation(libs.androidx.media3.exoplayer)
//            implementation(libs.androidx.media3.exoplayer.dash)
//            implementation(libs.androidx.media3.exoplayer.hls)
//            implementation(libs.androidx.media3.exoplayer.rtsp)
//            implementation(libs.androidx.media3.datasource.cronet)
//            implementation(libs.androidx.media3.datasource.okhttp)
//            implementation(libs.androidx.media3.datasource.rtmp)
//            implementation(libs.androidx.media3.ui)
//            implementation(libs.androidx.media3.session)
//            implementation(libs.androidx.media3.extractor)
//            implementation(libs.androidx.media3.cast)
//            implementation(libs.androidx.media3.exoplayer.workmanager)
//            implementation(libs.androidx.media3.transformer)
//            implementation(libs.androidx.media3.database)
//            implementation(libs.androidx.media3.decoder)
//            implementation(libs.androidx.media3.datasource)
//            implementation(libs.androidx.media3.common)
//            implementation(libs.androidx.media3.exoplayer.ima)
//            implementation(libs.androidx.media3.ui.leanback)
//            // encrypt
//            implementation(libs.aescrypt)
//            // permission
//            implementation(libs.accompanist.permissions)
//            // zxing
//            implementation(libs.zxing.core)
//            // svg
//            implementation(libs.androidsvg.aar)
//            // pallette
//            implementation(libs.androidx.palette.ktx)
//            // lottie
//            implementation(libs.compose.lottie)
//            // jsoup
//            implementation(libs.jsoup)
//            // dm
//            implementation(libs.dailymotion.sdk.android)
//            // dynamic theme
//            implementation(libs.android.material)
//            // widget
//            implementation(libs.androidx.glance.appwidget)
//            implementation(libs.androidx.glance.material)
//            implementation(libs.androidx.glance.material3)
//            // test
//            testImplementation(libs.junit)
//            androidTestImplementation(libs.androidx.junit)
//            androidTestImplementation(libs.androidx.espresso.core)
//            // anr
//            implementation(libs.anrwatchdog)
//            // oauth
//            implementation(libs.auth0)
//            implementation(libs.android.jwtdecode)
        }
    }
}
