/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.base.BaseTask
import org.gradle.api.tasks.TaskAction

open class DependencyUpdateCleanTask : BaseTask() {

    private val outputFiles = listOf(
        project.rootProject.file("dependencies.txt")
    )

    init {
        group = "mjdev"
        description = "This task clear all release-notes files, to clean project."
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun taskAction() {
        println("> Clearing release zip archive") // todo
        outputFiles.forEach { file ->
            if (file.isDirectory) file.deleteRecursively()
            else file.delete()
        }
    }

}
