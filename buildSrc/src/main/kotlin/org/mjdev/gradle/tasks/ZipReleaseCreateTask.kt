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

@Suppress("LeakingThis")
open class ZipReleaseCreateTask : Zip() {

    private val files = arrayListOf(
        project.rootProject.file("documentation"),
        project.rootProject.file("dependencies.txt"),
        project.rootProject.file("README.md"),
        // todo due variant
        project.file("build").file("outputs").file("apk").file("debug"),
        project.file("build").file("outputs").file("apk").file("release"),
        project.file("build").file("outputs").file("logs")
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
