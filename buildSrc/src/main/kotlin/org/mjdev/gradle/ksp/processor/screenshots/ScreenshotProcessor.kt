/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.processor.screenshots

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

import com.google.devtools.ksp.validate
import org.mjdev.gradle.ksp.base.ITestBuilder
import org.mjdev.gradle.ksp.base.IProcessor
import org.mjdev.gradle.ksp.visitors.ScreenShotsVisitor
import org.mjdev.gradle.annotations.CreateScreenShot

@Suppress("unused")
class ScreenshotProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor, IProcessor {

    private val screenShotAnnotation = CreateScreenShot::class.java.canonicalName

    private val builders = mutableListOf<ITestBuilder>()

    override fun addBuilder(builder: ITestBuilder) {
        builders.add(builder)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        println("Processor for KSP starts to process annotations: $screenShotAnnotation")
        val screenshots : Sequence<KSAnnotated> = try {
            resolver.getSymbolsWithAnnotation(screenShotAnnotation)
        } catch (t:Throwable) {
            logger.error(t.message ?: "unknown error to get annotation.", null)
            emptySequence()
        }
        if (!screenshots.iterator().hasNext()) {
            return emptyList()
        }
        screenshots.forEach {
            it.accept(ScreenShotsVisitor(this), Unit)
        }
        val unableToProcess = screenshots.filterNot {
            it.validate()
        }.toList()
        builders.forEach { builder ->
            builder.build(codeGenerator)
        }
        return unableToProcess
    }

}
