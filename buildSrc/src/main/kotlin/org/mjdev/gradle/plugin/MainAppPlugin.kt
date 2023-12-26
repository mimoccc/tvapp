/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.gradle.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.extra
import org.mjdev.gradle.extensions.containsAndroidPlugin
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import org.mjdev.gradle.extensions.dokkaGfm
import org.mjdev.gradle.extensions.versionName
import org.mjdev.gradle.extensions.registerTask

const val CONFIG_VERSION_PROPERTIES_FILE = "config/version.properties"
const val CONFIG_KEYSTORE_PROPERTIES_FILE = "config/keystore.properties"

const val SIGNING_CONFIG_NAME = "Any"

const val projectCompileSdk: Int = 34
const val projectMinSdk = 21

const val kotlinCompilerExtVersion = "1.5.1"

val javaVersion: JavaVersion = JavaVersion.VERSION_17

const val TASK_PREPARE_RELEASE_NOTES = "prepareReleaseNotes"

const val TASK_ASSEMBLE_RELEASE = "assembleRelease"
const val TASK_ASSEMBLE_DEBUG = "assembleDebug"
const val TASK_ASSEMBLE_MINIFIED = "assembleMinified"
const val TASK_ASSEMBLE_MOCK = "assembleMock"

val ASSEMBLE_TASKS = listOf(
    TASK_ASSEMBLE_DEBUG,
    TASK_ASSEMBLE_MOCK,
    TASK_ASSEMBLE_RELEASE,
    TASK_ASSEMBLE_MINIFIED
)

@Suppress("unused")
interface MainAppPluginExtension {
    val versionPropertiesFile: Property<String?>
    val signingPropertiesFile: Property<String?>
    val signingConfigName: Property<String?>
    val createDocumentation: Property<Boolean>
    val runDetekt: Property<Boolean>
}

// todo
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class MainAppPlugin : Plugin<Project> {

    @Suppress("UNUSED_VARIABLE")
    override fun apply(project: Project): Unit = with(project) {

        val mainExt = extensions.create("mainAppConfig", MainAppPluginExtension::class.java)

//        configure<DetektExtension> {
//            autoCorrect = true
//            toolVersion = "1.20.0"
//            parallel = false
//            source = files(
//                "src/main/kotlin",
//                "src/main/java"
//            )
//            config = files("${project.rootDir}/buildSrc/detekt.yml")
//        }

        // todo non functional as wanted
//        allprojects {
//            gradle.useLogger(CustomEventLogger())
//        }

        // versions
        val versionPropertiesFile = rootProject.file(CONFIG_VERSION_PROPERTIES_FILE)
        val versionProps = Properties()
        versionProps.load(FileInputStream(versionPropertiesFile))
        versionProps.forEach { prop ->
            rootProject.extra.set(prop.key.toString(), prop.value.toString())
        }

        //task release notes
        registerTask<Exec>(TASK_PREPARE_RELEASE_NOTES) {
            commandLine = mutableListOf("./scripts/git_log.sh")
            workingDir = rootDir
            setArgs(mutableListOf(versionName))
        }

        // documentation
        allprojects {
            afterEvaluate {
                ASSEMBLE_TASKS.forEach { taskName ->
                    val releaseNotesTask = tasks.findByName(TASK_PREPARE_RELEASE_NOTES)
                    val currentTask = tasks.findByName(taskName)
                    releaseNotesTask?.apply {
                        outputs.dir(File(projectDir, "../wiki/documentation"))
                    }
                    if (containsAndroidPlugin) {
                    currentTask?.finalizedBy(dokkaGfm, releaseNotesTask)
                    } else {
                        currentTask?.finalizedBy(dokkaGfm)
                    }
                }
            }
        }

//        android?.finalizeDsl { ext->
//            ext.buildTypes.create("staging").let { buildType ->
//                buildType.initWith(ext.buildTypes.getByName("debug"))
//                buildType.manifestPlaceholders["hostName"] = "example.com"
//                buildType.applicationIdSuffix = ".debugStaging"
//            }
//        }

//        android?.applicationVariants.all {
//            outputs.map {
//                it as BaseVariantOutputImpl
//            }.forEach { output ->
//                val outputFileName = "${applicationId}-${versionName}.apk"
//                output.outputFileName = outputFileName
//            }
//        }

//        // dependencies
//        dependencies.apply {
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