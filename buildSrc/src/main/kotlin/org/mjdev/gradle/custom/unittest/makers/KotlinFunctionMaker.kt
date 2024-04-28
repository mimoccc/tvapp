/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.unittest.makers

import com.squareup.kotlinpoet.FunSpec
import java.lang.reflect.Method

internal interface KotlinFunctionMaker {
    fun createTestFunction(
        functionName: String,
        method: Method
    ): FunSpec

    fun createBeforeFunction(
        clazz: Class<*>,
        testObjectMap: Map<String, Map<String, Class<*>>>
    ): FunSpec
}