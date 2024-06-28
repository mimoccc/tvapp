/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import com.google.devtools.ksp.impl.KotlinSymbolProcessing
import com.google.devtools.ksp.processing.KSPJvmConfig
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import org.gradle.api.Project
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.buildDirectory
import org.mjdev.gradle.extensions.generatedKspDir
import org.mjdev.gradle.extensions.generatedKspCachesDir
import org.mjdev.gradle.extensions.generatedResDir
import org.gradle.api.JavaVersion
import org.mjdev.gradle.extensions.println
import org.mjdev.gradle.extensions.runBeforeAssemble
import org.mjdev.gradle.ksp.log.LoggerKsp
import org.mjdev.gradle.ksp.processor.screenshots.ScreenshotProcessorProvider

@Suppress("unused")
open class TestClassesGeneratorTask : BaseTask() {
//    private val GH_API_KEY = "0f2d28811bd14776b5c6fd4a84798d5b.96b1d61fc593b1ba"
//    private val GH_API_URL = "https://api-beta.copilot.com"

    //curl \
    //  -X POST \
    //  -H "Content-Type: application/json" \
    //  -H "X-API-KEY: <Replace this with your API Key>" \
    //  --data '<Replace this with a json request body as needed>' \
    //  https://api-beta.copilot.com/v1/<Replace this with a resource>

    private val generatedDir
        get() = project.buildDirectory.resolve("generated")

    //    private val worker = GithubClassWorker()
    private val screenshotKSPProvider by lazy { ScreenshotProcessorProvider() }

    init {
        group = "mjdev"
        description = "This task create unit tests."
        outputs.upToDateWhen { false }
    }

//    private fun createTestFile(
//        project: Project,
//        file: File
//    ) {
//        makeTestFile(
//            file,
//            File(project.unitTestDir, file.asTestFile.name)
//        )
//    }

//    @Suppress("SpellCheckingInspection")
//    private fun makeTestFile(
//        srcFile: File,
//        destFile: File
//    ) {
//        worker.makeTestClass(srcFile).also { createdClass ->
//            createdClass?.writeTo(destFile)
//        }
//    }

//    interface ClassWorker {
//        fun makeTestClass(file: File): FileSpec?
//    }

    // todo

//    class GithubClassWorker : ClassWorker {
//        override fun makeTestClass(file: File): FileSpec? = null
//    }

    override fun onClean() {
//        val pkg = project.packageName
//        val annotation = CreateScreenShot::class.java.canonicalName
//        ClassGraph()
//            .verbose()
//            .enableAllInfo()
//            .enableClassInfo()
//            .enableFieldInfo()
//            .enableMethodInfo()
//            .enableAnnotationInfo()
//            .ignoreClassVisibility()
//            .ignoreFieldVisibility()
//            .ignoreMethodVisibility()
//            .acceptPackages(pkg)
//            .scan().use { scanResult ->
//                scanResult.getClassesWithAnnotation(annotation).forEach { classInfo ->
//                    val annotationInfo = classInfo.getAnnotationInfo(annotation)
//                    val paramValues: List<AnnotationParameterValue> = annotationInfo.parameterValues
//                    val ann = paramValues[0].value as String
//                    println("${classInfo.name} is annotated with $ann")
//                }
//            }
//        processKsp(project, screenshotKSPProvider)
    }

    override fun onAssemble() {
        processKsp(project, screenshotKSPProvider)
    }

    private fun processKsp(
        project: Project,
        processorProvider: SymbolProcessorProvider
    ) {
        println("processing symbols for : ${project.name}")
        KotlinSymbolProcessing(
            KSPJvmConfig.Builder().apply {
                moduleName = project.projectDir.name
                projectBaseDir = project.projectDir
                outputBaseDir = project.projectDir
                sourceRoots = listOf(project.projectDir)
                javaOutputDir = project.generatedKspDir
                kotlinOutputDir = project.generatedKspDir
                cachesDir = project.generatedKspCachesDir
                classOutputDir = project.generatedKspDir
                resourceOutputDir = project.generatedResDir
                jvmTarget = JavaVersion.VERSION_17.toString()
                incremental = false
                incrementalLog = false
                allWarningsAsErrors = true
                mapAnnotationArgumentsInJava = false
                languageVersion = "2.0.0"
                apiVersion = "1.0.22"
                modifiedSources = emptyList()
                removedSources = emptyList()
                changedClasses = emptyList()
                processorOptions = emptyMap()
                commonSourceRoots = emptyList()
            }.build(),
            listOf(processorProvider),
            LoggerKsp(logger)
        ).execute().let { code ->
            println("Kotlin processor ends with exit code: $code")
        }
    }

}
