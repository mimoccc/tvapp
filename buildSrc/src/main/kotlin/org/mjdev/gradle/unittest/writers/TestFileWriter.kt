package org.mjdev.gradle.unittest.writers

import com.squareup.kotlinpoet.FileSpec
import java.nio.file.Path

internal interface TestFileWriter {
    fun writeFile(file: FileSpec, sourceCodePath: Path)
}