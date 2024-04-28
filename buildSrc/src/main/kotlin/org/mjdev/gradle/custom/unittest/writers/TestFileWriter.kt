/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest.writers

import com.squareup.kotlinpoet.FileSpec
import java.nio.file.Path

internal interface TestFileWriter {
    fun writeFile(file: FileSpec, sourceCodePath: Path)
}