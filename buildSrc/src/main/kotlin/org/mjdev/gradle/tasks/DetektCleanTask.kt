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

// todo
open class DetektCleanTask : BaseTask() {

    init {
        group = "mjdev"
        description = "Kotlin lint clean task."
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun taskAction() {
        // todo
    }

}
