/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused", "DEPRECATION")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.StartParameter
import org.gradle.api.GradleException
import org.gradle.api.Task
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSet.TEST_SOURCE_SET_NAME
import org.gradle.api.tasks.TaskContainer
import org.mjdev.gradle.base.BaseTask
import java.io.File

val Task.projectName: String
    get() = project.name

val Task.projectVersion: String
    get() = project.versionName

val Task.tasks: TaskContainer
    get() = project.tasks

val Task.assembleTasks
    get() = project.assembleTasks

val Task.cleanTask: Task
    get() = tasks.byName<Task>("clean")

val Task.androidComponents: AndroidComponentsExtension<*, *, *>
    get() = project.extensions.getByType(AndroidComponentsExtension::class.java)

@Suppress("UnstableApiUsage")
fun Task.finalizeDsl(block: CommonExtension<*, *, *, *, *>.() -> Unit) {
    androidComponents.finalizeDsl { android ->
        block(android)
    }
}

//fun Task.registerSourceDirectory(
//    dir: File
//) = finalizeDsl {
//    sourceSets.findByName("main")!!.kotlin.srcDirs(dir)
//}

//fun Task.registerSources(outDir: File, variant: BaseVariant) =
//    project.registerSources(outDir, variant)

//@Suppress("UnstableApiUsage")
fun Task.registerSourceDirectory(outDir: File) {
    android.sourceSets.findByName(MAIN_SOURCE_SET_NAME)?.kotlin?.srcDirs(outDir)
    android.sourceSets.findByName(MAIN_SOURCE_SET_NAME)?.java?.srcDirs(outDir)
//    androidComponents.onVariants { variant ->
//        variant.sources.java?.addStaticSourceDirectory(outDir.absolutePath)
//    }
//    androidComponents.onVariants { variant ->
//        variant.sources.kotlin?.addStaticSourceDirectory(outDir.absolutePath)
//    }
}

fun Task.registerResourceDirectory(outDir: File) {
    android.sourceSets.findByName(MAIN_SOURCE_SET_NAME)?.res?.srcDirs(outDir)
//    androidComponents.onVariants { variant ->
//        variant.sources.res?.addStaticSourceDirectory(outDir.absolutePath)
//    }
}

//@Suppress("UnstableApiUsage")
fun Task.registerTestSourceDirectory(outDir: File) {
    android.sourceSets.findByName(TEST_SOURCE_SET_NAME)?.kotlin?.srcDirs(outDir)
    android.sourceSets.findByName(TEST_SOURCE_SET_NAME)?.java?.srcDirs(outDir)
//    androidComponents.onVariants { variant ->
//        variant.sources.kotlin?.addStaticSourceDirectory(outDir.absolutePath)
//    }
//    androidComponents.onVariants { variant ->
//        variant.sources.java?.addStaticSourceDirectory(outDir.absolutePath)
//    }
}

//fun Task.manifest(block: () -> Unit) {
//    androidComponents.onVariants { variant ->
//        val manifestUpdater = project.tasks.register(
//            variant.name + "ManifestUpdater",
//            DefaultTask::class.java
//        ) {
//            block()
//        }
//        variant.artifacts.use(manifestUpdater).wiredWithFiles(
//            DefaultTask::mergedManifest,
//            DefaultTask::updatedManifest
//        ).toTransform(SingleArtifact.MERGED_MANIFEST)
//    }
//}


fun Task.runAfterCleanTask() {
//    println("> Task order ${cleanTask.name} -> $name")
    cleanTask.finalizedBy(this)
}

fun Task.runBeforeCleanTask() {
    println("> Task order ${cleanTask.name} -> $name")
    cleanTask.dependsOn(this)
}

fun Task.runAfterPreBuildTask() {
    val variantName = (this as? BaseTask)?.variantName ?: ""
    val task = tasks.byName<Task>("pre${variantName}Build")
//    println("> Task order ${task.name} -> $name")
    task.finalizedBy(this)
}

//fun Task.runAfterAssembleTask() {
//    val variantName = (this as? BaseTask)?.variantName ?: ""
//    val task = tasks.byName<Task>("assemble${variantName}")
////    println("> Task order ${task.name} -> $name")
////    task.finalizedBy(this)
//    mustRunAfter(task)
//}

