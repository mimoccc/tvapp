/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.InternalKotlinGradlePluginApi
import org.mjdev.gradle.base.PluginBase
import org.mjdev.gradle.dependency.anotherDependencies
import org.mjdev.gradle.dependency.baseDependencies
import org.mjdev.gradle.dependency.composeDependencies
import org.mjdev.gradle.dependency.daggerDependencies
import org.mjdev.gradle.dependency.encryptDependencies
import org.mjdev.gradle.dependency.exoPlayerDependencies
import org.mjdev.gradle.dependency.glideDependencies
import org.mjdev.gradle.dependency.mjdevTvLib
import org.mjdev.gradle.dependency.moshiDependencies
import org.mjdev.gradle.dependency.okHttpDependencies
import org.mjdev.gradle.dependency.permissionsDependencies
import org.mjdev.gradle.dependency.retrofitDependencies
import org.mjdev.gradle.dependency.sandwichDependencies
import org.mjdev.gradle.dependency.testDependencies
import org.mjdev.gradle.dependency.timberDependencies
import org.mjdev.gradle.dependency.widgetDependencies
import org.mjdev.gradle.extensions.capitalize
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.extensions.runAfterCleanTask
import org.mjdev.gradle.extensions.runAfterAssembleTask
import org.mjdev.gradle.tasks.ReleaseNotesCreateTask
import org.mjdev.gradle.tasks.ReleaseNotesCleanTask
import org.mjdev.gradle.tasks.DependencyUpdateTask
import org.mjdev.gradle.tasks.ZipReleaseClearTask
import org.mjdev.gradle.tasks.DependencyUpdateCleanTask
import java.util.Date
import  org.mjdev.gradle.extensions.runAfter
import org.mjdev.gradle.extensions.runAfterPreBuildTask
import org.mjdev.gradle.icons.LauncherIconTask
import org.mjdev.gradle.tasks.DetektCleanTask
import org.mjdev.gradle.tasks.DetektCreateTask
import org.mjdev.gradle.tasks.DokkaCleanTask
import org.mjdev.gradle.tasks.DokkaCreateTask
import org.mjdev.gradle.tasks.InAppInfoCreateTask
import org.mjdev.gradle.tasks.ZipReleaseCreateTask
import org.mjdev.gradle.unittest.TestCaseGeneratorTask
import java.util.Locale

