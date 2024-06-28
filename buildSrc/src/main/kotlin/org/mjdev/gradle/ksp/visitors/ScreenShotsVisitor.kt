/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.visitors

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import org.mjdev.gradle.ksp.base.IProcessor
import org.mjdev.gradle.ksp.builders.PaparazziTestBuilder
import org.mjdev.gradle.extensions.projectDir

class ScreenShotsVisitor(
    private val processor: IProcessor
) : KSVisitorVoid() {

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        val file = function.containingFile
        if (file != null) {
            PaparazziTestBuilder(file.projectDir).fileName(file.fileName)
                .packageName(file.packageName.asString()).function(function).also {
                    processor.addBuilder(it)
                }
        }
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // todo
//            val file = classDeclaration.containingFile
//            if (file != null) {
//                PaparazziTestBuilder(file.projectDir).fileName(file.fileName)
//                    .packageName(file.packageName.asString()).function(classDeclaration).also {
//                        addBuilder(it)
//                    }
//            }
    }

}