/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
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
import org.mjdev.gradle.extensions.addSyncProviderAuthString
import org.mjdev.gradle.extensions.asInt
import org.mjdev.gradle.extensions.registerTask
import org.mjdev.gradle.extensions.runAfterAssembleTask
import org.mjdev.gradle.extensions.buildConfigString
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.libs
import org.mjdev.gradle.extensions.manifestPlaceholders
import org.mjdev.gradle.extensions.implementation
import org.mjdev.gradle.extensions.debugImplementation
import org.mjdev.gradle.extensions.kapt
import org.mjdev.gradle.extensions.testImplementation
import org.mjdev.gradle.extensions.androidTestImplementation
import org.mjdev.gradle.extensions.applicationId
import org.mjdev.gradle.extensions.ksp
import org.mjdev.gradle.extensions.setSigningConfigs
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.detektTask
import org.mjdev.gradle.extensions.dokkaTask
import org.mjdev.gradle.extensions.releaseNotesCreateTask
import org.mjdev.gradle.extensions.apply
import org.mjdev.gradle.extensions.projectName
import org.mjdev.gradle.extensions.webServiceCreateTask
import org.mjdev.gradle.extensions.loadRootPropertiesFile
import org.mjdev.gradle.extensions.fromBuildPropertiesFile
import org.mjdev.gradle.plugin.config.AppConfig
import org.mjdev.gradle.tasks.ReleaseNotesCreateTask
import org.mjdev.gradle.tasks.WebServiceCreateTask
import org.mjdev.gradle.tasks.CreatePropsTask
import org.mjdev.gradle.tasks.CheckNewLibsTask
import org.mjdev.gradle.tasks.CleanProjectTask
import org.kordamp.gradle.plugin.markdown.MarkdownPlugin
import org.mjdev.gradle.extensions.assembleTasks
import org.mjdev.gradle.extensions.cleanProjectTask
import org.mjdev.gradle.extensions.markDownToHtmlTask
import org.mjdev.gradle.extensions.variants
import org.mjdev.gradle.extensions.zipReleaseCreateTask
import org.mjdev.gradle.tasks.ZipReleaseCreateTask

