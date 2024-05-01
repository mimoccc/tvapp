/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectVersion

open class ZipReleaseCreateTask : BaseTask() {

    private val releaseFile = project.rootProject
        .file("documentation")
        .file("release-notes-$projectVersion.md")

    private val readmeFile = project.rootProject
        .file("readme.md")

    private val files = arrayListOf(
        releaseFile,
        readmeFile,
        project.rootProject.file("dependencies.txt"),
        project.rootProject.file("reports"),
        project.rootProject.file("web"),
        project.file("build").file("outputs").file("apk").file("debug"),
        project.file("build").file("outputs").file("apk").file("release"),
    )

    private val zipFileName = "zipFileName"
    private val zipFile = project.rootDir.resolve(zipFileName)

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
//        from(files)
//        include("*", "*/*", "*/**")
//        includeEmptyDirs = true
//        destinationDirectory.set(project.rootProject.rootDir)
//        archiveFileName.set("release.zip")
        // todo
    }

}
