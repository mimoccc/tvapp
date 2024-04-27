/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.TaskAction
import java.io.File
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectName
import org.mjdev.gradle.base.BaseTask

open class DokkaCleanTask : BaseTask() {

    private val docProjectDir: File
        get() =  project.rootProject
            .file("documentation")
            .file(projectName)

    init {
        group = "mjdev"
        description = "Documentation clean task."
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun taskAction() {
        project.rootProject.file(docProjectDir).deleteRecursively()
    }

}
