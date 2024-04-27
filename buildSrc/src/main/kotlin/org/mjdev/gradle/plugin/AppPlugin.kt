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
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.mjdev.gradle.extensions.addSyncProviderAuthString
import org.mjdev.gradle.extensions.buildConfigString
import org.mjdev.gradle.extensions.manifestPlaceholders
import org.mjdev.gradle.extensions.setSigningConfigs
import org.mjdev.gradle.extensions.stringRes
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.dokka.Platform
import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin
import org.mjdev.gradle.base.BasePlugin
import org.mjdev.gradle.plugin.config.AppConfig

@Suppress("UnstableApiUsage")
class AppPlugin : BasePlugin() {
    private val projectNamespace = "org.mjdev.tvapp"
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
    private val configFieldName = "appConfig"

    override fun Project.work() {
        loadVersionCatalog(projectLibVersionsFile)
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
        apply(DetektPlugin::class)
        apply(KotlinterPlugin::class)
        apply("org.jetbrains.dokka")
        configure<ApplicationExtension> {
            namespace = projectNamespace
            compileSdk = projectCompileSdk
            setSigningConfigs(project) {
                debugKeyFile = "config/signing.prop"
                releaseKeyFile = "config/signing.prop"
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
                applicationId = projectNamespace
                minSdk = projectMinSdk
                targetSdk = projectCompileSdk
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
//            kotlinOptions {
//                jvmTarget = JavaVersion.VERSION_17.toString()
//            }
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
//            kapt {
//                correctErrorTypes = true
//            }
//            hilt {
//                enableAggregatingTask = true
//            }
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
                if (appConfig.createDocumentation) runAfterAssembleTask()
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
//            add(IMPLEMENTATION, libs.androidx.compose.ui.tooling)
//            add(IMPLEMENTATION, Dependencies.AndroidX.ANNOTATION)
//            add(IMPLEMENTATION, Dependencies.AndroidX.APPCOMPAT)
//            add(IMPLEMENTATION, Dependencies.Kotlin.STD_LIB)
        }
    }
}
