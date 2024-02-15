/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.versions.base.DependencyUpdatesTask
import java.io.File

open class DependencyUpdateTask : DependencyUpdatesTask() {

    private val dependenciesOutputDir: File = project.rootProject.rootDir
    private val reportName: String = "dependencies"
    private val outputFormat = "text"


    init {
        group = "mjdev"
        description = "Displays the dependency updates for the project."
        outputDir = dependenciesOutputDir.absolutePath
        reportFileName = reportName
        outputFormatter = outputFormat
        checkForGradleUpdate = false
        checkConstraints = false
        checkBuildEnvironmentConstraints = false
        resolutionStrategy = null
//        outputs.upToDateWhen { false }
    }

}
