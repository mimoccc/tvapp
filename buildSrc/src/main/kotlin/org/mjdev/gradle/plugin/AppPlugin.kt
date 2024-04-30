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
import org.mjdev.gradle.extensions.applicationId
import org.mjdev.gradle.extensions.ksp
import org.mjdev.gradle.extensions.setSigningConfigs
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.detektTask
import org.mjdev.gradle.extensions.dokkaTask
import org.mjdev.gradle.extensions.apply
import org.mjdev.gradle.extensions.projectName
import org.mjdev.gradle.extensions.loadPropertiesFile
import org.mjdev.gradle.extensions.runConfigured
import org.mjdev.gradle.plugin.config.AppConfig
import org.mjdev.gradle.tasks.ApplicationConfigTask
import org.mjdev.gradle.tasks.ReleaseNotesCleanTask
import org.mjdev.gradle.tasks.ReleaseNotesCreateTask
import org.mjdev.gradle.tasks.WebServiceCreateTask
import org.mjdev.gradle.tasks.ZipReleaseClearTask
import org.mjdev.gradle.tasks.ZipReleaseCreateTask
import org.kordamp.gradle.plugin.markdown.MarkdownPlugin
import org.mjdev.gradle.extensions.kotlinCompileOptions
import org.mjdev.gradle.extensions.markDownToHtmlTask
import org.mjdev.gradle.extensions.runConfigured

class AppPlugin : BasePlugin() {
    private val configFieldName = "appConfig"

    override fun Project.work() {
        val appConfig = extension<AppConfig>(configFieldName)
        loadPropertiesFile(appConfig.versionPropertiesFile)
        println("---------------------------------------------------------------------")
        println("App Configuration")
        println("---------------------------------------------------------------------")
        println("Project version : ${project.versionName}")
        println("Project description : $description")
        println("---------------------------------------------------------------------")
        println("autoCorrectCode : ${appConfig.autoCorrectCode}")
        println("ignoreCodeFailures : ${appConfig.ignoreCodeFailures}")
        println("createDocumentation : ${appConfig.createDocumentation}")
        println("reportUndocumentedFiles : ${appConfig.reportUndocumentedFiles}")
        println("failOnDocumentationWarning : ${appConfig.failOnDocumentationWarning}")
        println("createReleaseNotes : ${appConfig.createReleaseNotes}")
        println("createZipRelease : ${appConfig.createZipRelease}")
        println("createInfoClass : ${appConfig.createInfoClass}")
        println("createWebApp : ${appConfig.createWebApp}")
        println("createWebSiteFromGit : ${appConfig.createWebSiteFromGit}")
        println("renameApkOutputByAppID : ${appConfig.renameApkOutputByAppID}")
        println("launcherIconByBuildType : ${appConfig.launcherIconByBuildType}")
        println("---------------------------------------------------------------------")
        println("codeReportsDir : ${appConfig.codeReportsDir}")
        println("documentationDir : ${appConfig.documentationDir}")
        println("detectConfigFile : ${appConfig.detectConfigFile}")
        println("---------------------------------------------------------------------")
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
        registerTask<ReleaseNotesCleanTask> { runAfterCleanTask() }
        registerTask<ZipReleaseClearTask> { runAfterCleanTask() }
        configure<ApplicationExtension> {
            // todo : move
            namespace = libs.versions.app.namespace.string
            // todo : move
            compileSdk = libs.versions.compileSdk.int
            setSigningConfigs(project) {
                // todo : move
                debugKeyFile = libs.versions.debug.keyfile.string
                // todo : move
                releaseKeyFile = libs.versions.release.keyfile.string
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
            compileOptions {
                // todo : move
                sourceCompatibility = appConfig.javaVersion
                // todo : move
                targetCompatibility = appConfig.javaVersion
            }
            composeOptions {
                // todo : move
                kotlinCompilerExtensionVersion =
                    libs.versions.kotlin.compiler.version.string
            }
            defaultConfig {
                applicationId = libs.versions.app.namespace.string
                minSdk = libs.versions.minSdk.int
                targetSdk = libs.versions.compileSdk.int
                versionCode = project.versionCode
                versionName = project.versionName
                multiDexEnabled = true
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
            packaging {
                resources {
                    excludes.addAll(appConfig.projectExcludes)
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
                    signingConfig = signingConfigs[name]
                    // todo : move
                    stringRes("app_name", "TVApp-Debug")
                    // todo : move
                    addSyncProviderAuthString(
                        "$applicationId$applicationIdSuffix",
                        "sync_auth",
                        "sync"
                    )
                    proguardFiles(
                        getDefaultProguardFile(appConfig.projectProguardFile),
                        appConfig.projectProguardRulesFile
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
                    proguardFiles(
                        getDefaultProguardFile(appConfig.projectProguardFile),
                        appConfig.projectProguardRulesFile
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
                // todo : move
                abortOnError = false
                // todo move
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
        runConfigured<AppConfig> {
            kotlinCompileOptions {
                kotlinOptions {
                    freeCompilerArgs += "-Xjsr305=strict"
                    jvmTarget = javaVersion.toString()
                }
            }
            detektTask {
                if (autoCorrectCode)
                    runAfterAssembleTask()
            }
            dokkaTask {
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
                if (createDocumentation)
                    runAfterAssembleTask()
            }
            markDownToHtmlTask {
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
            if (createWebApp) {
                registerTask<WebServiceCreateTask> {
                    domain = "localhost"
                    servicePort = 2222
                    serviceName = project.name
                    serviceVersion = project.versionName
                    serviceCompany = "mjdev"
                    serviceLicense = ""
                    runAfterCleanTask()
                }
            }
            val rnTask = registerTask<ReleaseNotesCreateTask> {
                if (createReleaseNotes)
                    runAfterAssembleTask()
            }
            registerTask<ZipReleaseCreateTask> {
                if (createReleaseNotes)
                    mustRunAfter(rnTask)
                if (createZipRelease)
                    runAfterAssembleTask()
            }
            configure<DetektExtension> {
                ignoreFailures = ignoreCodeFailures
                reportsDir = rootDir.resolve(codeReportsDir)
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

// todo task
//if (appConfig.renameApkOutputByAppID) {
//    applicationVariants.all {
//        outputs.map {
//            it as BaseVariantOutputImpl
//        }.forEach { output ->
//            val outputFileName = "$applicationId-$versionName.apk"
//            output.outputFileName = outputFileName
//        }
//    }
//}