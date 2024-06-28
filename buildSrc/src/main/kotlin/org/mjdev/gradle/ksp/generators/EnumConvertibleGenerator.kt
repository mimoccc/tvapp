/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.generators;

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import org.mjdev.gradle.ksp.data.ProcessedEnumConvertible
import java.io.OutputStream

class EnumConvertibleGenerator(
    private val codeGenerator: CodeGenerator
) {

    fun generate(
        processedEnumConvertible: ProcessedEnumConvertible
    ) = with(processedEnumConvertible) {
        // Generate file and declare its dependencies
        val mapperFile: OutputStream = codeGenerator.createNewFile(
            dependencies = Dependencies(
                aggregating = true,
                sources = arrayOf(classDeclaration.containingFile!!)
            ),
            packageName = packageName,
            fileName = "${className}Mapper",
            extensionName = "kt"
        )
        val sourceCode = buildConvertibleMapperSource(
            packageName = processedEnumConvertible.packageName,
            className = processedEnumConvertible.className,
            keyName = processedEnumConvertible.keyName
        ).toByteArray()
        mapperFile.write(sourceCode)
        mapperFile.close()
    }

    private fun buildConvertibleMapperSource(
        packageName: String,
        className: String,
        keyName: String?
    ): String = """
            |package $packageName
            |
            |object ${className}Mapper {
            |    
            |    fun from(${keyName}: String?): ${className}? = values().find {
            |        it.value.equals(${keyName}, ignoreCase = true)
            |    }
            |}
        """.trimMargin()
}
