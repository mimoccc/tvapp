/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest

import org.mjdev.gradle.custom.unittest.Constants.Companion.KOTLIN_FILE_EXTENSION
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.extension

@Suppress("PrivatePropertyName")
internal class FilePathHelperImpl(
    private val sourceDirectoryList: List<String>,
    private val exclude: List<String>
) : FilePathHelper {

    private val KOTLIN_EXTENSION = KOTLIN_FILE_EXTENSION.replace(".","")

    override fun getFilePaths(): List<Path> {
        val answer = ArrayList<Path>()
        for (sourceDirectory in sourceDirectoryList) {
            val projectDirAbsolutePath = Paths.get(sourceDirectory)
            Files.walk(projectDirAbsolutePath).filter { item ->
                Files.isRegularFile(item)
            }.filter { item ->
                item.extension == KOTLIN_EXTENSION
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