/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.visitors

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

import org.mjdev.gradle.ksp.data.ProcessedEnumConvertible
import org.mjdev.gradle.annotations.EnumConvertible
import org.mjdev.gradle.extensions.extractKeyName

class EnumConvertibleVisitor(
    private val processedEnumConvertibles: MutableList<ProcessedEnumConvertible>
) : KSVisitorVoid() {

    private val enumConvertibleAnnotation = EnumConvertible::class.simpleName

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        if (classDeclaration.classKind != ClassKind.ENUM_CLASS) {
            throw IllegalStateException(
                "$enumConvertibleAnnotation can be used only in an enum class."
            )
        }
        val processedEnumConvertible = ProcessedEnumConvertible(
            packageName = classDeclaration.packageName.asString(),
            className = classDeclaration.simpleName.asString(),
            keyName =  classDeclaration.extractKeyName(),
            classDeclaration = classDeclaration
        )
        processedEnumConvertibles.add(processedEnumConvertible)
    }

}