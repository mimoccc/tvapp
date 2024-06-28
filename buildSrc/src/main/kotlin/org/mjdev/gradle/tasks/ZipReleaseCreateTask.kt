/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.zipArchive

open class ZipReleaseCreateTask : BaseTask() {

    private val zipFileName = "release.zip"

    @Suppress("DEPRECATION")
    private val files = mutableListOf(
        project.rootDir
            .resolve("readme.md"),
        project.rootDir
            .resolve("dependencies.md"),
        project.rootDir
            .resolve("documentation"),
        project.rootDir
            .resolve("reports"),
        project.rootDir
            .resolve("web"),
        project.buildDir
            .resolve("outputs")
            .resolve("apk")
            .resolve("debug"),
        project.buildDir
            .resolve("outputs")
            .resolve("apk")
            .resolve("release"),
    )

    private val zipFile
        get() = project.rootDir.resolve(zipFileName)

    init {
        group = "mjdev"
        description = "This task makes archive from build artifacts and wiki, and logs."
        outputs.upToDateWhen { false }
    }

    override fun onClean() {
        if (zipFile.exists()) {
            zipFile.delete()
        }
    }

    override fun onAssemble() {
        zipArchive(files, zipFile)
    }

}
