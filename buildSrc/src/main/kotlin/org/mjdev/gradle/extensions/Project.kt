/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused", "DEPRECATION", "RedundantRunCatching")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariant
import org.gradle.TaskExecutionRequest
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSet.TEST_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import java.io.File
import org.mjdev.gradle.base.BaseTask
import kotlin.reflect.full.isSubclassOf

private const val VERSION_TAG_MAJOR = "majorVersion"
private const val VERSION_TAG_MINOR = "minorVersion"
private const val VERSION_TAG_PATCH = "patchVersion"

val Project.versionCode
    get(): Int {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return major * 10000 + minor * 100 + patch
    }

val Project.versionName: String
    get() {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return "${major}.${minor}.${patch}"
    }

val Project.taskRequest: List<TaskExecutionRequest>
    get() = gradle.startParameter.taskRequests

val Project.taskRequestsArgs: List<String>
    get() = ArrayList<String>().also { argsList ->
        taskRequest.map { tr ->
            tr.args
        }.let { list ->
            list.forEach { argsList.addAll(it) }
        }
    }

val Project.taskRequestAssembleTasks
    get() = taskRequestsArgs.filter { arg -> arg.startsWith("assemble") }

val Project.taskRequestBundleTasks
    get() = taskRequestsArgs.filter { arg -> arg.startsWith("bundle") }

val Project.taskRequestHasAssembles
    get() = taskRequestAssembleTasks.isNotEmpty()

val Project.taskRequestHasBundles
    get() = taskRequestBundleTasks.isNotEmpty()

//val Project.currentFlavor: String
//    get() {
//        val buildTypes = arrayOf("Release", "Debug", "Mock")
//        val str: String = when {
//            taskRequestHasAssembles -> taskRequestAssembleTasks.joinToString { "," }
//            taskRequestHasBundles -> taskRequestBundleTasks.joinToString { "," }
//            else -> ""
//        }
//        return when {
//            taskRequestHasAssembles -> Pattern
//                .compile("assemble(\\w+)(${buildTypes.joinToString { "|" }})")
//                .matcher(str)
//                .let { matcher ->
//                    if (matcher.find()) matcher.group(1).lowercase()
//                    else ""
//                }
//
//            taskRequestHasBundles -> Pattern
//                .compile("bundle(\\w+)(${buildTypes.joinToString { "|" }})")
//                .matcher(str)
//                .let { matcher ->
//                    if (matcher.find()) matcher.group(1).lowercase()
//                    else ""
//                }
//
//            else -> "-"
//        }
//    }

val Project.android: ApplicationExtension
    get() = extensions.getByType(ApplicationExtension::class.java)

val Project.isAndroid: Boolean
    get() = extensions.findByType<ApplicationExtension>() != null

val Project.packageName: String
    get() = applicationId

val Project.applicationId: String
    get() = if (isAndroid) android.defaultConfig.applicationId ?: "" else ""

val Project.androidComponents: AndroidComponentsExtension<*, *, *>
    get() = extensions.getByType(AndroidComponentsExtension::class.java)

val Project.buildDirectory: File
    get() = layout.buildDirectory.asFile.get()

val Project.sourceSets
    get() = properties["sourceSets"] as SourceSetContainer

val Project.mainSourceSet: SourceSet?
    get() = sourceSets.findByName(MAIN_SOURCE_SET_NAME)

val Project.testSourceSet: SourceSet?
    get() = sourceSets.findByName(TEST_SOURCE_SET_NAME)

val Project.androidExtension: AppExtension
    get() = extensions.getByType(AppExtension::class.java)

val Project.kotlinExtension: KotlinProjectExtension
    get() = extensions.getByName("kotlin") as KotlinProjectExtension

val Project.assembleTasks: List<Task>
    get() = mutableListOf<Task>().apply {
        androidExtension.apply {
            applicationVariants.all { variant ->
                val task = project.tasks.getByName("assemble${variant.name.capitalize()}")
                add(task)
            }
        }
    }

val Project.variants: List<ApplicationVariant>
    get() = androidExtension.applicationVariants.toList()

val Project.unitTestDir: File
    get() = file("src").file("test").file("java")

inline fun <reified T : Task> Project.registerTask(
    name: String,
    configurationAction: Action<T> = Action<T> {}
): List<T> = if (T::class.isSubclassOf(BaseTask::class)) {
    mutableListOf<T>().apply {
        variants.forEach { variant ->
            registerTask<T>(
                "$name${variant.name.capitalize()}"
            ).apply {
                (this.get() as BaseTask).variant = variant
            }.get().also { task ->
                task.apply {
                    configurationAction.execute(this)
                    add(this)
                }
            }
        }
    }
} else {
    listOf(registerTask<T>(name).get().apply {
        configurationAction.execute(this)
    })
}

inline fun <reified T : Task> Project.registerTask(
    name: String
): TaskProvider<T> {
//    println("> Registering task : $name[${T::class.simpleName}]")
    return tasks.register(name, T::class.java)
}

fun Project.registerSources(outDir: File, variant: BaseVariant) {
    if (outDir.absolutePath.contains("generated/source")) {
        project.kotlinExtension.sourceSets.findByName(variant.name)?.apply {
            kotlin.srcDirs(outDir)
        }
        project.androidExtension.sourceSets.findByName(variant.name)?.apply {
            kotlin.srcDirs(outDir)
        }
    } else if (outDir.absolutePath.contains("generated/res")) {
        project.androidExtension.sourceSets.findByName(variant.name)?.apply {
            res.srcDirs(outDir)
        }
//        variant.mergeResourcesProvider.configure {
//            it.dependsOn(task)
//        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Project.extension(
    name: String,
    cls: Class<T>,
    canCreate: Boolean = true
): T? = extensions.findByName(name) as? T ?: if (canCreate) extensions.create(
    name,
    cls
) else null


fun Project.propOrDefault(propertyName: String, defaultValue: Any): Any = project
    .properties[propertyName] ?: (System.getenv(propertyName) ?: defaultValue)

fun Project.propertyOrEmpty(name: String): String = findProperty(name) as String? ?: ""

fun Project.fileTree(directory: File, inc: String, exc: String): List<File> {
    return project.fileTree(directory) {
        setExcludes(listOf(exc))
        setIncludes(listOf(inc))
    }.toList()
}

fun Project.fileTree(directory: String, inc: String, exc: String): List<File> {
    return project.fileTree(directory) {
        setExcludes(listOf(exc))
        setIncludes(listOf(inc))
    }.toList()
}

//fun Project.androidComponents(
//    block: AndroidComponentsExtension<*, *, *>.() -> Unit
//) {
//    block.invoke(androidComponents)
//}
