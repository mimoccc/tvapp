/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom

import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.custom.unittest.Worker
import org.mjdev.gradle.custom.unittest.WorkerImpl

abstract class TestCaseGeneratorTask : BaseTask() {

    private val urls
        get() = mutableListOf(
            javaCompiledClasses,
            kotlinCompiledClasses,
        ).apply { addAll(resDependencies) }

    private val sourceDirectoryList
        get() = listOf(
            "src/main/kotlin",
            "src/main/java"
        ).mapNotNull { path ->
            val file = project.file(path)
            if (file.exists()) file.absolutePath else null
        }

    private val exclude: List<String> = mutableListOf()

    private var worker: Worker? = null

    init {
        group = "mjdev"
        description = "Test classes generation tool"
    }

    fun injectSource() {
        worker = WorkerImpl(urls, sourceDirectoryList, exclude).apply {
            work()
        }
    }

}