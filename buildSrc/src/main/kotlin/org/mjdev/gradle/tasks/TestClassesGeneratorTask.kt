/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

//import com.google.devtools.ksp.impl.CommandLineKSPLogger
//import com.google.devtools.ksp.impl.KotlinSymbolProcessing
//import com.google.devtools.ksp.processing.KSPJvmConfig
//import com.google.devtools.ksp.processor.AbstractTestProcessor
//import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
//import org.jetbrains.kotlin.cli.jvm.config.javaSourceRoots
//import org.jetbrains.kotlin.cli.jvm.config.jvmClasspathRoots
//import org.jetbrains.kotlin.cli.jvm.config.jvmModularRoots
//import org.jetbrains.kotlin.config.JVMConfigurationKeys
//import org.jetbrains.kotlin.config.languageVersionSettings
//import org.jetbrains.kotlin.test.compileJavaFiles
//import org.jetbrains.kotlin.test.kotlinPathsForDistDirectoryForTests
//import org.jetbrains.kotlin.test.model.FrontendKinds
//import org.jetbrains.kotlin.test.model.TestModule
//import org.jetbrains.kotlin.test.services.JUnit5Assertions
//import org.jetbrains.kotlin.test.services.TestServices
//import org.jetbrains.kotlin.test.services.compilerConfigurationProvider
//import org.jetbrains.kotlin.test.services.isKtFile
//import org.jetbrains.kotlin.test.util.KtTestUtil
//import org.jetbrains.kotlin.utils.PathUtil
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.allFiles
import org.mjdev.gradle.extensions.file

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

    private val subprojects = project.rootProject.subprojects

    private val fileMap = subprojects.associateWith { p ->
        p.file("src").file("main").allFiles("kt")
    }

//    private val worker = GithubClassWorker()

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
//        destfile: File
//    ) {
//        worker.makeTestClass(srcFile).also { createdClass ->
//            createdClass?.writeTo(destfile)
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
    }

    override fun onAssemble() {
//        val kspConfig = KSPJvmConfig.Builder().apply {
//             All configurations happen here.
//        }.build()
//        val exitCode = KotlinSymbolProcessing(kspConfig, listOfProcessors, kspLoggerImpl).execute()
//        println("> Got files:")
//        fileMap.keys.forEach { project ->
//            val files = fileMap[project] ?: emptyList()
//            files.forEach { file ->
//                println("> ${project.name} > $file")
//                createTestFile(project, file)
//            }
//        }
    }

}
