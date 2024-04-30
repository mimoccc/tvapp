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
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.LockMode
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.dokka.Platform
import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin
import org.mjdev.gradle.base.BasePlugin
import org.mjdev.gradle.plugin.config.LibConfig
import org.mjdev.gradle.extensions.asInt
import org.mjdev.gradle.extensions.runAfterAssembleTask
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.libs
import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.debugImplementation
import org.mjdev.gradle.extensions.kapt
import org.mjdev.gradle.extensions.testImplementation
import org.mjdev.gradle.extensions.androidTestImplementation
import org.mjdev.gradle.extensions.ksp
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.detektTask
import org.mjdev.gradle.extensions.dokkaTask
import org.mjdev.gradle.extensions.apply
import org.mjdev.gradle.extensions.int
import org.mjdev.gradle.extensions.string
import org.mjdev.gradle.extensions.projectName
import org.mjdev.gradle.extensions.runConfigured

@Suppress("UnstableApiUsage")
class LibPlugin : BasePlugin() {
    private val configFieldName = "libConfig"

    override fun Project.work() {
        val libConfig = extension<LibConfig>(configFieldName)
        apply(plugin = "kotlin-android")
        apply(plugin = "com.android.library")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "kotlin-parcelize")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "com.google.dagger.hilt.android")
        apply(plugin = "dagger.hilt.android.plugin")
        apply(plugin = "io.objectbox")
        apply("org.jetbrains.dokka")
        apply(DetektPlugin::class)
        apply(KotlinterPlugin::class)
        configure<LibraryExtension> {
            // todo : move
            namespace = libs.versions.lib.namespace.string
            // todo : move
            compileSdk = libs.versions.compileSdk.int
            compileOptions {
                // todo : move
                sourceCompatibility = libConfig.javaVersion
                // todo : move
                targetCompatibility = libConfig.javaVersion
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
            composeOptions {
                // todo : move
                kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.version.string
            }
            defaultConfig {
                // todo : move
                minSdk = libs.versions.minSdk.int
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            packaging {
                resources {
                    excludes.addAll(libConfig.projectExcludes)
                }
            }
            buildTypes {
                debug {
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile(libConfig.projectProguardFile),
                        libConfig.projectProguardRulesFile
                    )
                }
                release {
                    // todo minify
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile(libConfig.projectProguardFile),
                        libConfig.projectProguardRulesFile
                    )
                }
            }
            sourceSets {
                getByName("main") {
                    jniLibs.srcDirs()
                }
            }
            lint {
                checkAllWarnings = true
                showAll = true
                explainIssues = true
                // todo : move
                abortOnError = false
                // todo : move
                warningsAsErrors = false
                disable += "UnusedIds"
            }
            testOptions {
                unitTests.isReturnDefaultValues = true
            }
            testCoverage {
                jacocoVersion = libs.versions.jacoco.version.string
            }
        }
        runConfigured<LibConfig> {
            kotlinCompileOptions {
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = javaVersion.toString()
                }
            }
            detektTask {
                enabled = autoCorrectCode
                runAfterAssembleTask()
            }
            dokkaTask {
                enabled = createDocumentation
                outputDirectory.set(rootDir.resolve(documentationDir))
                moduleName.set(projectName)
                suppressObviousFunctions.set(false)
                dokkaSourceSets.configureEach {
                    offlineMode.set(false)
                    includeNonPublic.set(false)
                    skipDeprecated.set(false)
                    failOnWarning.set(failOnDocumentationWarning)
                    reportUndocumented.set(reportUndocumentedFiles)
                    skipEmptyPackages.set(false)
                    platform.set(Platform.jvm)
                    jdkVersion.set(javaVersion.asInt())
                    noStdlibLink.set(false)
                    noJdkLink.set(false)
                    noAndroidSdkLink.set(false)
                }
                runAfterAssembleTask()
            }
            configure<DetektExtension> {
                reportsDir = rootDir.resolve(codeReportsDir)
                ignoreFailures = ignoreCodeFailures
                @Suppress("DEPRECATION")
                config = files(project.rootDir.resolve(detectConfigFile))
            }
            configure<KotlinterExtension> {
                ignoreFailures = ignoreCodeFailures
                reporters = arrayOf("checkstyle", "plain")
            }
        }
        dependencyLocking {
            lockMode.set(LockMode.STRICT)
        }
        dependencies {
            // core
            implementation(libs.androidx.ktx)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.kotlinx.coroutines.core)
            // reflect / todo : remove
            implementation(libs.kotlin.reflect)
            // window manager
            implementation(libs.androidx.window)
            // fix duplicates
            implementation(libs.androidx.work.runtime.ktx)
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
            kapt(libs.dagger.compiler)
            kapt(libs.dagger.android.processor)
            kapt(libs.dagger.hilt.compiler)
            // moshi
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
    }
}
