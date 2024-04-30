/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectVersion
import java.io.File
import org.mjdev.gradle.base.BaseTask

open class ReleaseNotesCleanTask : BaseTask() {

    private val fileName: String
        get() = "release-notes-$projectVersion.md"

    private val docDir: File
        get() = project.rootProject
            .file("documentation")

    private val outputFiles = listOf(
        docDir.file(fileName)
    )

    init {
        group = "mjdev"
        description = "This task clear all release-notes files, to clean project."
        outputs.upToDateWhen { false }
    }

    override fun doTask() {
        outputFiles.forEach { file ->
            if (file.isDirectory) {
                file.deleteRecursively()
            } else {
                file.delete()
            }
        }
    }

}
