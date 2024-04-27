/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import kotlin.reflect.KClass

@Suppress("unused")
fun createFile(
    name: String,
    packageName: String = "",
    block: FileScope.() -> Unit = {}
): FileSpec = FileSpec.builder(packageName, name).apply {
    val fileScope = FileScope()
    block.invoke(fileScope)
    fileScope.functions.forEach { funSpec ->
        addFunction(funSpec)
    }
    fileScope.classes.forEach { classSpec ->
        addType(classSpec)
    }
}.build()

@Suppress("unused")
class FileScope {
    val functions = mutableListOf<FunSpec>()
    val classes = mutableListOf<TypeSpec>()

    fun createFnc(
        fncName: String,
        receiverType: KClass<*>? = null,
        returnType: KClass<*>? = Unit::class,
        block: FunSpec.Builder.() -> Unit = {}
    ): FunSpec = FunSpec.builder(fncName).apply {
        receiverType?.let { receiver(it) }
        returnType?.let { returns(it) }
        block.invoke(this)
    }.build().also { fnSpec ->
        functions.add(fnSpec)
    }

    fun createClass(
        name: String,
        block: ClassScope.() -> Unit = {}
    ) {
        TypeSpec.classBuilder(name).also { classSpec ->
            val classScope = ClassScope(classSpec)
            block.invoke(classScope)
            classes.add(classSpec.build())
        }
    }
}

@Suppress("unused")
class ClassScope(
    private val builder: TypeSpec.Builder
) {
    fun createFnc(
        name: String,
        receiverType: KClass<*>? = null,
        returnType: KClass<*>? = null,
        block: FunSpec.Builder.() -> Unit = {}
    ): FunSpec = FunSpec.builder(name).apply {
        receiverType?.let { receiver(it) }
        returnType?.let { returns(it) }
        block.invoke(this)
    }.build().also { fnSpec ->
        builder.addFunction(fnSpec)
    }
}

fun FileSpec.write(
    outputDir: File,
    block:FileSpec.()->Unit
) {
    writeTo(outputDir)
    block.invoke(this)
}