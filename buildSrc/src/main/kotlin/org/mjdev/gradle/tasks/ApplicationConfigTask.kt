/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.runConfigured
import org.mjdev.gradle.plugin.config.AppConfig

open class ApplicationConfigTask : BaseTask() {

    init {
        group = "mjdev"
        description = "This task configure android application project."
        outputs.upToDateWhen { false }
    }

    override fun doTask() = runConfigured<AppConfig> {
//        println("---------------------------------------------------------------------")
//        println("App Configuration")
//        println("---------------------------------------------------------------------")
//        println(project.versionName)
//        println("---------------------------------------------------------------------")
//        println(autoCorrectCode)
//        println(ignoreCodeFailures)
//        println(createDocumentation)
//        println(reportUndocumentedFiles)
//        println(failOnDocumentationWarning)
//        println(createReleaseNotes)
//        println(createZipRelease)
//        println(createInfoClass)
//        println(createWebApp)
//        println(createWebSiteFromGit)
//        println(renameApkOutputByAppID)
//        println(launcherIconByBuildType)
//        println("---------------------------------------------------------------------")
//        println(codeReportsDir)
//        println(documentationDir)
//        println(detectConfigFile)
//        println("---------------------------------------------------------------------")
    }
}