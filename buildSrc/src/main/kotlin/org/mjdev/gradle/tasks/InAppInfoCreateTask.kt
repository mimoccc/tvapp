/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.mjdev.gradle.extensions.packageName
import org.mjdev.gradle.extensions.createFile
import org.mjdev.gradle.extensions.srcOutputDirectory
import java.io.File
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.write
import org.mjdev.gradle.extensions.registerSourceDirectory

@Suppress("MemberVisibilityCanBePrivate")
open class InAppInfoCreateTask : BaseTask() {

    @get:OutputDirectory
    val srcOutputDir: File
        get() = srcOutputDirectory

    init {
        group = "mjdev"
        description = "Create ApplicationInfo class"
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun taskAction() {
        registerSourceDirectory(srcOutputDir)
        createFile(
            name = "ApplicationInfo",
            packageName = project.packageName
        ) {
            createClass(
                name = "ApplicationInfo"
            ) {
                createFnc(
                    name = "getInfo",
                    returnType = String::class
                ) {
                    addStatement("return \"\"")
                }
            }
        }.write(srcOutputDir) {
            println("> Writing generated file: ${srcOutputDir}/${this.name}.kt")
        }
    }

}
