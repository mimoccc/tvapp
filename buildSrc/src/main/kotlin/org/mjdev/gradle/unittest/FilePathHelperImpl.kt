/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest

import org.mjdev.gradle.unittest.Constants.Companion.JAVA_FILE_EXTENSION
import org.mjdev.gradle.unittest.Constants.Companion.KOTLIN_FILE_EXTENSION
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

internal class FilePathHelperImpl(
    private val sourceDirectoryList: List<String>,
    private val exclude: List<String>
) : FilePathHelper {
    override fun getFilePaths(): List<Path> {
        val answer = ArrayList<Path>()
        for (sourceDirectory in sourceDirectoryList) {
            val projectDirAbsolutePath = Paths.get(sourceDirectory)
            Files.walk(projectDirAbsolutePath).filter { item ->
                Files.isRegularFile(item)
            }.filter { item ->
                (item.toString().endsWith(KOTLIN_FILE_EXTENSION) || item.toString()
                    .endsWith(JAVA_FILE_EXTENSION))
            }.forEach { item ->
                var shouldExclude = false
                for (exc in exclude) {
                    if (item.toString().startsWith(exc)) {
                        shouldExclude = true
                    }
                }
                if (!shouldExclude) {
                    answer.add(item)
                }
            }
        }
        return answer
    }
}