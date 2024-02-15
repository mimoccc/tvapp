/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest.makers

import com.squareup.kotlinpoet.FileSpec
import org.mjdev.gradle.unittest.Constants.Companion.PACKAGE_SEPARATOR
import org.mjdev.gradle.unittest.Constants.Companion.TEST_FILE_SUFFIX

internal class KotlinFileMakerImpl(
    private val kotlinClassMaker: KotlinClassMaker = KotlinClassMakerImpl()
) : KotlinFileMaker {

    override fun makeKotlinFile(clazz: Class<*>): FileSpec? {
        val testClass = kotlinClassMaker
            .createTestClass(clazz, clazz.simpleName + TEST_FILE_SUFFIX) ?: return null
        return FileSpec.builder(
            clazz.canonicalName.substringBeforeLast(PACKAGE_SEPARATOR),
            clazz.simpleName + TEST_FILE_SUFFIX
        ).addType(testClass).build()
    }
}