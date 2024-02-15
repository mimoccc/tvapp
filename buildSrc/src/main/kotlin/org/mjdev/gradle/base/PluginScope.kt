/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.base

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.api.BaseVariantImpl
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.invocation.Gradle
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.plugins.PluginManager
import org.gradle.internal.logging.text.StyledTextOutput
import org.gradle.internal.logging.text.StyledTextOutputFactory
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.support.serviceOf
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.versionCode
import org.mjdev.gradle.extensions.versionName
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.util.Properties

@Suppress("MemberVisibilityCanBePrivate", "unused")
class PluginScope<E : PluginExtension, F : TestedExtension>(
    val plugin: PluginBase<E, F>,
    val project: Project = plugin.project,
) {
    val taskScope: TaskScope<E, F> = TaskScope(project, this)

    val testBlocks = mutableListOf<PluginScope<E, F>.() -> Unit>()
    val androidBlocks = mutableListOf<F.() -> Unit>()
    val pluginsBlocks = mutableListOf<PluginManager.() -> Unit>()
    val repoBlocks = mutableListOf<RepositoryHandler.() -> Unit>()
    val dependencyBlocks = mutableListOf<DependencyHandler.() -> Unit>()

    @Suppress("UNCHECKED_CAST")
    val android: F
        get() = project.extensions.findByName("android") as F

    val androidComponents: AndroidComponentsExtension<*, *, *>
        get() = project.extensions.getByType(AndroidComponentsExtension::class.java)

    val projectDir: File
        get() = project.projectDir

    val rootProject: Project
        get() = project.rootProject

    val rootProjectDir: File
        get() = project.rootDir

    val extra
        get() = project.extra

    val versionName
        get() = project.versionName

    val versionCode
        get() = project.versionCode

    val params
        get() = plugin.params

    val gradle: Gradle
        get() = project.gradle

    init {
        plugin.init(this@PluginScope)
        runPlugins()
        runRepositories()
        runDependencies()
        afterEvaluate {
            runAndroids()
            runTests()
        }
    }

    private fun runPlugins() {
        project.pluginManager.also { pm ->
            pluginsBlocks.forEach { it.invoke(pm) }
        }
    }

    private fun runRepositories() {
        project.repositories.also { repos ->
            repoBlocks.forEach { it.invoke(repos) }
        }
    }

    private fun runDependencies() {
        project.dependencies.also { repos ->
            dependencyBlocks.forEach { it.invoke(repos) }
        }
    }

    fun plugins(block: PluginManager.() -> Unit) {
        pluginsBlocks.add(block)
    }

    fun repositories(block: RepositoryHandler.() -> Unit) {
        repoBlocks.add(block)
    }

    fun dependencies(block: DependencyHandler.() -> Unit) {
        dependencyBlocks.add(block)
    }

    fun tasks(
        block: TaskScope<E, F>.() -> Unit
    ) = project.afterEvaluate { block.invoke(taskScope) }

    @Suppress("UnstableApiUsage")
    fun finalizeDsl(block: CommonExtension<*, *, *, *, *>.() -> Unit) {
        androidComponents.finalizeDsl { android ->
            block(android)
        }
    }

    fun variants(block:Variant.() -> Unit) {
        androidComponents.onVariants {
            block(it)
        }
    }

    inline fun <reified T> createExtension(name: String) =
        project.extension(name, T::class.java, true)

    fun configuration(
        name: String
    ): Configuration? = with(project.configurations) {
        findByName(name) ?: create(name)
    }

    fun rootFileExists(path: String): Boolean =
        rootProject.file(path).exists()

    fun file(path: Any): File =
        project.file(path)

    fun rootFile(path: String): File =
        project.rootProject.file(path)

    fun files(vararg paths: Any): ConfigurableFileCollection =
        project.files(paths)

    fun rootFiles(vararg paths: Any): ConfigurableFileCollection =
        project.rootProject.files(paths)

    fun allprojects(action: Action<in Project>) =
        project.allprojects(action)

    @Suppress("HasPlatformType")
    fun println(
        message: String
    ) = gradle.serviceOf<StyledTextOutputFactory>()
        .create("out")
        .style(StyledTextOutput.Style.Failure)
        .println("> $message")

    @Suppress("HasPlatformType")
    fun printErr(
        error: Throwable,
        includeStackTrace: Boolean = true
    ) = gradle.serviceOf<StyledTextOutputFactory>()
        .create("out")
        .style(StyledTextOutput.Style.Failure).apply {
            println("> ${plugin::class.simpleName} ${error::class.simpleName} : ${error.message}")
            if (includeStackTrace) {
                val writer = StringWriter()
                val printWriter = PrintWriter(writer)
                error.printStackTrace(printWriter)
                printWriter.flush()
                val stackTrace = writer.toString()
                stackTrace.split("\n").forEach { line ->
                    println("> ${plugin::class.simpleName} ${error::class.simpleName} : $line")
                }
            }
        }

    fun afterEvaluate(
        block: PluginScope<E, F>.() -> Unit
    ) {
        project.afterEvaluate {
            block(this@PluginScope)
        }
    }

    fun findPlugin(
        name: String
    ): AppliedPlugin? = try {
        project.pluginManager.findPlugin(name)
    } catch (e: Exception) {
        null
    }

    fun applyPlugin(
        name: String
    ) = runCatching {
        val pluginName = findPlugin(name)?.name
        println("Applying plugin: ${pluginName ?: name}")
        project.pluginManager.apply(name)
    }

    fun <T : Plugin<*>> applyPlugin(
        cls: Class<T>
    ) = runCatching {
        val name = cls.simpleName.replace("_Decorated", "")
        println("Applying plugin: $name")
        project.pluginManager.apply(cls)
    }

    @Suppress("UNNECESSARY_SAFE_CALL")
    inline fun <reified T> configureExtension(crossinline block: T.() -> Unit) {
        val name = T::class.simpleName?.replace("_Decorated", "")
        runCatching {
            println("Configuring extension: $name")
            project.extensions.getByType(T::class.java)?.apply {
                block(this)
            } ?: throw (GradleException("Extension $name not found"))
        }
    }

    fun test(block: PluginScope<E, F>.() -> Unit) {
        testBlocks.add(block)
    }

    fun android(block: F.() -> Unit) {
        androidBlocks.add(block)
    }

    fun loadPropertiesFile(path: String) = runCatching {
        val file = rootProject.file(path)
        val props = Properties()
        props.load(FileInputStream(file))
        props.forEach { prop ->
            rootProject.extra.set(prop.key.toString(), prop.value.toString())
        }
    }

    fun loadKeyStoreProperties(
        path: String,
        block: (keyAlias: String, keyPassword: String, storeFile: File, storePassword: String) -> Unit
    ) {
        val keystorePropertiesFile = rootProject.file(path)
        val keystoreProperties = Properties()
        if (keystorePropertiesFile.exists()) {
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))
        } else {
            keystoreProperties["keyAlias"] = System.getenv("KEYSTORE_KEY_ALIAS") ?: ""
            keystoreProperties["keyPassword"] = System.getenv("KEYSTORE_KEY_PASSWORD") ?: ""
            keystoreProperties["storePassword"] = System.getenv("KEYSTORE_STORE_PASSWORD") ?: ""
            keystoreProperties["storeFile"] = System.getenv("KEYSTORE_FILE") ?: ""
        }
        block.invoke(
            keystoreProperties["keyAlias"] as String? ?: "",
            keystoreProperties["keyPassword"] as? String? ?: "",
            File(projectDir, keystoreProperties["storeFile"] as? String? ?: ""),
            keystoreProperties["storePassword"] as? String? ?: ""
        )
    }

    fun params(
        block: E.() -> Unit
    ) = runCatching {
        block.invoke(params)
    }

    inline fun <reified T, R> withNonNull(
        receiver: T?,
        crossinline block: T.() -> R
    ) = runCatching {
        receiver?.let {
            with(it, block)
        } ?: throw (GradleException("${T::class.simpleName} is null"))
    }

    fun runTests() {
        testBlocks.forEach { testBlock ->
            runCatching { testBlock(this) }
        }
    }

    fun runAndroids() {
        androidBlocks.forEach { androidBlock ->
            runCatching {
                android.let {
                    try {
                        androidBlock(it)
                    } catch (e: Exception) {
                        printErr(e)
                    }
                } ?: throw (GradleException("No android extension found"))
            }
        }
    }

    companion object {
        const val SIGNING_CONFIG_NAME = "Any"
    }

}