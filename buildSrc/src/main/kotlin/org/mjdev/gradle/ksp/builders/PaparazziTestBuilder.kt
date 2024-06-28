/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.builders

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.io.File
import org.mjdev.gradle.ksp.base.ITestBuilder
import org.mjdev.gradle.ksp.base.ITestBuilder.Companion.fileFromPackageName
import org.mjdev.gradle.ksp.base.ITestBuilder.Companion.file

class PaparazziTestBuilder(
    private val projectDir: File
) : ITestBuilder {

    private var packageName: String? = null
    private var fileName: String? = null
    private var fncName: String? = null

    private val unitTestDir: File
        get() = projectDir
            .file("src")
            .file("test")
            .file("kotlin")
            .fileFromPackageName(packageName ?: "")

    private val normalizedFileName: String
        get() = File(fileName!!).nameWithoutExtension + "Screenshot"

    private val unitTestFile: File
        get() = unitTestDir.file(normalizedFileName, "kt")

    fun packageName(name: String?): PaparazziTestBuilder {
        this.packageName = name
        return this
    }

    fun fileName(name: String?): PaparazziTestBuilder {
        this.fileName = name
        return this
    }

    fun function(function: KSFunctionDeclaration): PaparazziTestBuilder {
        val fncParent = function.parent
        this.fncName = if (fncParent is KSClassDeclaration) {
            "${fncParent.simpleName.asString()}().${function.simpleName.asString()}"
        } else {
            "${function.simpleName.asString()}()"
        }
        return this
    }

    private fun createContent(): String = StringBuilder().apply {
        appendLine("package $packageName")
        appendLine()
        appendLine("import app.cash.paparazzi.Paparazzi")
        appendLine("import org.junit.Rule")
        appendLine("import org.junit.Test")
        appendLine()
        appendLine("class $normalizedFileName {")
        appendLine("    @get:Rule")
        appendLine("    val paparazzi = Paparazzi()")
        appendLine()
        appendLine("    @Test")
        appendLine("    fun makeScreenshot() {")
        appendLine("        try {")
        appendLine("            paparazzi.snapshot {")
        appendLine("                $fncName()")
        appendLine("            }")
        appendLine("        } catch (_: Throwable) {")
        appendLine("        }")
        appendLine("    }")
        appendLine("}")
    }.toString()

    override fun build(codeGenerator: CodeGenerator) {
        if (packageName != null && fileName != null) {
            unitTestFile.apply {
                parentFile.mkdirs()
                createNewFile()
                writeText(createContent())
            }
        }
    }

}