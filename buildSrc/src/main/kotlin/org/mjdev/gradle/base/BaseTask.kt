/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("DEPRECATION", "unused")

package org.mjdev.gradle.base

import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import org.mjdev.gradle.extensions.applicationId
import org.mjdev.gradle.extensions.buildDirectory
import org.mjdev.gradle.extensions.capitalize
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.hasAssembleTask
import org.mjdev.gradle.extensions.println
import org.mjdev.gradle.extensions.runAfterCleanTask
import java.net.URL

abstract class BaseTask : DefaultTask() {

    @Internal
    lateinit var variant: ApplicationVariant

    @get:Internal
    val variantName: String
        get() = variant.name.capitalize()

    @get:Internal
    val javaCompiledClasses: URL
        get() = variant
            .javaCompileProvider
            .get()
            .destinationDirectory
            .asFile
            .get()
            .apply {
                println("> Compiled java classes path : $this")
            }
            .toURI()
            .toURL()

    @get:Internal
    val kotlinCompiledClasses: URL
        get() = project.buildDirectory
            .file("tmp")
            .file("kotlin-classes")
            .file(variant.name)
            .file(project.applicationId.replace(".", "/"))
            .apply {
                println("> Compiled kotlin classes path : $this")
            }
            .toURI()
            .toURL()

    @get:Internal
    val resDependencies
        get() = variant
            .getCompileClasspath(null)
            .files
            .map {
                it.toURI().toURL()
            }.toTypedArray()

    @get:Internal
    val kotlinCompileTask
        get() = project.tasks
            .findByName("compile${variant.name.capitalize()}Kotlin") as? SourceTask

    @get:Internal
    val isAssembleTask
        get() = hasAssembleTask

    init {
        runAfterCleanTask()
    }

    @TaskAction
    fun taskAction() {
        when (isAssembleTask) {
            true -> onAssemble()
            false -> onClean()
        }
    }

    abstract fun onClean()

    abstract fun onAssemble()

}