@Suppress("UnstableApiUsage")
class AppPlugin : BasePlugin() {
    override fun Project.work() {
        val appConfig: AppConfig = extension<AppConfig>(AppConfig.configFieldName)
        fromBuildPropertiesFile(appConfig, AppConfig.configPropertiesFile)
        loadRootPropertiesFile(appConfig.versionPropertiesFile)
        apply(plugin = "com.android.application")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "kotlin-android")
        apply(plugin = "kotlin-parcelize")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "com.google.dagger.hilt.android")
        apply(plugin = "dagger.hilt.android.plugin")
        apply(plugin = "io.objectbox")
        apply(plugin = "org.jetbrains.dokka")
        apply(MarkdownPlugin::class)
        apply(DetektPlugin::class)
        apply(KotlinterPlugin::class)
        registerTask<CleanProjectTask>()
        registerTask<CheckNewLibsTask>() {
            mustRunAfter(cleanProjectTask())
        }
        registerTask<CreatePropsTask> {
            propsFilePath = AppConfig.configPropertiesFile
            propsClass = AppConfig::class.java
            mustRunAfter(cleanProjectTask())
        }
        registerTask<ReleaseNotesCreateTask>()
        registerTask<WebServiceCreateTask>()
        registerTask<ZipReleaseCreateTask>()
        configure<ApplicationExtension> {
            namespace = appConfig.namespace
            compileSdk = AppConfig.compileSdk
            setSigningConfigs(project) {
                debugKeyFile = appConfig.debugKeySigningFile
                releaseKeyFile = appConfig.releaseSigningFile
            }
            buildFeatures {
                compose = AppConfig.composeEnabled
                buildConfig = AppConfig.buildConfigEnabled
            }
            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }
            composeOptions {
                kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerVersion
            }
            defaultConfig {
                applicationId = appConfig.namespace
                minSdk = AppConfig.minSdk
                targetSdk = AppConfig.compileSdk
                versionCode = project.versionCode
                versionName = project.versionName
                multiDexEnabled = AppConfig.multiDexEnabled
                // todo : move
                buildConfigString(
                    "IPTV_API_URL" to "https://iptv-org.github.io/api/",
                    "GITHUB_USER" to "mimoccc",
                    "GITHUB_REPOSITORY" to "tvapp"
                )
                // todo : move
                manifestPlaceholders(
                    "auth0Domain" to "@string/com_auth0_domain",
                    "auth0Scheme" to "demo"
                )
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            packaging.resources.excludes.addAll(AppConfig.projectExcludes)
            buildTypes {
                debug {
                    applicationIdSuffix = ".$name"
                    isDebuggable = true
                    isJniDebuggable = true
                    isMinifyEnabled = false
                    isShrinkResources = false
                    isCrunchPngs = false
                    signingConfig = signingConfigs[name]
                    // todo : move
                    stringRes("app_name", "TVApp-Debug")
                    // todo : move
                    addSyncProviderAuthString(
                        "$applicationId$applicationIdSuffix",
                        "sync_auth",
                        "sync"
                    )
                    // todo : move
                    proguardFiles(
                        getDefaultProguardFile(appConfig.proguardFile),
                        appConfig.proguardRulesFile
                    )
                }
                release {
                    applicationIdSuffix = ""
                    isDebuggable = false
                    isJniDebuggable = false
                    isMinifyEnabled = true
                    isShrinkResources = true
                    isCrunchPngs = true
                    signingConfig = signingConfigs[name]
                    // todo : move
                    stringRes("app_name", "TVApp")
                    // todo : move
                    addSyncProviderAuthString(
                        "$applicationId$applicationIdSuffix",
                        "sync_auth",
                        "sync"
                    )
                    // todo : move
                    proguardFiles(
                        getDefaultProguardFile(appConfig.proguardFile),
                        appConfig.proguardRulesFile
                    )
                }
            }
            sourceSets {
                getByName("main") { jniLibs.srcDirs() }
            }
            lint {
                checkReleaseBuilds = true
                checkAllWarnings = true
                showAll = true
                explainIssues = true
                abortOnError = !appConfig.ignoreCodeFailures
                warningsAsErrors = !appConfig.ignoreCodeFailures
                disable += "UnusedIds"
            }
            testOptions {
                unitTests.isReturnDefaultValues = true
            }
            testCoverage {
                jacocoVersion = AppConfig.jacocoVersion
            }
        }
        afterEvaluate {
            detektTask {
                if (appConfig.autoCorrectCode) {
                    mustRunAfter(assembleTasks)
                    runAfterAssembleTask()
                }
            }
            dokkaTask {
                outputDirectory.set(rootDir.resolve(appConfig.documentationDir))
                moduleName.set(projectName)
                suppressObviousFunctions.set(false)
                dokkaSourceSets.configureEach {
                    offlineMode.set(false)
                    includeNonPublic.set(false)
                    skipDeprecated.set(false)
                    failOnWarning.set(appConfig.failOnDocumentationWarning)
                    reportUndocumented.set(appConfig.reportUndocumentedFiles)
                    skipEmptyPackages.set(false)
                    platform.set(Platform.jvm)
                    jdkVersion.set(AppConfig.javaVersion.asInt())
                    noStdlibLink.set(false)
                    noJdkLink.set(false)
                    noAndroidSdkLink.set(false)
                }
                if (appConfig.createDocumentation) {
                    runAfterAssembleTask()
                }
            }
            markDownToHtmlTask {
//            enabled = appConfig.createWebSiteFromGit
//                sourceDir = rootDir
//                outputDir = rootDir.resolve("web")
//                hardwraps = true
//                autoLinks = true
//                abbreviations = true
//                definitionLists = true
//                smartQuotes = true
//                smartPunctuation = true
//                smart = true
//                fencedCodeBlocks = true
//                tables = true
//                all = true
//                removeHtml = true
//                removeTables = true
//                baseUri = ""
//                customizePegdown = {}
//                customizeRemark = {}
//                if(createWebSiteFromGit) {
//                    runAfterAssembleTask()
//                }
            }
            releaseNotesCreateTask {
                if (appConfig.createReleaseNotes) {
                    runAfterAssembleTask()
                }
            }
            webServiceCreateTask {
                domain = "localhost"
                servicePort = 2222
                serviceName = project.name
                serviceVersion = project.versionName
                serviceCompany = "mjdev"
                serviceLicense = ""
                if (appConfig.createWebApp) {
                    runAfterAssembleTask()
                }
            }
            zipReleaseCreateTask {
                // todo files here
                if (appConfig.createZipRelease) {
                    runAfterAssembleTask()
                }
                if (appConfig.createReleaseNotes) {
                    mustRunAfter(releaseNotesCreateTask())
                }
                if (appConfig.createWebApp) {
                    mustRunAfter(webServiceCreateTask())
                }
                if (appConfig.createDocumentation) {
                    mustRunAfter(dokkaTask())
                }
                if (appConfig.autoCorrectCode) {
                    mustRunAfter(detektTask())
                }
            }
            kotlinCompileOptions {
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = AppConfig.javaVersion.toString()
                }
            }
            configure<DetektExtension> {
                ignoreFailures = appConfig.ignoreCodeFailures
                reportsDir = rootDir.resolve(appConfig.codeReportsDir)
                @Suppress("DEPRECATION")
                config = files(project.rootDir.resolve(appConfig.detectConfigFile))
            }
            configure<KotlinterExtension> {
                ignoreFailures = appConfig.ignoreCodeFailures
                reporters = arrayOf("checkstyle", "plain")
            }
            if (appConfig.renameApkOutputByAppID) {
                this.variants.forEach { variant ->
                    variant.outputs.map {
                        it as BaseVariantOutputImpl
                    }.forEach { output ->
                        val outputFileName = "$applicationId-$versionName.apk"
                        output.outputFileName = outputFileName
                    }
                }
            }
        }
        dependencyLocking {
            lockMode.set(LockMode.STRICT)
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
