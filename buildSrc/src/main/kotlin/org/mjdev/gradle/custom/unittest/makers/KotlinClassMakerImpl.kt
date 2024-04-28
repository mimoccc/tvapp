/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest.makers

import com.squareup.kotlinpoet.TypeSpec
import org.mjdev.gradle.custom.unittest.ClassHelper
import org.mjdev.gradle.custom.unittest.ClassHelperImpl

internal class KotlinClassMakerImpl(
    private val classHelper: ClassHelper = ClassHelperImpl(),
    private val kotlinFunctionMaker: KotlinFunctionMaker = KotlinFunctionMakerImpl(),
    private val kotlinPropertyMaker: KotlinPropertyMaker = KotlinPropertyMakerImpl()
) : KotlinClassMaker {

    override fun createTestClass(clazz: Class<*>, className: String): TypeSpec? {
        val methodMap = classHelper.getMethodMap(clazz)
        if (methodMap.first <= 0) {
            return null
        }
        val classBuilder = TypeSpec.classBuilder(className)
        val testObjectMap = classHelper.getTestObjectMap(clazz)
        classBuilder.addFunction(kotlinFunctionMaker.createBeforeFunction(clazz, testObjectMap))
        for (testObject in testObjectMap) {
            classBuilder.addProperty(
                kotlinPropertyMaker.createProperty(
                    clazz,
                    testObject.key,
                    mock = false
                )
            )
            for (parameter in testObject.value) {
                if (parameter.value == java.lang.String::class.java ||
                    parameter.value == java.lang.Throwable::class.java ||
                    parameter.value.isPrimitive
                ) {
                    classBuilder.addProperty(
                        kotlinPropertyMaker.createProperty(
                            parameter.value,
                            parameter.key,
                            mock = false
                        )
                    )
                } else {
                    classBuilder.addProperty(
                        kotlinPropertyMaker.createProperty(
                            parameter.value,
                            parameter.key,
                            mock = true
                        )
                    )
                }
            }
        }
        for (method in methodMap.second) {
            classBuilder.addFunction(
                kotlinFunctionMaker.createTestFunction(
                    method.key,
                    method.value
                )
            )
        }
        return classBuilder.build()
    }
}