/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.plugin

import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.extra
import org.mjdev.gradle.helper.CustomEventLogger
import java.io.File
import java.io.FileInputStream
import java.util.Properties

// todo
@Suppress("UsePropertyAccessSyntax", "UnusedReceiverParameter")
abstract class MainAppPlugin : Plugin<Project> {

    companion object {

        const val CONFIG_VERSION_PROPERTIES_FILE = "config/version.properties"
        const val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"
        const val SIGNING_CONFIG_NAME = "Any"

        const val TASK_PREPARE_RELEASE_NOTES = "prepareReleaseNotes"
//        const val TASK_DOKKA = "dokkaGfm"

        const val TASK_ASSEMBLE_RELEASE = "assembleRelease"
        const val TASK_ASSEMBLE_DEBUG = "assembleDebug"
        const val TASK_ASSEMBLE_MINIFIED = "assembleMinified"
        const val TASK_ASSEMBLE_MOCK = "assembleMock"

        private const val VERSION_TAG_MAJOR = "majorVersion"
        private const val VERSION_TAG_MINOR = "minorVersion"
        private const val VERSION_TAG_PATCH = "patchVersion"

        val Project.versionCode
            get(): Int {
                val major = rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt() ?: 1
                val minor = rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt() ?: 0
                val patch = rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt() ?: 0
                return major * 10000 + minor * 100 + patch
            }

        val Project.versionName
            get(): String {
                val major = rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt() ?: 1
                val minor = rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt() ?: 0
                val patch = rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt() ?: 0
                return "${major}.${minor}.${patch}"
            }

        val javaVersion: JavaVersion = JavaVersion.VERSION_17

        const val kotlinCompilerExtVersion = "1.5.1"

        inline fun <reified T : Task> Project.registerTask(
            name: String,
            block: Action<T>
        ) = project.tasks.register(name, T::class.java).configure(block)

//        fun DependencyHandler.implementCore(): Array<Dependency?> = arrayOf(
//            // base, kotlin & etc
//            implementation("androidx.core:core-ktx:1.10.1"),
//            implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.7.0")),
//            // reflection just for view models, unfinished compose libs
//            implementation(kotlin("reflect", "1.8.21") as String)
//        )

//        fun DependencyHandler.implementLifeCycle(): Array<Dependency?> = arrayOf(
//            // base, kotlin & etc
//            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"),
//        )

//        fun DependencyHandler.implementCoroutines(): Array<Dependency?> = arrayOf(
//            // coroutines
//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"),
//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1"),
//        )

//        fun DependencyHandler.implementDagger(): Array<Dependency?> = arrayOf(
//            // dagger core
//            implementation("com.google.dagger:dagger:2.46.1"),
//            kapt("com.google.dagger:dagger-compiler:2.46.1"),
//            // dagger android
//            implementation("com.google.dagger:dagger-android:2.46.1"),
//            implementation("com.google.dagger:dagger-android-support:2.46.1"),
//            kapt("com.google.dagger:dagger-android-processor:2.46.1"),
//        )

//        fun DependencyHandler.implementHilt(): Array<Dependency?> = arrayOf(
//            // dagger - hilt
//            implementation("com.google.dagger:hilt-android:2.46.1"),
//            kapt("com.google.dagger:hilt-compiler:2.46.1"),
//        )

//        fun DependencyHandler.implementThemeMaterial3() = arrayOf(
//            // material
//            implementation("com.google.android.material:material:1.9.0"),
//        )

//        fun DependencyHandler.implementCompose() = arrayOf(
//            // compose things
//            implementation(platform("androidx.compose:compose-bom:2023.05.01")),
//            implementation("androidx.activity:activity-compose:1.7.2"),
//            implementation("androidx.compose.ui:ui:1.4.3"),
//            implementation("androidx.compose.ui:ui-graphics:1.4.3"),
//            implementation("androidx.compose.ui:ui-tooling-preview:1.4.3"),
//            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1"),
//            implementation("androidx.paging:paging-compose:1.0.0-alpha20"),
//            implementation("androidx.compose.material3:material3:1.1.0"),
//            implementation("androidx.compose.material3:material3-window-size-class:1.1.0"),
//            implementation("androidx.hilt:hilt-navigation-compose:1.0.0"),
//            implementation("androidx.navigation:navigation-compose:2.5.3"),
//            implementation("androidx.paging:paging-compose:1.0.0-alpha20"),
//            // kotlin conf
//            constraints {
//                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")?.apply {
//                    because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
//                }
//                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")?.apply {
//                    because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
//                }
//            }
//        )

//        fun DependencyHandler.implementGlide() = arrayOf(
//            // glide things
//            implementation("com.github.bumptech.glide:glide:4.15.1"),
//            implementation("com.github.bumptech.glide:gifdecoder:4.15.1"),
//            implementation("com.github.bumptech.glide:annotations:4.15.1"),
//            implementation("com.github.bumptech.glide:okhttp4-integration:4.15.1"),
//            kapt("com.github.bumptech.glide:compiler:4.15.1"),
//        )

//        fun DependencyHandler.implementOkHttp() = arrayOf(
//            // okhttp things
//            implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11"),
//            implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"),
//        )

//        fun DependencyHandler.implementRetrofit() = arrayOf(
//            // retrofit
//            implementation("com.squareup.retrofit2:retrofit:2.9.0"),
//            implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"),
//        )

//        fun DependencyHandler.implementMoshi() = arrayOf(
//            implementation("com.squareup.moshi:moshi:1.15.0"),
//            implementation("com.squareup.retrofit2:converter-moshi:2.9.0"),
//            kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0"),
//        )

//        fun DependencyHandler.implementTimber() = arrayOf(
//            // timber logger
//            implementation("com.jakewharton.timber:timber:5.0.1"),
//        )

//        fun DependencyHandler.implementORMLite() = arrayOf(
//            implementation("com.j256.ormlite.cipher:ormlite-sqlcipher:1.4@aar"),
//            implementation("com.j256.ormlite:ormlite-core:5.1"),
//            implementation("com.j256.ormlite:ormlite-android:5.1"),
//            implementation("net.zetetic:android-database-sqlcipher:4.0.1@aar"),
//        )

//        fun DependencyHandler.implementTestUnit() = arrayOf(
//            testImplementation("junit:junit:4.13.2"),
//            testImplementation("com.google.dagger:hilt-android-testing:2.46.1"),
//            testAnnotationProcessor("com.google.dagger:hilt-compiler:2.46.1"),
//        )

//        fun DependencyHandler.implementTestInstrumentation() = arrayOf(
//            androidTestImplementation("androidx.test.ext:junit:1.1.5"),
//            androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1"),
//            androidTestImplementation(platform("androidx.compose:compose-bom:2023.05.01")),
//            androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3"),
//            androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1"),
//            androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:2.46.1"),
//        )

//        fun DependencyHandler.implementDebug() = arrayOf(
//            debugImplementation("androidx.compose.ui:ui-tooling:1.4.3"),
//            debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3"),
//        )

//        mocking
//        implementation("io.mockk:mockk:1.9")

        fun Project.loadKeyStoreProperties(
            signConfigFilePath: String,
            block: (keyAlias: String, keyPassword: String, storeFile: File, storePassword: String) -> Unit
        ) {
            val keystorePropertiesFile = project.rootProject.file(signConfigFilePath)
            val keystoreProperties = Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            } else {
                keystoreProperties["keyAlias"] = System.getenv("KEYSTORE_KEY_ALIAS").orEmpty()
                keystoreProperties["keyPassword"] = System.getenv("KEYSTORE_KEY_PASSWORD").orEmpty()
                keystoreProperties["storePassword"] =
                    System.getenv("KEYSTORE_STORE_PASSWORD").orEmpty()
                keystoreProperties["storeFile"] = System.getenv("KEYSTORE_FILE").orEmpty()
            }
            block.invoke(
                keystoreProperties["keyAlias"] as String? ?: "",
                keystoreProperties["keyPassword"] as? String? ?: "",
                File(projectDir, keystoreProperties["storeFile"] as? String? ?: ""),
                keystoreProperties["storePassword"] as? String? ?: ""
            )
        }

    }

    override fun apply(project: Project) {

        // todo non functional as wanted
        project.allprojects {
            gradle.useLogger(CustomEventLogger())
        }

        // versions
        val versionPropertiesFile = project.rootProject.file(CONFIG_VERSION_PROPERTIES_FILE)
        val versionProps = Properties()
        versionProps.load(FileInputStream(versionPropertiesFile))
        versionProps.forEach { prop ->
            project.rootProject.extra.set(prop.key.toString(), prop.value.toString())
        }

        //task release notes
        project.registerTask<Exec>(TASK_PREPARE_RELEASE_NOTES) {
            setCommandLine(mutableListOf("./scripts/git_log.sh"))
            setWorkingDir(project.rootDir)
            setArgs(mutableListOf(project.versionName))
        }

        // tasks after build
//        project.afterEvaluate {
//            listOf(
//                TASK_ASSEMBLE_DEBUG,
//                TASK_ASSEMBLE_MOCK,
//                TASK_ASSEMBLE_RELEASE,
//                TASK_ASSEMBLE_MINIFIED
//            ).forEach { taskName ->
//                tasks.findByName(taskName)
//                    ?.finalizedBy(TASK_PREPARE_RELEASE_NOTES, TASK_DOKKA)
//            }
//        }

//        // dependencies
//        project.dependencies.apply {
//            // base libs kotlin & etc
//            implementCore()
//            // lifecycle
//            implementLifeCycle()
//            // coroutines
//            implementCoroutines()
//            // dagger core
//            implementDagger()
//            // dagger - hilt
//            implementHilt()
//            // themes
//            implementThemeMaterial3()
//            // compose
//            implementCompose()
//            // glide
//            implementGlide()
//            // okhttp
//            implementOkHttp()
//            // retrofit
//            implementRetrofit()
//            // moshi
//            implementMoshi()
//            // log
//            implementTimber()
//            // db
//            implementORMLite()
//            // test
//            implementTestUnit()
//            // ui test
//            implementTestInstrumentation()
//            // debug
//            implementDebug()
//        }

    }

}