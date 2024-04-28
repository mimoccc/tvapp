/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest.writers

import org.mjdev.gradle.custom.unittest.Constants.Companion.DIRECTORY_SEPARATOR
import org.mjdev.gradle.custom.unittest.Constants.Companion.JAVA_DIRECTORY
import org.mjdev.gradle.custom.unittest.Constants.Companion.MAIN_DIRECTORY
import org.mjdev.gradle.custom.unittest.Constants.Companion.PACKAGE_SEPARATOR
import org.mjdev.gradle.custom.unittest.Constants.Companion.TEST_DIRECTORY
import org.mjdev.gradle.custom.unittest.Constants.Companion.TEST_FILE_SUFFIX
import com.squareup.kotlinpoet.FileSpec
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

internal class TestFileWriterImpl(
    private val sourceDirectoryList: List<String>
) : TestFileWriter {
    override fun writeFile(file: FileSpec, sourceCodePath: Path) {
        var testPath: Path = Paths.get("")
        for (source in sourceDirectoryList) {
            if (sourceCodePath.startsWith(source)) {
                var prefix: String
                if (source.endsWith(MAIN_DIRECTORY)) {
                    prefix = source.substringBeforeLast(MAIN_DIRECTORY) + TEST_DIRECTORY
                } else {
                    prefix = source.substringBeforeLast(DIRECTORY_SEPARATOR)
                    var dir = source.substringAfterLast(DIRECTORY_SEPARATOR)
                    dir = TEST_DIRECTORY + dir[0].uppercase() + dir.substring(1)
                    prefix = prefix + DIRECTORY_SEPARATOR + dir
                }
                var actualPathString = sourceCodePath.toString().substringAfter(source)
                actualPathString =
                    actualPathString.substringBeforeLast(PACKAGE_SEPARATOR) + TEST_FILE_SUFFIX + PACKAGE_SEPARATOR + actualPathString.substringAfterLast(
                        PACKAGE_SEPARATOR
                    )
                val testPathString = prefix + actualPathString
                testPath = Paths.get(testPathString)
            }
        }
        val fileCheck = File(testPath.toUri()).isFile
        if (!fileCheck) {
            val myPath = Paths.get(
                testPath.toString()
                    .substringBefore(DIRECTORY_SEPARATOR + JAVA_DIRECTORY + DIRECTORY_SEPARATOR) + DIRECTORY_SEPARATOR + JAVA_DIRECTORY
            )
            file.writeTo(myPath)
        }
    }

}