@Suppress("unused", "MemberVisibilityCanBePrivate")
class AppPlugin : PluginBase<AppPluginParams, BaseAppModuleExtension>(AppPluginParams::class.java, {
    plugins {
//        apply("org.jetbrains.kotlin.android")
//        apply("com.android.application")
        apply("kotlin-parcelize")
        apply("com.google.dagger.hilt.android")
        apply("dagger.hilt.android.plugin")
        apply("com.google.devtools.ksp")
        apply("io.objectbox")
        apply("org.jetbrains.dokka")
        //id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
        //id ("app.cash.paparazzi") version "1.3.1"
    }
//    repositories {
//        mavenLocal()
//        mavenCentral()
//        @Suppress("DEPRECATION")
//        jcenter()
//    }
    tasks {
        register<TestCaseGeneratorTask>()
        register<DependencyUpdateCleanTask> {
            runAfterCleanTask()
        }
        if (params.createInfoClass) {
            register<InAppInfoCreateTask> {
                task("compile%sKotlin", variant).dependsOn(this)
            }
        }
        if (params.buildTypeInLauncherIcon) {
            register<LauncherIconTask> {
                runAfterPreBuildTask()
            }
        }
        if (params.updateDependencies) {
            register<DependencyUpdateTask> {
                runAfterAssembleTask()
            }
        }
        register<ReleaseNotesCleanTask> {
            runAfterCleanTask()
        }
        if (params.createReleaseNotes) {
            register<ReleaseNotesCreateTask> {
                runAfterAssembleTask()
            }
        }
        register<ZipReleaseClearTask> {
            runAfterCleanTask()
        }
        if (params.createReleaseZip) {
            register<ZipReleaseCreateTask> {
                if (params.createReleaseNotes) {
                    runAfter<ReleaseNotesCreateTask>()
                } else if (params.createDocumentation) {
                    // todo
//                    runAfter<DokkaCreateTask>()
                } else {
                    runAfterAssembleTask()
                }
            }
        }
        register<DokkaCleanTask> {
            runAfterCleanTask()
        }
        if (params.createDocumentation) {
            register<DokkaCreateTask> {
                runAfterAssembleTask()
            }
        }
        if (params.autoCorrectCode) {
            register<DetektCleanTask> {
                runAfterCleanTask()
            }
            register<DetektCreateTask> {
                runAfterAssembleTask()
            }
        }
    }
    dependencies {
//    ktlint(includeVersion = false)
//    detekt(includeVersion = false)
        mjdevTvLib()
        baseDependencies()
        composeDependencies()
        daggerDependencies()
        moshiDependencies()
        okHttpDependencies()
        timberDependencies()
        retrofitDependencies()
        sandwichDependencies()
        glideDependencies()
        exoPlayerDependencies()
        encryptDependencies()
        permissionsDependencies()
        anotherDependencies()
        widgetDependencies()
        testDependencies()
    }
    test {
        println("--------------------------------------------------------------------------------")
        println(project.name.capitalize())
        println("--------------------------------------------------------------------------------")
        println("Version properties file = ${rootFile(params.versionPropertiesFile)}")
        println("Signing properties file = ${rootFile(params.signingPropertiesFile)}")
        println("--------------------------------------------------------------------------------")
        println("Using signing config: ${params.signingConfigName}")
        android.signingConfigs.forEach { config ->
            println("Signing config key alias: ${config.name}")
            println("Signing config key alias: ${config.keyAlias}")
            println("Signing config store: ${config.storeFile}")
        }
        println("--------------------------------------------------------------------------------")
        println("Version name: ${project.versionName}")
        println("Version code: ${project.versionCode}")
        println("--------------------------------------------------------------------------------")
        println("Settings:")
        println("--------------------------------------------------------------------------------")
        println("Namespace = ${params.packageName}")
        println("Application id = ${params.applicationId}")
        println("Min sdk = ${params.minSdk}")
        println("Compile sdk = ${params.compileSdk}")
        println("MultiDex = ${params.multiDexEnabled}")
        println("Create launcher icon by variant = ${params.buildTypeInLauncherIcon}")
        println("Rename apk file by application id release zip archive = ${params.renameApkOutputByAppID}")
        println("Update dependencies = ${params.updateDependencies}")
        println("Autocorrect code = ${params.autoCorrectCode}")
        println("Create info class = ${params.createInfoClass}")
        println("Create release notes = ${params.createReleaseNotes}")
        println("Create release zip archive = ${params.createReleaseZip}")
        println("--------------------------------------------------------------------------------")
    }
    with(params) {
        loadPropertiesFile(versionPropertiesFile)
        finalizeDsl {
            namespace = params.packageName
            buildFeatures.compose = true
            buildFeatures.buildConfig = true
            buildFeatures.resValues = true
            compileSdk = params.compileSdk
            compileOptions.sourceCompatibility = JavaVersion.VERSION_17
            compileOptions.targetCompatibility = JavaVersion.VERSION_17
            composeOptions.kotlinCompilerExtensionVersion = "1.5.8"
            loadKeyStoreProperties(signingPropertiesFile) { key, pwd, fileStore, pwdStore ->
                signingConfigs {
                    create(signingConfigName) {
                        keyAlias = key
                        keyPassword = pwd
                        storeFile = fileStore
                        storePassword = pwdStore
                    }
                }
            }
            packaging {
                resources.excludes.apply {
                    add("META-INF/")
                    add("/META-INF/{AL2.0,LGPL2.1}")
                    add("/META-INF/DEPENDENCIES")
                    add("/mozilla/public-suffix-list.txt")
                    add("okhttp3/")
                    add("kotlin/")
                    add("org/")
                    add(".properties")
                    add(".bin")
                }
            }
            defaultConfig {
                minSdk = params.minSdk
                compileSdk = params.compileSdk
                multiDexEnabled = params.multiDexEnabled
                applicationId = params.applicationId
                @Suppress("DEPRECATION")
                buildConfigField(
                    "String",
                    "DATE_CREATED",
                    "\"" + Date().toLocaleString() + "\""
                )
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
//            debug {
//                applicationIdSuffix = ".debug"
//                isDebuggable = true
//                isMinifyEnabled = false
//                isShrinkResources = false
//                isCrunchPngs = false
//                isEmbedMicroApp = true
//            }
//            release {
//                applicationIdSuffix = ""
//                isDebuggable = false
//                isMinifyEnabled = true
//                isShrinkResources = true
//                isCrunchPngs = true
//                isEmbedMicroApp = true
//            }
            lint {
                checkReleaseBuilds = false
                abortOnError = false
                checkAllWarnings = true
                warningsAsErrors = false
                showAll = true
                explainIssues = true
                textReport = true
                xmlReport = false
                htmlReport = false
                textOutput = rootProject.file("documentation").file("lint.txt")
                checkTestSources = false
                ignoreTestSources = true
                checkGeneratedSources = false
                checkDependencies = true
            }
        }
        android {
            // todo move
//            defaultConfig {
            // todo
//                    versionCode = project.versionCode
//                    versionName = project.versionName
//                    signingConfig = signingConfigs[signingConfigName]
//                multiDexEnabled = params.multiDexEnabled
//            }
            if (renameApkOutputByAppID) {
                applicationVariants.all {
                    outputs.map { output ->
                        output as BaseVariantOutputImpl
                    }.forEach { output ->
                        output.outputFileName = "$applicationId-$versionName.apk"
                    }
                }
            }
        }
    }
})
