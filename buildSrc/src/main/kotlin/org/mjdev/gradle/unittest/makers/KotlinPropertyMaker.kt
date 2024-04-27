/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest.makers

import com.squareup.kotlinpoet.PropertySpec

internal interface KotlinPropertyMaker {
    fun createProperty(
        parameter: Class<*>,
        name: String,
        mock: Boolean
    ): PropertySpec
}