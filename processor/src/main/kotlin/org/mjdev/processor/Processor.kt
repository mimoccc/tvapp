/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import org.mjdev.annotations.CreateScreenShot
import org.mjdev.processor.base.ITestBuilder
import org.mjdev.processor.builders.PaparazziTestBuilder
import java.io.File

@Suppress("unused")
class Processor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {

    private val builders = mutableListOf<ITestBuilder>()

    fun addBuilder(builder: ITestBuilder) {
        builders.add(builder)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val screenshots = resolver.getSymbolsWithAnnotation(CreateScreenShot::class.qualifiedName!!)
        if (!screenshots.iterator().hasNext()) {
            return emptyList()
        }
        screenshots.forEach {
            it.accept(ScreenShotsVisitor(), Unit)
        }
        val unableToProcess = screenshots.filterNot {
            it.validate()
        }.toList()
        builders.forEach { builder ->
            builder.build(codeGenerator)
        }
        return unableToProcess
    }

    inner class ScreenShotsVisitor : KSVisitorVoid() {

        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            val file = function.containingFile
            if (file != null) {
                PaparazziTestBuilder(file.projectDir).fileName(file.fileName)
                    .packageName(file.packageName.asString()).function(function).also {
                        addBuilder(it)
                    }
            }
        }

        private val KSFile.projectDir: File
            get() = File(filePath).let {
                var ff = it
                while (ff.name != "src") ff = ff.parentFile
                ff.parentFile
            }

    }

}
