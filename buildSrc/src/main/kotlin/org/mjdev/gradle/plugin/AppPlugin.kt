/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import com.android.build.api.dsl.ApplicationExtension
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
import org.mjdev.gradle.extensions.addSyncProviderAuthString
import org.mjdev.gradle.extensions.asInt
import org.mjdev.gradle.extensions.registerTask
import org.mjdev.gradle.extensions.runAfterCleanTask
import org.mjdev.gradle.extensions.runAfterAssembleTask
import org.mjdev.gradle.extensions.buildConfigString
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.libs
import org.mjdev.gradle.extensions.string
import org.mjdev.gradle.extensions.int
import org.mjdev.gradle.extensions.manifestPlaceholders
import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.debugImplementation
import org.mjdev.gradle.extensions.kapt
import org.mjdev.gradle.extensions.testImplementation
import org.mjdev.gradle.extensions.androidTestImplementation
import org.mjdev.gradle.extensions.ksp
import org.mjdev.gradle.extensions.setSigningConfigs
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.detektTask
import org.mjdev.gradle.extensions.dokkaTask
import org.mjdev.gradle.extensions.apply
import org.mjdev.gradle.plugin.config.AppConfig
import org.mjdev.gradle.tasks.ReleaseNotesCleanTask
import org.mjdev.gradle.tasks.ReleaseNotesCreateTask
import org.mjdev.gradle.tasks.ZipReleaseClearTask
import org.mjdev.gradle.tasks.ZipReleaseCreateTask

@Suppress("UnstableApiUsage")
class AppPlugin : BasePlugin() {
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
    private val configFieldName = "appConfig"

    override fun Project.work() {
        extension<AppConfig>(configFieldName)
        apply(plugin = "version-catalog")
        apply(plugin = "kotlin-android")
        apply(plugin = "com.android.application")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "kotlin-parcelize")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "com.google.dagger.hilt.android")
        apply(plugin = "dagger.hilt.android.plugin")
        apply(plugin = "io.objectbox")
        apply("org.jetbrains.dokka")
        apply(DetektPlugin::class)
        apply(KotlinterPlugin::class)
        registerTask<ReleaseNotesCleanTask> {
            runAfterCleanTask()
        }
        registerTask<ZipReleaseClearTask> {
            runAfterCleanTask()
        }
        configure<ApplicationExtension> {
            namespace = libs.versions.app.namespace.string
            compileSdk = libs.versions.compileSdk.int
            setSigningConfigs(project) {
                debugKeyFile = libs.versions.debug.keyfile.string
                releaseKeyFile = libs.versions.release.keyfile.string
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
            compileOptions {
                sourceCompatibility = projectJavaVersion
                targetCompatibility = projectJavaVersion
            }
            composeOptions {
                kotlinCompilerExtensionVersion = projectKotlinCompilerVersion
            }
            defaultConfig {
                applicationId = libs.versions.app.namespace.string
                minSdk = libs.versions.minSdk.int
                targetSdk = libs.versions.compileSdk.int
                versionCode = project.versionCode
                versionName = project.versionName
                multiDexEnabled = true
                buildConfigString(
                    "IPTV_API_URL" to "https://iptv-org.github.io/api/",
                    "GITHUB_USER" to "mimoccc",
                    "GITHUB_REPOSITORY" to "tvapp"
                )
                manifestPlaceholders(
                    "auth0Domain" to "@string/com_auth0_domain",
                    "auth0Scheme" to "demo"
                )
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            packaging {
                resources {
                    excludes.addAll(projectExcludes)
                }
            }
            buildTypes {
                debug {
                    applicationIdSuffix = ".$name"
                    isDebuggable = true
                    isJniDebuggable = true
                    isMinifyEnabled = false
                    isShrinkResources = false
                    isCrunchPngs = false
//                  isEmbedMicroApp = true
//                  isPseudoLocalesEnabled = true
//                  enableUnitTestCoverage = true
//                  enableAndroidTestCoverage = true
//                  isRenderscriptDebuggable = true
//                  isZipAlignEnabled = false
                    signingConfig = signingConfigs.getAt(name)
                    stringRes("app_name", "TVApp-Debug")
                    addSyncProviderAuthString("sync_auth", ".sync")
                    proguardFiles(
                        getDefaultProguardFile(projectProguardFile),
                        projectProguardRulesFile
                    )
                }
                release {
                    applicationIdSuffix = ".$name"
                    isDebuggable = false
                    isJniDebuggable = false
                    isMinifyEnabled = true
                    isShrinkResources = true
                    isCrunchPngs = true
//                  isEmbedMicroApp = true
//                  isPseudoLocalesEnabled = false
//                  enableUnitTestCoverage = false
//                  enableAndroidTestCoverage = false
//                  isRenderscriptDebuggable = false
//                  isZipAlignEnabled = true
                    signingConfig = signingConfigs.getAt(name)
                    stringRes("app_name", "TVApp")
                    addSyncProviderAuthString("sync_auth", ".sync")
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
                checkReleaseBuilds = false
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
        // app
        afterEvaluate {
            val appConfig = project.extension<AppConfig>()
            kotlinCompileOptions {
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = projectJavaVersion.toString()
                }
            }
            detektTask {
                if (appConfig.autoCorrectCode) runAfterAssembleTask()
            }
            dokkaTask {
                outputDirectory.set(project.rootDir.resolve(appConfig.documentationDir))
                moduleName.set(project.name)
                suppressObviousFunctions.set(false)
                dokkaSourceSets.configureEach {
                    offlineMode.set(false)
                    includeNonPublic.set(false)
                    skipDeprecated.set(false)
                    reportUndocumented.set(false)
                    skipEmptyPackages.set(false)
                    platform.set(Platform.jvm)
                    jdkVersion.set(projectJavaVersion.asInt())
                    noStdlibLink.set(false)
                    noJdkLink.set(false)
                    noAndroidSdkLink.set(false)
                }
                if (appConfig.createDocumentation) runAfterAssembleTask()
            }
            val rnTask = registerTask<ReleaseNotesCreateTask> {
                if (appConfig.createReleaseNotes) runAfterAssembleTask()
            }
            registerTask<ZipReleaseCreateTask> {
                mustRunAfter(rnTask)
                if (appConfig.createZipRelease) runAfterAssembleTask()
            }
            configure<DetektExtension> {
                @Suppress("DEPRECATION")
                config = files(project.rootDir.resolve(appConfig.detectConfigFile))
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
//            applicationVariants.all {
//                outputs.map {
//                    it as BaseVariantOutputImpl
//                }.forEach { output ->
//                    val outputFileName = "$applicationId-$versionName.apk"
//                    output.outputFileName = outputFileName
//                }
//            }
        }
        dependencyLocking {
//            lockMode.set(LockMode.STRICT)
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
    }
}
