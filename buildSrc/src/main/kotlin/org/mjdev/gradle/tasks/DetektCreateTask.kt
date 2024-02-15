/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.TaskAction
import org.mjdev.gradle.base.BaseTask


open class DetektCreateTask : BaseTask() {

    private val documentationDir = "documentation"

    init {
        group = "mjdev"
        description = "Kotlin code check and format task."

//        tasks.byType<DetektGenerateConfigTask>().apply {
//            runAfter(this)
//        }
//        tasks.byName<Task>("ktlintFormat").apply {
//            runAfter(cleanTask)
//        }

        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun taskAction() {
//        applyPlugin("org.jlleitschuh.gradle.ktlint")
//        configureExtension<DetektExtension> {
//            autoCorrect = true
//            parallel = false
//
//            source = project.files("src/main/kotlin")
//            reportsDir = project.rootProject.file(documentationDir)
//        }
    }

}
