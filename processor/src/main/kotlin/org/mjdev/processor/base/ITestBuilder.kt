/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.processor.base

import com.google.devtools.ksp.processing.CodeGenerator
import java.io.File

interface ITestBuilder {
    fun build(codeGenerator: CodeGenerator)

    companion object {
        fun File.file(name: String) = File(this, name)

        fun File.file(name: String, ext: String) = File(this, "$name.$ext")

        fun File.fileFromPackageName(packageName: String): File {
            val subFolders = packageName.split(".")
            var root = this
            for (subfolder in subFolders) {
                root = root.file(subfolder)
            }
            return root
        }
    }
}