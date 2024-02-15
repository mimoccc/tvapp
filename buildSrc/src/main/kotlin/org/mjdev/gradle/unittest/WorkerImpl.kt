/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest

import org.mjdev.gradle.unittest.makers.ClassMakerImpl
import org.mjdev.gradle.unittest.makers.KotlinFileMakerImpl
import org.mjdev.gradle.unittest.makers.ClassMaker
import org.mjdev.gradle.unittest.makers.KotlinFileMaker
import org.mjdev.gradle.unittest.writers.TestFileWriter
import org.mjdev.gradle.unittest.writers.TestFileWriterImpl
import java.net.URL

internal class WorkerImpl(
    private val urls: Array<URL>,
    private val sourceDirectoryList: List<String>,
    private val exclude: List<String>,
    private val filePathHelper: FilePathHelper = FilePathHelperImpl(sourceDirectoryList, exclude),
    private val classMaker: ClassMaker = ClassMakerImpl(urls),
    private val kotlinFileMaker: KotlinFileMaker = KotlinFileMakerImpl(),
    private val testFileWriter: TestFileWriter = TestFileWriterImpl(sourceDirectoryList)
) : Worker {
    override fun work() {
        val paths = filePathHelper.getFilePaths()
        println("> Got project sources:")
        sourceDirectoryList.forEach { path ->
            println("> $path")
        }
        println("> Got files: ${paths.size}")
        for (path in paths) {
            println("> Creating class for $path")
            val clazz = classMaker.makeClass(path)
            println("> Class created: ${clazz?.name}")
            val file = clazz?.let {
                kotlinFileMaker.makeKotlinFile(it)
            }
            println("> Class file created: ${file?.name}")
            if (file != null) {
                println("> file created : $path")
                testFileWriter.writeFile(file, path)
            }
        }
    }
}