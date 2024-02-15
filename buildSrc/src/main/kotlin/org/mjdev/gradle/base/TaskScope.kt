/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("DEPRECATION")

package org.mjdev.gradle.base

import com.android.build.gradle.TestedExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.mjdev.gradle.extensions.byName
import org.mjdev.gradle.extensions.byType
import org.mjdev.gradle.extensions.assembleTasks
import org.mjdev.gradle.extensions.capitalize
import org.mjdev.gradle.extensions.registerTask

@Suppress("MemberVisibilityCanBePrivate", "unused", "UnusedReceiverParameter")
class TaskScope<E : PluginExtension, F : TestedExtension>(
    val project: Project,
    val pluginScope: PluginScope<E, F>
) {

    val tasks: TaskContainer
        get() = project.tasks

    val assembleTasks: List<Task>
        get() = project.assembleTasks

    val cleanTask: Task?
        get() = byName("clean")

    val preBuildTask: Task?
        get() = byName("preBuild")

    val assembleDebugTask: Task?
        get() = byName("assembleDebug")

    val ktlintFormatTask: Task?
        get() = byName("ktlintFormat")

    inline fun <reified T : Task> byName(name: String): T? =
        tasks.byName(name) as T?

    inline fun <reified T : Task> byType(): T? =
        tasks.byType() as T?

    inline fun <reified T : Task> register(
        name: String = T::class.java.simpleName.replaceFirstChar { char ->
            char.lowercase()
        }.replace("Task", ""),
        configurationAction: Action<T> = Action<T> {}
    ): List<T> = project.registerTask<T>(name, configurationAction)

    fun  Task.task(name: String, block: Task.() -> Unit) {
        tasks.getByName(name).also { task ->
            block.invoke(task)
        }
    }

    fun  Task.task(
        nameFormat: String,
        variant: BaseVariant,
        block: Task.() -> Unit
    ) {
        tasks.getByName(String.format(nameFormat, variant.name.capitalize())).also { task ->
            block.invoke(task)
        }
    }

    fun Task.task(name: String): Task = tasks.getByName(name)

    fun Task.task(
        nameFormat: String,
        variant: BaseVariant
    ): Task = tasks.getByName(String.format(nameFormat, variant.name.capitalize()))


    inline fun <reified T> printErr(
        message: String,
        includeStackTrace: Boolean = true
    ): T? = printErr(GradleException(message), includeStackTrace)

    inline fun <reified T> printErr(
        error: Throwable,
        includeStackTrace: Boolean = true
    ): T? {
        pluginScope.printErr(error, includeStackTrace)
        return null
    }

    fun println(
        message: String
    ) {
        pluginScope.println(message)
    }

    fun assembleTasks(block: Task.() -> Unit) = assembleTasks.forEach { task ->
        block.invoke(task)
    }

    fun runSafe(
        block: () -> Unit
    ) {
        try {
            block()
        } catch (t: Throwable) {
            printErr(t)
        }
    }

    companion object {
        const val TASK_ASSEMBLE_RELEASE = "assembleRelease"
        const val TASK_ASSEMBLE_DEBUG = "assembleDebug"
        const val TASK_ASSEMBLE_MINIFIED = "assembleMinified"
        const val TASK_ASSEMBLE_MOCK = "assembleMock"

        val ASSEMBLE_TASKS = listOf(
            TASK_ASSEMBLE_DEBUG,
            TASK_ASSEMBLE_RELEASE,
            TASK_ASSEMBLE_MOCK,
            TASK_ASSEMBLE_MINIFIED,
        )
    }

}