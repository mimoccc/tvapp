/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.bundling.Zip
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectVersion

@Suppress("LeakingThis")
open class ZipReleaseCreateTask : Zip() {

    private val releaseFile = project.rootProject
        .file("documentation")
        .file("app")
        .file("release-notes-$projectVersion.md")

    private val readmeFile = project.rootProject
        .file("readme.md")

    private val files = arrayListOf(
        releaseFile,
        readmeFile,
        project.rootProject.file("dependencies.txt"),
        project.file("build").file("outputs").file("apk").file("debug"),
        project.file("build").file("outputs").file("apk").file("release"),
    )

    init {
        group = "mjdev"
        description = "This task makes archive from build artifacts and wiki, and logs."

        outputs.upToDateWhen { false }

        from(files)
        include("*", "*/*", "*/**")
        includeEmptyDirs = true
        destinationDirectory.set(project.rootProject.rootDir)
        archiveFileName.set("release.zip")
    }

}
