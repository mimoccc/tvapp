/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("DEPRECATION")

package org.mjdev.gradle.logger

import org.gradle.BuildAdapter
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState

// todo all messages handling and remove non errors
@Suppress("deprecation", "unused")
class CustomEventLogger : BuildAdapter(), TaskExecutionListener {

    override fun beforeExecute(task: Task) {
        //println("[${task.name}]")
    }

    override fun afterExecute(task: Task, state: TaskState) {
    }

    @Deprecated(message = "Deprecated in Java", replaceWith = ReplaceWith(""))
    override fun buildFinished(result: BuildResult) {
        when (result.failure) {
            null -> println("Build completed.")
            else -> {
                (result.failure as Throwable).printStackTrace()
            }
        }
    }
}