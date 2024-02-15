/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest.makers

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import org.mockito.Mock

internal class KotlinPropertyMakerImpl : KotlinPropertyMaker {
    override fun createProperty(
        parameter: Class<*>,
        name: String,
        mock: Boolean
    ): PropertySpec {
        val answer = PropertySpec.builder(name, parameter.kotlin)
        answer.mutable(true)
        if (parameter.isPrimitive) {
            when (parameter.kotlin) {
                Boolean::class -> {
                    answer.addModifiers(arrayListOf(KModifier.PRIVATE))
                        .initializer("false")
                }
                Char::class -> {
                    answer.addModifiers(arrayListOf(KModifier.PRIVATE))
                        .initializer("a")
                }
                Byte::class, Short::class, Int::class, Long::class -> {
                    answer.addModifiers(arrayListOf(KModifier.PRIVATE))
                        .initializer("1")
                }
                Float::class, Double::class -> {
                    answer.addModifiers(arrayListOf(KModifier.PRIVATE))
                        .initializer("1.0")
                }
                Void::class -> {
                    answer.addModifiers(arrayListOf(KModifier.PRIVATE))
                        .initializer("null")
                }
            }
        } else {
            answer.addModifiers(arrayListOf(KModifier.LATEINIT, KModifier.PRIVATE))
        }
        if (mock) {
            answer.addAnnotation(Mock::class)
        }
        return answer.build()
    }
}