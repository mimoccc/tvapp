/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.mjdev.gradle.extensions.file
import java.net.URL

abstract class TestCaseGeneratorTask : DefaultTask() {

    private var exclude: List<String> = mutableListOf()
    private var worker: Worker? = null
    private var urls: Array<URL> = arrayOf()

    private val sourceDirectoryList: List<String>
        get() = project.rootProject.subprojects.mapNotNull { project ->
            project
                .file("src")
                .file("main")
                .file("kotlin")
                .let { dir ->
                    if (dir.exists()) dir.absolutePath else null
                }
        }

    init {
        group = "mjdev"
        description = "Test classes generation tool"
    }

    @TaskAction
    fun injectSource() {
        worker = WorkerImpl(urls, sourceDirectoryList, exclude)
        worker?.work()
    }
}