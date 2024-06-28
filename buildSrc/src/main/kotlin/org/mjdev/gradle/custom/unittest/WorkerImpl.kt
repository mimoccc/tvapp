/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest

import org.mjdev.gradle.custom.unittest.makers.ClassMakerImpl
import org.mjdev.gradle.custom.unittest.makers.KotlinFileMakerImpl
import org.mjdev.gradle.custom.unittest.makers.ClassMaker
import org.mjdev.gradle.custom.unittest.makers.KotlinFileMaker
import org.mjdev.gradle.custom.unittest.writers.TestFileWriter
import org.mjdev.gradle.custom.unittest.writers.TestFileWriterImpl
import java.net.URL

internal class WorkerImpl(
    private val urls: List<URL>,
    private val sourceDirectoryList: List<String>,
    private val exclude: List<String>,
    private val filePathHelper: FilePathHelper = FilePathHelperImpl(sourceDirectoryList, exclude),
    private val classMaker: ClassMaker = ClassMakerImpl(urls),
    private val kotlinFileMaker: KotlinFileMaker = KotlinFileMakerImpl(),
    private val testFileWriter: TestFileWriter = TestFileWriterImpl(sourceDirectoryList)
) : Worker {
    override fun work() {
        val paths = filePathHelper.getFilePaths()
        for (path in paths) {
            val clazz = classMaker.makeClass(path)
            val file = clazz?.let { kotlinFileMaker.makeKotlinFile(it) }
            if (file != null) {
                testFileWriter.writeFile(file, path)
            }
        }
    }
}