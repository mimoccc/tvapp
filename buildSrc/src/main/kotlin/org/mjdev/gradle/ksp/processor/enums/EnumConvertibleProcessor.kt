/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.processor.enums

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import org.mjdev.gradle.ksp.data.ProcessedEnumConvertible
import org.mjdev.gradle.ksp.visitors.EnumConvertibleVisitor
import org.mjdev.gradle.ksp.generators.EnumConvertibleGenerator
import org.mjdev.gradle.annotations.EnumConvertible

class EnumConvertibleProcessor(codeGenerator: CodeGenerator) : SymbolProcessor {
    private val enumConvertibleExtGenerator by lazy {
        EnumConvertibleGenerator(codeGenerator = codeGenerator)
    }
    private val processedEnumConvertibles by lazy {
        mutableListOf<ProcessedEnumConvertible>()
    }
    private val enumConvertibleVisitor by lazy {
        EnumConvertibleVisitor(
            processedEnumConvertibles = processedEnumConvertibles
        )
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            annotationName = EnumConvertible::class.java.name
        )
        symbols
            .filterIsInstance<KSClassDeclaration>()
            .filter { kSClassDeclaration ->
                kSClassDeclaration.validate()
            }
            .forEach { kSClassDeclaration ->
                kSClassDeclaration.accept(enumConvertibleVisitor, Unit)
            }
        return emptyList()
    }

    override fun finish() {
        processedEnumConvertibles.forEach {
            enumConvertibleExtGenerator.generate(processedEnumConvertible = it)
        }
    }
}
