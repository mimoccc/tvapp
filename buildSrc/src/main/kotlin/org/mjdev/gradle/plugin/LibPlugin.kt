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
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.dokka.Platform
import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin
import org.mjdev.gradle.base.BasePlugin
import org.mjdev.gradle.extensions.androidTestImplementation
import org.mjdev.gradle.extensions.applyPlugin
import org.mjdev.gradle.extensions.asInt
import org.mjdev.gradle.extensions.debugImplementation
import org.mjdev.gradle.extensions.detektTask
import org.mjdev.gradle.extensions.dokkaTask
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.fromBuildPropertiesFile
import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.ksp
import org.mjdev.gradle.extensions.libs
import org.mjdev.gradle.extensions.loadRootPropertiesFile
import org.mjdev.gradle.extensions.projectName
import org.mjdev.gradle.extensions.registerTask
import org.mjdev.gradle.extensions.runAfterAssembleTask
import org.mjdev.gradle.extensions.runBeforeAssemble
import org.mjdev.gradle.extensions.testImplementation
import org.mjdev.gradle.plugin.config.LibConfig
import org.mjdev.gradle.tasks.CreatePropsTask
import org.mjdev.gradle.tasks.TestClassesGeneratorTask

@Suppress("UnstableApiUsage")
class LibPlugin : BasePlugin() {
    override fun Project.work() {
        val libConfig: LibConfig = extension<LibConfig>(LibConfig.configFieldName)
        fromBuildPropertiesFile(libConfig, LibConfig.configPropertiesFile)
        loadRootPropertiesFile(libConfig.versionPropertiesFile)
        applyPlugin(libs.plugins.android.library)
        applyPlugin(libs.plugins.kotlin.android)
        applyPlugin(libs.plugins.kotlin.parcelize)
        applyPlugin(libs.plugins.google.devtools.ksp)
        applyPlugin(libs.plugins.objectbox)
        applyPlugin(libs.plugins.gradle.dokka)
        applyPlugin(libs.plugins.kotlin.compose.compiler)
        applyPlugin(libs.plugins.gradle.paparazzi.plugin)
        applyPlugin<DetektPlugin>()
        applyPlugin<KotlinterPlugin>()
        registerTask<CreatePropsTask> {
            propsFilePath = LibConfig.configPropertiesFile
            propsClass = LibConfig::class.java
        }
        registerTask<TestClassesGeneratorTask>()
        configure<LibraryExtension> {
            namespace = libConfig.namespace
            compileSdk = LibConfig.compileSdk
            compileOptions {
                sourceCompatibility = LibConfig.javaVersion
                targetCompatibility = LibConfig.javaVersion
            }
            buildFeatures {
                compose = LibConfig.composeEnabled
                buildConfig = LibConfig.buildConfigEnabled
            }
            composeOptions {
                useLiveLiterals = true
            }
            defaultConfig {
                minSdk = LibConfig.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//                buildToolsVersion = LibConfig.buildToolsVersion
                vectorDrawables {
                    useSupportLibrary = false
                    generatedDensities()
                }
            }
            packaging.resources.excludes.addAll(LibConfig.projectExcludes)
            buildTypes {
                debug {
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile(libConfig.proguardFile),
                        libConfig.proguardRulesFile
                    )
                }
                release {
                    isMinifyEnabled = libConfig.minify
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile(libConfig.proguardFile),
                        libConfig.proguardRulesFile
                    )
                }
            }
            sourceSets {
                getByName("main") {
                    jniLibs.srcDirs()
                }
            }
            lint {
                checkReleaseBuilds = true
                checkAllWarnings = true
                showAll = true
                explainIssues = true
                abortOnError = !libConfig.ignoreCodeFailures
                warningsAsErrors = !libConfig.ignoreCodeFailures
                disable += "UnusedIds"
            }
            testOptions {
                unitTests.isReturnDefaultValues = true
            }
            testCoverage {
                jacocoVersion = LibConfig.jacocoVersion
            }
        }
        afterEvaluate {
            detektTask {
                if (libConfig.autoCorrectCode) {
                    runAfterAssembleTask()
                } else {
                    enabled = false
                }
            }
            dokkaTask {
                outputDirectory.set(rootDir.resolve(libConfig.documentationDir))
                moduleName.set(projectName)
                suppressObviousFunctions.set(false)
                dokkaSourceSets.configureEach {
                    offlineMode.set(false)
                    includeNonPublic.set(false)
                    skipDeprecated.set(false)
                    failOnWarning.set(libConfig.failOnDocumentationWarning)
                    reportUndocumented.set(libConfig.reportUndocumentedFiles)
                    skipEmptyPackages.set(false)
                    platform.set(Platform.jvm)
                    jdkVersion.set(LibConfig.javaVersion.asInt())
                    noStdlibLink.set(false)
                    noJdkLink.set(false)
                    noAndroidSdkLink.set(false)
                }
                if (libConfig.createDocumentation) {
                    runAfterAssembleTask()
                } else {
                    enabled = false
                }
            }
            kotlinCompileOptions {
                @Suppress("DEPRECATION")
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = LibConfig.javaVersion.toString()
                }
            }
            configure<DetektExtension> {
                reportsDir = rootDir.resolve(libConfig.codeReportsDir)
                ignoreFailures = libConfig.ignoreCodeFailures
                @Suppress("DEPRECATION")
                config = files(project.rootDir.resolve(libConfig.detectConfigFile))
            }
            configure<KotlinterExtension> {
                ignoreFailures = libConfig.ignoreCodeFailures
                reporters = arrayOf("checkstyle", "plain")
            }
        }
        dependencyLocking {
            lockMode.set(LockMode.STRICT)
        }
        dependencies {
            implementation(files("$rootDir/buildSrc/build/libs/buildSrc.jar"))
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
            // previews
            debugImplementation(libs.androidx.customview.poolingcontainer)
            // foundation
            implementation(libs.androidx.foundation)
            // Kodein DI
            implementation(libs.kodein.di)
            implementation(libs.kodein.di.framework.compose)
            implementation(libs.kodein.di.framework.android.x)
            implementation(libs.kodein.di.framework.android.x.viewmodel)
            implementation(libs.kodein.di.framework.android.x.viewmodel.savedstate)
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
            // palette
            implementation(libs.androidx.palette.ktx)
            // lottie
            implementation(libs.compose.lottie)
            // jsoup
            implementation(libs.jsoup)
            // dm
            implementation(libs.dailymotion.sdk.android)
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
            // own ksp
//            ksp(project(":processor"))
            // oauth
//            implementation(libs.auth0)
//            implementation(libs.android.jwtdecode)
        }
    }
}
