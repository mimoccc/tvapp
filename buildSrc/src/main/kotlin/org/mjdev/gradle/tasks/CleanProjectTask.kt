/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.base.BaseTask

// todo : .gitignore
open class CleanProjectTask : BaseTask() {

    @Suppress("DEPRECATION")
    private val files = mutableListOf(
        project.rootDir.resolve("documentation"),
        project.rootDir.resolve("reports"),
        project.buildDir,
    )

    private fun doClean() {
        files.forEach { file ->
            if (file.exists()) {
                file.listFiles()?.forEach { f ->
                    if (f.isDirectory) {
                        f.deleteRecursively()
                    }
                }
            }
        }
    }

    override fun onClean() {
        doClean()
    }

    override fun onAssemble() {
        doClean()
    }
}