fun Task.runBeforeAssembleTask() {
    val variantName = (this as? BaseTask)?.variantName ?: ""
    val task = tasks.byName<Task>("assemble${variantName}")
//    println("> Task order ${task.name} -> $name")
    task.dependsOn(this)
}

fun Task.findPlugin(
    name: String
): AppliedPlugin? = runCatching {
    project.pluginManager.findPlugin(name)
}.getOrNull()

fun Task.applyPlugin(
    name: String
) = runCatching {
    val pluginName = findPlugin(name)?.name
    println("Applying plugin: ${pluginName ?: name}")
    project.pluginManager.apply(name)
}

@Suppress("UNNECESSARY_SAFE_CALL")
inline fun <reified T> Task.configureExtension(
    crossinline block: T.() -> Unit
) {
    runCatching {
        val name = T::class.simpleName?.replace("_Decorated", "")
        println("Configuring extension: $name")
        project.extensions.getByType(T::class.java)?.apply {
            block(this)
        } ?: throw (GradleException("Extension $name not found"))
    }
}

inline fun <reified T : Task> TaskContainer.byType(): T =
    withType(T::class.java).firstOrNull()
        ?: throw GradleException("Task ${T::class.java} not found")

inline fun <reified T : Task> TaskContainer.byName(name: String): T =
    findByName(name) as T?
        ?: throw GradleException("Task $name not found")

inline fun <reified T : Task> Task.runAfter() = tasks.forEach { task ->
    if (task is T) {
        task.finalizedBy(this)
    }
}

inline fun <reified T : Task> Task.shouldRunAfter() = tasks.forEach { task ->
    if (task is T) {
        this.shouldRunAfter(task)
    }
}

fun Task.runAfterAllAssembleVariants(vararg exclude: String) = assembleTasks.map { task ->
    var excluded = false
    exclude.forEach { exclude ->
        excluded = excluded or task.name.startsWith("assemble${exclude.capitalize()}")
    }
    if (!excluded) dependsOn(task) else null
}

fun Task.runAfterAllAssembleVariants(
    filter: (Task) -> Boolean
) = assembleTasks.filter(filter).onEach { task -> dependsOn(task) }

fun Task.runBeforeAssemble() = assembleTasks.forEach { task -> task.dependsOn(this) }

fun Task.setTaskDescription(description: String?): Task = this.apply {
    description?.let { this.description = it }
}

val Task.android: AppExtension
    get() = project.extensions.getByType(AppExtension::class.java)

val Task.packageName: String
    get() = android.defaultConfig.applicationId ?: ""

val Task.variants: List<BaseVariant>
    get() = android.applicationVariants.toTypedArray().toList()

val Task.taskName: String
    get() = this.name

val Task.taskGroup: String
    get() = this.group.toString()

val Task.gradleParams: StartParameter
    get() = project.gradle.startParameter

val Task.taskParameters: Map<String, String>
    get() = gradleParams.projectProperties

val Task.runningTasks: List<String>
    get() = gradleParams.taskNames

val Task.hasAssembleTask: Boolean
    get() = runningTasks.any { task -> task.lowercase().contains("assemble") }

val Task.normalizedClassName: String
    get() = this::class.simpleName!!.replace("_Decorated", "")

val Task.resOutputDirectory
    get() =
        project.buildDirectory
            .file("generated")
            .file("res")
            .file(normalizedClassName)
            .file(variantName.lowercase())

val Task.variantName: String
    get() = (this as? BaseTask)?.variantName ?: ""

val Task.srcOutputDirectory
    get() = project.buildDirectory
        .file("generated")
        .file("source")
        .file(normalizedClassName)
        .file(variantName.lowercase())

val BaseTask.androidManifestFiles: List<File>
    get() = project.androidExtension.let { app ->
        listOfNotNull(
            "main",
            variant.name,
            variant.buildType?.name,
            variant.flavorName
        ).filterNotEmpty().distinct().map { variantName ->
            project.file(app.sourceSets.getByName(variantName).manifest.srcFile)
        }.filter { manifestFile ->
            manifestFile.exists()
        }
    }


